'use strict'
module.exports = function(app)
{
	var User = require('../controllers/controllers.js');

	app.route('/message')
		.post(User.sendMessage);

	app.route('/getWear')
		.post(User.getEquip);

	app.route('/unwear')
		.post(User.unequipItem);

	app.route('/kickFromGuild')
		.post(User.kickFromGuild);

	app.route('/wear')
		.post(User.equipItem);

	app.route('/message/:userName')
		.get(User.getMessages);

	app.route('/removeMessage')
		.post(User.removeMessage);

	app.route('/manageGuildMem')
		.post(User.manageGuildMem);

	app.route('/password')
		.post(User.changePassword);

	app.route('/adminUsers')
		.get(User.searchUser)
		.post(User.createUser);

	app.route('/terminateAccount')
		.post(User.terminateAccount);

	app.route('/adminSearch/:UserUID')
		.get(User.searchUser);

	app.route('/adminCreateReward/:rewardName')
		.post(User.updateReward)
		.get(User.searchReward);

	app.route('/guild/:UserUID/:GuildName')
		.post(User.createGuild)
		.get(User.joinGuild);
	
	app.route('/myGuild/:UserUID')
		.get(User.getMyGuilds);

	app.route('/promote/:leaderName/:memberName/:guildName')
		.get(User.promoteMember);

	app.route('/aGuild/:GuildName')
		.get(User.getGuildMembers);
	app.route('/aGuild')
		.get(User.getAllGuildMember);

	app.route('/guild')
		.get(User.getAllGuilds);
	app.route('/leaveGuild/:UserUID/:GuildName')
		.get(User.leaveGuild);

	app.route('/rewards')
		.get(User.getAllRewards);

	app.route('/rewards/:userUID')
		.get(User.getMyRewards);

	app.route('/refunds/:userEmail/:userUID/:rewardID')
		.get(User.refundReward);
//		.post(User.finishRefund);

	app.route('/rewards/:userEmail/:userUID/:rewardName')
		.get(User.purchaseReward);
//		.post(User.finishTransaction);

	app.route('/javaUsersLogin/:userName/:password')
		.get(User.findUser);

	app.route('/javaUsersModify/:UserUID')
		.post(User.updateUsers);

	app.route('/javaUsers/:UserUID')
		.post(User.javaRegisterUser);

	app.route('/htmlUsers/:userName/:password')
		.get(User.findUser);

	app.route('/htmlUsersModify')
		.post(User.htmlUpdateUser);

	app.route('/htmlUsers')
		.get(User.htmlFindUser)
		.post(User.htmlRegisterUser);

	app.route('/setLeader/:guildName')
		.get(User.setLeader);

	app.route('/avatar/:avatarName/:userUID')
		.post(User.newAvatar);

	app.route('/deleteAvatar/:avatarName')
		.get(User.removeAvatar);

	app.route('/defaultAvatar/:userUID/:avatarName')
		.get(User.setAvatarAsDefault);

//	app.route('/htmlAvatar/:avatarName/:userUID')
//		.post(User.htmlNewAvatar);

	app.route('/searchAvatar/:avatarName')
		.get(User.getAnAvatar);

	app.route('/avatar/:userUID')
		.get(User.getAllUserAvatar);

	app.route('/battles/:userUID')
		.get(User.getMyBattles);
};
