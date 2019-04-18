'use strict';

var mongoose = require('mongoose'),
	User = mongoose.model('User'),
	keyChain = mongoose.model('Keychain'),
	Reward = mongoose.model('Reward'),
	userRewards = mongoose.model('User Rewards'),
	guilds = mongoose.model('Guilds'),
	guildMembers = mongoose.model('Guild Members'),
	avatars = mongoose.model('Avatar'),
	userAvatar = mongoose.model('User Avatars'),
	userBattle = mongoose.model('User Battles'),
	equip = mongoose.model('Equip'),
	crypto = require('crypto'),
	url = require('url'),
	mailer = require('nodemailer'),
	message = mongoose.model('Messages'),
	ObjectID = require('mongodb').ObjectID,
	algorithm = 'aes-256-cbc';


exports.testEncryption = function(req, res)
{
	let cipher = crypto.createCipheriv('aes-256-cbc', Buffer.from(key), iv);
	let cipherText = cipher.update(req.body.getMsg + "A very hardcoded delicious salt");
	cipherText = Buffer.concat([cipherText, cipher.final()]);
	res.json({key: key.toString('hex'), iv: iv.toString('hex'), cipherMsg: cipherText.toString('hex')});
}

exports.testDecryption = function(req, res)
{
	let iv = Buffer.from(getPckg.iv, 'hex');
	let cipherText = Buffer.from(getPckg.cipherText, 'hex');
	let decipher = crypto.createDecipheriv('aes-256-cbc', Buffer.from(getPckg.key), iv);
	let plainText = decipher.update(cipherText);
	plainText = Buffer.concat([plainText, decipher.final()]);
	res.json(plainText.toString());
}

exports.sendMessage = function(req, res){
	User.findOne({ 'userName': req.body.to}, function(err, toUser){
		User.findOne({ 'userName': req.body.from}, function(err, fromUser){
			var new_message = new message({to: toUser, from: fromUser, subject: req.body.subject, bodyMsg: req.body.msg});
			new_message.save(function(err, newMessage){
				if (err) console.log(err);
				res.send("Message Sent");
			});
		});
	});
}

exports.removeMessage = function(req, res){
	message.deleteOne({ '_id': req.body.id}, function(err, removeMsg){});
	res.send("Deleted");
}

exports.getMessages = function(req, res){
	message.find({'to.userName': req.params.userName}, function(err, findMessages){
		res.json(findMessages);
	});
}

//SELECT * FROM users;
exports.getUsers = function(req, res){
	User.find({}, function(err, user)
	{
		if (err) res.send(err);
		res.json(user);
	});
};

//INSERT INTO users ( userUID, userNAme, password, firstName, lastName, email, phone, dateOfBirth ) VALUES ( //Insert values here )
exports.createUser = function(req, res){
	var newUsers = new User(req.body);
	newUsers.save(function(err, createUser)
	{
		sendEmail(req.body.email, req.body.firstName + " " + req.body.lastName, req.body.userUID, "Create");
		if (err) res.send(err);
		res.json(createUser);
	});
};

exports.javaRegisterUser = function(req, res){
	User.findOne({ 'userUID': req.params.UserUID }, function(err, foundUser)
	{
		if (err) res.send(err);
		if (foundUser == null) res.send("User not found");
		else {
			foundUser.userName = req.body.userName;
			foundUser.password = encryptPassword(foundUser, req.body.password);
			sendEmail(foundUser.email, foundUser.firstName + " " + foundUser.lastName, foundUser.userUID, "Register");
			User.findOneAndUpdate({ 'userUID': req.params.UserUID }, foundUser, function(err, updateUser)
			{
				res.send(updateUser);
			});
		}
	});
}

