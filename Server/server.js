var express = require('express'),
	app = express(),
	http = require('http'),
	httpApp = express(),
	port = process.env.PORT || 3000,
	htmlPort = process.env.PORT || 80,
	mongoose = require('mongoose'),
	FATmodels = require('./api/models/models.js'),
	userAvatar = mongoose.model('User Avatars'),
	battles = mongoose.model('Battles'),
	User = mongoose.model('User'),
	userBattles = mongoose.model('User Battles'),
	bodyParser = require('body-parser');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/Food_Adventure');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(function(req, res, next){	
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Credentials", true);
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

var routes = require('./api/routes/routes.js');
routes(app);

app.get('/test', function(req, res){
	console.log("====Weekly Monthly Tournament====");
	var sessions = [];
	//avatars.find({}, function(err, foundAll){
	userAvatar.find({}, function(err, foundAll){
		var shuffledArray = shuffle(foundAll);
		var totalSession = shuffledArray.length / 10;
		for (var i = 0; i < totalSession; i++){
			sessions.push(new battles({ 'room': Math.floor(Math.random() * 99999), 'date': Date.now() }));
		}

		var userSession = [];
		var combatPoint1 = 0;
		var combatPoint2 = 0;
		for (var i = 0; i < shuffledArray.length; i++){
			//var avatarGet = shuffledArray[i];
			var avatarGet = shuffledArray[i].avatarID[0];
			var combatPoint = (5 * avatarGet.strength) + (4 * avatarGet.agility) + (5 * avatarGet.intellect) + (4 * avatarGet.dexterity) + (3 * avatarGet.stamina) + (2 * avatarGet.health); 
			userSession.push(new userBattles({ 'room': sessions[Math.floor(i/10)], 'participant': avatarGet, 'Group': i%2, 'damage': combatPoint }));
			if (i%2 == 0) combatPoint1 += combatPoint;
			else combatPoint2 += combatPoint;
			if (i%10==0 && i !== 0) {
				console.log(combatPoint1 + " , " + combatPoint2);
				if(combatPoint1 > combatPoint2) {
					userSession[i-1].result = true;
					userSession[i-3].result = true;
					userSession[i-5].result = true;
					userSession[i-7].result = true;
					userSession[i-9].result = true;
				} else if (combatPoint2 > combatPoint1){
					userSession[i].result = true;
					userSession[i-2].result = true;
					userSession[i-4].result = true;
					userSession[i-6].result = true;
					userSession[i-8].result = true;
				}
				combatPoint1 = 0;
				combatPoint2 = 0;
			}	
		}
		var finalCalc = shuffledArray.length%10;
		for (var j = (shuffledArray.length%10); j > 0; j--){
			if (j%2 == 0){
				if (combatPoint1 < combatPoint2){
				 userSession[shuffledArray.length - j].result = true;
				} else{
				 userSession[shuffledArray.length - j].result = false;
				}
			} else if (j%2 == 1){
				if (combatPoint1 > combatPoint2){
				 userSession[shuffledArray.length - j].result = true;
				} else{
				 userSession[shuffledArray.length - j].result = false;
				}
			}
		}
		battles.insertMany(sessions, function(err, sesionInsert){
			if (err) console.log(err);
		});

		userBattles.insertMany(userSession, function(err, userSessionInsert){
			if (err) console.log(err);
		});
	});
	console.log("All avatar has been allocated");

	allUsers = []
	User.find({}, function(err, foundAllUser){
		for (var j = 0; j < foundAllUser.length; j++){
			if (foundAllUser[j].coins > (500 + (foundAllUser[j].tier * 250))){
				foundAllUser[j].tier += 1;
				allUsers.push(foundAllUser[j]);
			}
		}

		allUsers.forEach(function(aUser){
			User.update({'userUID': aUser.userUID}, aUser, function(err, updateOne){
				if (err) console.log(err);
			});
		});
	});

	res.send("Done");
})

function shuffle(array)
{
	var currentIndex = array.length, tempVal, ranIndex;
	while (currentIndex !== 0)
	{
		ranIndex = Math.floor(Math.random() * currentIndex);
		currentIndex -= 1;
		tempVal = array[currentIndex];
		array[currentIndex] = array[ranIndex];
		array[ranIndex] = tempVal;
	}
	return array
}

app.listen(port);