exports.updateReward = function(req, res)
{
//	var item = req.params.rewardName.replace(/<>/g, ' ');
	var item = req.params.rewardName.replace(/\+/g, ' ');
	Reward.findOne({ 'name': item }, function(err, rewardFound)
	{
		if (rewardFound == null){
			var new_rewards = new Reward(req.body);
			new_rewards.name = item;
			new_rewards.save(function(err, createReward)
			{
				res.json(createReward);
			});
		} else {
			req.body.name = item;
			Reward.findOneAndUpdate({ 'name': item }, req.body, {upsert:true, overwrite:true}, function(err, updateReward)
			{
				res.json(updateReward);
			});
		}
	});
}

exports.searchReward = function(req, res)
{
//	var item = req.params.rewardName.replace(/+/g, ' ');
	var item = req.params.rewardName.replace(/\+/g, ' ');
	Reward.findOne({ 'name': item }, function(err, rewardFound)
	{
		if (rewardFound == null){
			var new_rewards = new Reward({'name': item});
			res.json(new_rewards);
		} else {
			res.json(rewardFound);
		}
	})
}

exports.getAllRewards = function(req, res)
{
	Reward.find({}, function(err, foundReward)
	{
		res.json(foundReward);
	});
}

exports.purchaseReward = function(req, res)
{
	User.findOne({ 'userUID': req.params.userUID }, function(err, foundUser)
	{
		if (foundUser.email == req.params.userEmail){
//		var item = req.params.rewardName.replace(/<>/g, ' ');
		var item = req.params.rewardName.replace(/\+/g, ' ');
		Reward.findOne({ 'name': item }, function(err, rewardFound)
		{
			if (foundUser.coins >= rewardFound.coin) 
			{
				foundUser.coins -= rewardFound.coin;
				User.findOneAndUpdate({ 'userUID': req.params.userUID }, foundUser, function(req, updateUser)
					{
						updateUser.markModified("coins");						
						var user_purchase = new userRewards({ 'user': foundUser, 'reward': rewardFound});
						user_purchase.save(function(err, newReward){
							if (err) res.send(err);
							sendEmail(foundUser.email, foundUser.firstName + " " + foundUser.lastName, foundUser.userUID, "Item");

							res.json(newReward);
						});
						//res.json(updateUser);
					});	
			} else{
				console.log("Transaction failed");
				res.send("Transaction failed");
			}
		});
		} else {
			console.log("Invalid Email");
			res.send("Invalid Email");
		}

	});
}

exports.getMyRewards = function(req, res)
{
	User.findOne({ 'userUID': req.params.userUID }, function(err, foundUser)
	{
		userRewards.find({ 'user.userUID': foundUser.userUID }, function(err, foundRewards)
		{
			res.json(foundRewards);
		});
	});
}
/*
exports.finishTransaction = function(req, res)
{
	User.findOne({ 'userUID': req.params.userUID }, function(err, foundUser)
	{
		var item = req.params.rewardName.replace(/<>/g, ' ');
		Reward.findOne({ 'name': item }, function(err, rewardFound)
		{
			var user_purchase = new userRewards({ 'user': foundUser, 'reward': rewardFound});
			user_purchase.save(function(err, newReward){
				if (err) {
					res.send(err);
				}
				res.json(newReward);
			});
		});
	});
}*/

exports.refundReward = function(req, res)
{
	User.findOne({ 'userUID': req.params.userUID }, function(err, foundUser)
	{
		if (foundUser.email == req.params.userEmail){
			userRewards.findOne( {'_id': req.params.rewardID } , function(err, rewardFound)
			{
				if (rewardFound.redeemedDate == null)
				{
					foundUser.coins += rewardFound.reward[0].coin;
					User.findOneAndUpdate( { 'userUID': req.params.userUID }, foundUser, function(err, userUpdate)
					{
						if (rewardFound.redeemedDate == null)
						{
							rewardFound.redeemedDate = Date.now();
							userRewards.findOneAndUpdate( { '_id': req.params.rewardID }, rewardFound, {upsert:true, overwrite:true}, function(err, rewardUpdate)
							{
								if (err) res.send(err);
								res.json(rewardUpdate);
							});
						} else {
							res.send("Already Redeemed");
						}
						//res.json(userUpdate);
					});
				}
			});
		} else {
			res.send("Invalid Email");
		}
	});
}

exports.finishRefund = function(req, res)
{
	User.findOne({ 'userUID': req.params.userUID }, function(err, foundUser)
	{
		userRewards.findOne( {'_id': req.params.rewardID } , function(err, rewardFound)
		{
			if (rewardFound.redeemedDate == null)
			{
				rewardFound.redeemedDate = Date.now();
				userRewards.findOneAndUpdate( { '_id': req.params.rewardID }, rewardFound, function(err, rewardUpdate)
				{
					if (err) res.send(err);
					res.send("Done");
				});
			}
		});
	});

}

exports.newAvatar = function(req, res){
	User.findOne( { 'userUID': req.params.userUID }, function(err, foundUser){
		avatars.findOne( { 'name': req.params.avatarName }, function(err, foundAvatar){
			if (foundAvatar == null){
				var new_avatar = new avatars(req.body);
				new_avatar.save(function(err, newAvatar){
					if (err) console.log(err);
					var user_avatar =  new userAvatar({ 'avatarID': newAvatar, 'userID': foundUser });
					user_avatar.save(function(err, newAvatarUser){
						if (err) console.log(err);
						res.json(newAvatarUser);
					});
				});
			}
			else {
				res.send("Avatar Exists");
			}
		});
	});
}


exports.getAnAvatar = function(req, res){
	avatars.findOne({ 'name': req.params.avatarName}, function(err, foundAvatars){
//	userAvatar.findOne({ 'avatarID.name': req.params.avatarName }, function(err, foundAvatars){
		if (foundAvatars == null) res.send("Not Found");
		else res.json(foundAvatars);
//		else res.json(foundAvatars.avatarID[0]);
	});
}

exports.getAllUserAvatar = function(req, res){
	userAvatar.find({ 'userID.userUID': req.params.userUID }, function(err, foundAvatars){
		res.json(foundAvatars);
	});
}

exports.setAvatarAsDefault = function(req, res){
	userAvatar.find({ 'userID.userUID': req.params.userUID}, function(err, foundUser){
		if (foundUser.length == 0){
			console.log("wrong user");
			res.send("Wrong User");
		} else {
			var allAvatar = [];
			foundUser.forEach(function(eachAvatar){
				if (eachAvatar.avatarID[0].name == req.params.avatarName){
					eachAvatar.main = true;
				//	console.log("default avatari " + req.params.avatarName);
				} else {
					eachAvatar.main = false;
				//	console.log(eachAvatar.avatarID[0].name + " / " + req.params.avatarName);
				}		
				userAvatar.findOneAndUpdate({ 'avatarID.name': eachAvatar.avatarID[0].name }, eachAvatar, {upsert:true, overwrite:true},  function(err, updateAvatar){
					if (err) {
						console.log(err);
					}
				//allAvatar.push(eachAvatar);
				});
//			allAvatar.forEach(function(eachAvatar){
//				console.log(eachAvatar.userID[0]);
//
//					console.log(updateAvatar);
//					console.log(eachAvatar);
//					console.log(updateAvatar);
//				});
			});
			res.send("Complete");
//			console.log(foundUser);
//			userAvatar.updateMany(foundUser, function(err, updateUsers){
//				if (err) res.send(err);
//				console.log("update");
//			})
		}
	});
}

exports.htmlFindUser = function(req, res){
	User.findOne({ 'userName': req.query['userName'] }, function(err, foundUser)
	{
		if (foundUser != null){
			keyChain.findOne({'user.userName': req.query['userName']}, function(err, keyFound)		
			{
				var decPass = decryptPassword(keyFound, req.query['password']);
				if (decPass == foundUser.password){
					if (foundUser.frozen == false){
						res.json(foundUser);
					} else {
						res.send("Account Frozen");
					}
				} else {
					res.send("password not found");
				}
			});
		} else {
			res.send("User not found");
		}
	});
}

exports.htmlUpdateUser = function(req, res){

	User.findOne({ 'userUID': req.body.userUID }, function(err, foundUser){
		if (req.body.phone !== null && req.body.phone !== '') foundUser.phone = req.body.phone;
		if (req.body.email !== null && req.body.email !== '') foundUser.email = req.body.email;
		User.findOneAndUpdate({ 'userUID': req.body.userUID }, foundUser, {upsert:true, overwrite:true}, function(err, updateUser)
		{
			if (err) res.send(err);
			res.json(updateUser);
		});
	});
}



exports.htmlRegisterUser = function(req, res){
	User.findOne({ 'userUID': req.body['userUID'] }, function(err, foundUser)
	{
		if (err) res.send(err);
		else{
			if (foundUser == null) res.send("User not found");
			else {
				if (foundUser.userName == null){
					foundUser.userName = req.body['userName'];
					foundUser.password = encryptPassword(foundUser, req.body['password']);
					User.findOneAndUpdate({ 'userUID': req.body['userUID'] }, foundUser,{upsert:true, overwrite:true}, function(err, updateUser)
					{
						if (err) {
							console.log(err);
							res.send(err);
						}
						sendEmail(foundUser.email, foundUser.firstName + " " + foundUser.lastName, foundUser.userUID, "Register");
						res.json(updateUser);
					});
				} else{
					console.log("user existed");
					res.send("User already exist");
				}
			}
		}
	});
}

exports.changePassword = function(req, res){
	User.findOne({ 'userUID': req.body['userUID'] }, function(err, foundUser){
		keyChain.remove({ 'user.userUID': req.body['userUID'] }, function(err, removeKey){});
		foundUser.password = encryptPassword(foundUser, req.body['password']);
		User.findOneAndUpdate({ 'userUID': req.body['userUID'] }, foundUser, {upsert:true, overwrite:true}, function(err, updateUser){});
		res.send("Password Changed");
	});
}

exports.findUser = function(req, res){
	User.findOne({ 'userName': req.params.userName }, function(err, foundUser)
	{
		if (foundUser != null){
		keyChain.findOne({'user.userName': req.params.userName}, function(err, keyFound)		
		{
			var decPass = decryptPassword(keyFound, req.params.password);
			if (decPass == foundUser.password || foundUser.password == req.params.password){
				if (foundUser.frozen == false){
					res.json(foundUser);
				} else {
					res.send("Account Frozen");
				}
			} else {
				res.send("Invalid Login");
			}
		});
		} else {
			res.send("User not found");
		}
	});
}

exports.promoteMember = function(req, res){
	var guildGet = req.params.guildName.replace("<>", " ");
	//var item = req.params.rewardName.replace(/\+/g, ' ');
	guildMembers.findOne({ 'member.userName': req.params.leaderName, 'leader': true, 'guild.name': guildGet }, function(err, foundLeader)
	{
		guildMembers.findOne({ 'member.userName': req.params.memberName, 'leader': false, 'guild.name': guildGet }, function(err, foundMember)
		{
			foundLeader.leader = false;
			foundMember.leader = true;
			guildMembers.findOneAndUpdate({ 'member.userName': req.params.leaderName, 'leader': true, 'guild.name': guildGet }, foundLeader, {upsert:true, overwrite:true}, function(err, updateLeader){});
			guildMembers.findOneAndUpdate({ 'member.userName': req.params.memberName, 'leader': false, 'guild.name': guildGet }, foundMember, {upsert:true, overwrite:true}, function(err, updateMember){});
			res.send("Leader Passed");
		});
	});
}

exports.setLeader = function(req, res){
	var guildGet = req.params.GuildName.replace(/<>/g, " ");
	guildMembers.findOne({ 'guild.name': "Unicorn Theoriest"}, function(err, foundGuild)
	{
		foundGuild.leader = true;
		guildMembers.findOneAndUpdate({ '_id': foundGuild._id }, foundGuild, {upsert:true, overwrite:true}, function(err, memberUpdate){});
		res.send("Leader status update");
	});
}
exports.searchUser = function(req, res){
	User.findOne({ 'userUID': req.params.UserUID }, function(err, foundUser)
	{
		res.json(foundUser);
	});
};

exports.createGuild = function(req, res){
	var guildGet = req.params.GuildName.replace(/<>/g, " ");
	guilds.findOne({ 'name': guildGet }, function(err, foundGuild)
	{
		if (foundGuild == null){
			var new_guild = new guilds({ 'name': guildGet, 'members': 1 });
			new_guild.save(function(err, newGuild){
				User.findOne({ 'userUID': req.params.UserUID }, function(err, foundUser){
					var new_member = new guildMembers({ 'member': foundUser, 'guild': newGuild, 'dateAdmitted': Date.now(), 'leader': true });
					new_member.save(function(err, newMember){
//						res.send("Done");
						res.json(newGuild);
					});
				});
			});
		} else {
			res.send("Guild Exists");
		}
	});
}

exports.searchGuild = function(req, res){
	var guildGet = req.params.GuildName.replace(/<>/g, " ");
	guilds.findOne({ 'name': item }, function(err, foundGuild){
		if (foundGuild == null){
			var createNewGuild = new guilds({ 'name': item, 'establishDate': Date.now() });
			createNewGuild.save(function(err, guildSaved){
				res.json(guildSaved);
			});
		} else {
			res.json(foundGuild);
		}
	})
}

exports.getMyGuilds = function(req, res){
	guildMembers.find({ 'member.userUID': req.params.UserUID}, function(err, foundGuild)
	{
		var guildList = [];
		foundGuild.forEach(function(eachMem){
			if (eachMem.dateAdmitted != null){
				guildList.push(eachMem);
			}
		});
		res.json(guildList);
	});
}

exports.getGuildMembers = function(req, res){
	var guildGet = req.params.GuildName.replace(/<>/g, " ");
	guildMembers.find({ 'guild.name': guildGet }, function(err, foundMembers)
	{
		var memberList = [];
		foundMembers.forEach(function(eachMem){
			if (eachMem.dateAdmitted != null) {
				memberList.push(eachMem);
			}
		});
		res.json(memberList);
	});
}

exports.getAllGuilds = function(req, res){
	guilds.find({}, function(err, foundGuild)
	{
		res.json(foundGuild);
	});
}

exports.getAllGuildMember = function(req, res){
	guilds.find({}, function(err, foundGuilds)
	{
		var guildCounts = [];
		foundGuilds.forEach(function(eachGuild){
			//console.log("guild: " + eachGuild.name);
			guildMembers.count({'guild.name': eachGuild.name }, function(err, membercount){
				eachGuild.members = membercount;
				guildCounts.push(eachGuild);
			});
		});
	});
}

exports.kickFromGuild = function(req, res){
	console.log(req.body);
User.findOne({ 'userName': req.body.user }, function(err, foundUser)
	{
		guilds.findOne({ 'name': req.body.guild }, function(err, foundGuild)
		{
			if (foundUser != null && foundGuild != null){
				guildMembers.findOne({'guild.name': foundGuild.name, 'member.userName': foundUser.userName}, function(err, checkMember){
					if (checkMember != null){
						guildMembers.deleteOne({'guild.name': foundGuild.name, 'member.userName': foundUser.userName}, function(err, removeMember){
							if (err) res.send(err);
							else {
								foundGuild.members -= 1;
								if (foundGuild.members > 0){
									guilds.findOneAndUpdate({'name': req.body.guild}, foundGuild, {upsert:true, overwrite:true}, function(err, updateGuild){
										if (err) res.send(err);
										res.json(updateGuild);
									});
								} else {
									guilds.deleteOne({'name': foundGuild.name}, function(err, removeGuild){
										res.send("Remove Guild");
									});
								}
							}
						});
					}
				});
			}
		});
	});


}
exports.leaveGuild = function(req, res){
	var guildGet = req.params.GuildName.replace("<>", " ");
	User.findOne({ 'userUID': req.params.UserUID }, function(err, foundUser)
	{
		guilds.findOne({ 'name': guildGet }, function(err, foundGuild)
		{
			if (foundUser != null && foundGuild != null){
				guildMembers.findOne({'guild.name': foundGuild.name, 'member.userName': foundUser.userName}, function(err, checkMember){
					if (checkMember != null){
						guildMembers.deleteOne({'guild.name': foundGuild.name, 'member.userName': foundUser.userName}, function(err, removeMember){
							if (err) res.send(err);
							else {
								foundGuild.members -= 1;
								if (foundGuild.members > 0){
									guilds.findOneAndUpdate({'name': guildGet}, foundGuild, {upsert:true, overwrite:true}, function(err, updateGuild){
										if (err) res.send(err);
										res.json(updateGuild);
									});
								} else {
									guilds.deleteOne({'name': foundGuild.name}, function(err, removeGuild){
										res.send("Remove Guild");
									});
								}
							}
						});
					}
				});
			}
		});
	});

}

exports.joinGuild = function(req, res){
	var guildGet = req.params.GuildName.replace("<>", " ");
	User.findOne({ 'userUID': req.params.UserUID }, function(err, foundUser)
	{
		guilds.findOne({ 'name': guildGet }, function(err, foundGuild)
		{
			if (foundUser != null && foundGuild != null){
				guildMembers.findOne({'guild.name': foundGuild.name, 'member.userName': foundUser.userName}, function(err, checkMember){
					if (checkMember == null){
						var newGuildMember = new guildMembers({ 'member': foundUser, 'guild': foundGuild});
						newGuildMember.save(function(err, newMember){
//							res.json(newMember);
						});
						guildMembers.findOne({'guild.name': foundGuild.name, 'leader': true}, function(err, foundLeader){
							res.send(foundLeader.member[0].userName);
						});
	//					res.json();
//						foundGuild.members += 1;
//						guilds.findOneAndUpdate({'name': guildGet}, foundGuild, {upsert:true, overwrite:true}, function(err, updateGuild){
//							if (err) console.log(err);
//							res.send("Updated");
//						});
					}
					else{
						res.send("Member already in guild");
					}
				});
			}
		});
	});
}

exports.manageGuildMem = function(req, res){
	guildMembers.findOne({'member.userName': req.body.userName, 'guild.name': req.body.guildName}, function(err, foundMember){
		if (foundMember != null && foundMember.dateAdmitted == null){
			if (req.body.admittance == "Admit"){
				guilds.findOne({ 'name': req.body.guildName }, function(err, foundGuild){
					foundMember.dateAdmitted = Date.now();
					foundGuild.members += 1;
					guildMembers.findOneAndUpdate({'member.userName': req.body.userName, 'guild.name': req.body.guildName}, foundMember, function(err, updateMember){});
					guilds.findOneAndUpdate({ 'name':req.body.guildName }, foundGuild, function(err, updateGuild){});
					res.send("admitted");
				});
			} else if (req.body.admittance == "Deny"){
				guildMembers.deleteOne({'guild.name': req.body.guildName, 'member.userName': req.body.userName}, function(err, removeMember){});
				res.send("denied");
			}
		} else {
			res.send("Decision already decided");
		}
	});
}
exports.removeAvatar = function(req, res){
	userAvatar.deleteOne({ 'avatarID.name': req.params.avatarName }, function(err, removeAvatar)
		{
			if (err) res.send(err);
		});
	avatars.deleteOne({ 'name': req.params.avatarName }, function(err, removeAAvatar)
		{
			if (err) res.send(err);
			res.send("Avatar deleted");
		});
}

//DELETE FROM users WHERE UserUID='//userUID here'
exports.terminateAccount = function(req, res){
	User.deleteOne({ 'userUID': req.body.userUID }, function(err, removeUser)
	{
		if (err) res.send(err);
	});
	keyChain.deleteOne({ 'user.userUID': req.body.userUID }, function(err, removeKey)
	{
		if (err) res.send(err);
	});
	res.send("Done");
};

//UPDATE users SET firstName="//set first name" WHERE UserUID="//UserUID here"
exports.updateUsers = function(req, res){
	User.findOneAndUpdate({ 'userUID': req.params.UserUID }, req.body, {upsert:true, overwrite:true}, function(err, updateUser)
	{
		if (err) res.send(err);
		res.json(updateUser);
	});
};

exports.getMyBattles = function(req, res){
	var myAvatars;
	userAvatar.find({ 'userID.userUID': req.params.userUID }, function(err, findAllAvatar){
		if (findAllAvatar == null) res.send("Empty");
		myAvatars = findAllAvatar;
	});

	while ( myAvatars === undefined) {
		require('deasync').runLoopOnce();
	}

	if (myAvatars.length == 0){
		res.send("empty");
	} else {
		var myBattles = [];
		var length = 0;
		myAvatars.forEach(function(eachAvatar){
			userBattle.find({ 'participant.name': eachAvatar.avatarID[0].name }, function(err, findAllAvatarBattle){
					length += 1;
					myBattles.push(findAllAvatarBattle);
				});
		});
		while( myBattles.length == 0){
			require('deasync').sleep(1000);
		}
		res.json(myBattles);
	}
}

exports.getEquip = function(req, res){
	equip.findOne({'avatarID.name': req.body.name}, function(err, foundAvatar){
		if (foundAvatar == null)
			res.send("Naked");
		else res.json(foundAvatar.item[0].reward[0]);
	});
}

exports.unequipItem = function(req, res){
	equip.findOne({ 'avatarID.name': req.body.name}, function(err, foundAvatar){
		if (foundAvatar == null){
		} else {
			foundAvatar.avatarID[0].strength -= foundAvatar.item[0].reward[0].strength;
			foundAvatar.avatarID[0].dexterity -= foundAvatar.item[0].reward[0].dexterity;
			foundAvatar.avatarID[0].agility -= foundAvatar.item[0].reward[0].agility;
			foundAvatar.avatarID[0].stamina -= foundAvatar.item[0].reward[0].stamina;
			foundAvatar.avatarID[0].health -= foundAvatar.item[0].reward[0].health;
			foundAvatar.avatarID[0].intellect -= foundAvatar.item[0].reward[0].intellect;
			equip.remove({ 'avatarID.name': req.body.name}, function(err, removed){
			});
			avatars.findOneAndUpdate({'name': foundAvatar.avatarID[0].name}, foundAvatar.avatarID[0], function(err, update){
			});
			res.send("Done");
		}
	});
}

exports.equipItem = function(req, res){
	console.log(req.body);
	userRewards.findOne({ '_id': req.body.item}, function(err, foundReward){
		if (foundReward == null)
			res.json(null)
		else{
			equip.findOne({'item._id': foundReward._id}, function(err, wearing){
				if (wearing == null){
					avatars.findOne({ 'name': req.body.user }, function(err, findAnAvatar){
//					userAvatar.find({ 'userID.userUID': req.body.user }, function(err, findAllAvatar){
//						findAllAvatar.forEach(function(eachAvatar){
//							if (eachAvatar.main){
						findAnAvatar.strength += foundReward.reward[0].strength;
						findAnAvatar.dexterity += foundReward.reward[0].dexterity;
						findAnAvatar.intellect += foundReward.reward[0].intellect;
						findAnAvatar.agility += foundReward.reward[0].agility;
						findAnAvatar.stamina += foundReward.reward[0].stamina;
						findAnAvatar.health += foundReward.reward[0].health;
						var newWearing = new equip({ avatarID: findAnAvatar, item: foundReward}); 
						newWearing.save(newWearing, function(err, doneSaving){
							if (err) console.log(err)
						});
						avatars.findOneAndUpdate({'name': findAnAvatar.name}, findAnAvatar, function(err, updated){
						});
					res.send("Done");
//							}
//						});
					})
				} else {
					res.json("Wearing item");
				}
			})
		}
	});
}

function encryptPassword(user, password)
{
	var key = crypto.randomBytes(32);
	var iv = crypto.randomBytes(16);
	let cipher = crypto.createCipheriv('aes-256-cbc', Buffer.from(key), iv);
	let cipherText = cipher.update(password + "A very hardcoded delicious salt");
	cipherText = Buffer.concat([cipherText, cipher.final()]);
	var new_keyChain = new keyChain({ user: user, key: key.toString('hex'), iv: iv.toString('hex') });
	new_keyChain.save(function(err, newKey){
	});
	return cipherText.toString('hex');
	
}

function decryptPassword(keyFound, password)
{
	var iv = Buffer.from(keyFound.iv, 'hex');
	var key = Buffer.from(keyFound.key, 'hex');
	let cipher = crypto.createCipheriv('aes-256-cbc', Buffer.from(key), iv);
	let cipherText = cipher.update(password + "A very hardcoded delicious salt");
	cipherText = Buffer.concat([cipherText, cipher.final()]);
	return cipherText.toString('hex');
	
}

function sendEmail(email, fullName, userID, msg){
	var transporter = mailer.createTransport({
		service: 'gmail',
		auth: {
			user: 'foodAdventureCo@gmail.com',
			pass: 'fat4912019'
		}
	});

	if (msg == "Create"){
		var mailOptions = {
			from: 'foodAdventureCo@gmail.com',
			to: email,
			subject: "Food Adventure Go Account Creation",
			text: "Dear " + fullName + 
			" \n\n we are contacting you because you have created a new account with us here at Food Adventure Co. Below will be a link containing an address that will help you register your account" +
			" \n\n http://35.235.118.188/register.php" +
			" \n\n Your automated unique ID is: " + userID +
			" \n\n We hope you enjoy your time with us!" +
			" \n\n Food Adventure Co."
		};
	} else if (msg == "Register"){
		var mailOptions = {
			from: 'foodAdventureCo@gmail.com',
			to: email,
			subject: "Food Adventure Go Account Registration",
			text: "Dear " + fullName + 
			" \n\n This is an automated response to notif you that your user ID " + userID + " has been registered successfully." +
			" \n\n http://35.235.118.188/index.php" +
			" \n\n Follow the following link to start your adventure with us!" +
			" \n\n We hope you enjoy your time with us!" +
			" \n\n Food Adventure Co."
		}
	} else if (msg == "Item") {
		var mailOptions = {
			from: 'foodAdventureCo@gmail.com',
			to: email,
			subject: "Food Adventure Item Purchase",
			text: "Dear " + fullName +
			" \n\n This is an automated response to notify you that you have purchased an item off the store." +
			" \n\n We hope you enjoy your hard earned loyalty reward" +
			" \n\n Food Adventure Co."
		}
	}

	transporter.sendMail(mailOptions, function(err, info){
		if (err) console.log(err)
		else console.log('Email sent: ' + info.response);
	});
}
/*	var encPass = "";
	keyChain.findOne({'user.userName': user.userName}, function(err, keyFound)
		{
			let decipher = crypto.createCipheriv('aes-256-cbc', key, iv);
			
			let cipherText = decipher.update(password + "A very hardcoded delicious salt");
			cipherText = Buffer.concat([cipherText, decipher.final()]);

			//let plainText = decipher.update(cipherText);
			//console.log(plainText);
			//plainText = Buffer.concat([plainText, decipher.final()]);
			console.log("Test: " + cipherText.toString('hex'));
			if (user.password == cipherText.toString('hex'))
			{
				console.log("True");
				return true;
			}
			return false;
		});
}*/
