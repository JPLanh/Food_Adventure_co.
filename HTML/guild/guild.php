<!DOCTYPE html>
<html lang="en">
<head>
  <title>Food Adventure Co.</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<!-- gets the cookie -->
  <input type="hidden" id="userUID" value="<?php echo $_COOKIE['userUID'];?>"/>
  <input type="hidden" id="username" value="<?php echo $_COOKIE['username'];?>"/>
  <input type="hidden" id="password" value="<?php echo $_COOKIE['password'];?>"/>
  <input type="hidden" id="email" value="<?php echo $_COOKIE['email'];?>"/>
  <input type="hidden" id="phone" value="<?php echo $_COOKIE['phone'];?>"/>
  <input type="hidden" id="dateOfBirth" value="<?php echo $_COOKIE['dateOfBirth'];?>"/>
  <input type="hidden" id="coins" value="<?php echo $_COOKIE['coins'];?>"/>
  <input type="hidden" id="tier" value="<?php echo $_COOKIE['tier'];?>"/>
  <input type="hidden" id="diamonds" value="<?php echo $_COOKIE['diamonds'];?>"/>
  <input type="hidden" id="firstName" value="<?php echo $_COOKIE['firstName'];?>"/>
  <input type="hidden" id="lastName" value="<?php echo $_COOKIE['lastName'];?>"/>


<!-- load the cookie and search the user right on load -->
<script>
	$(document).ready(function()
	{
		var test;
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/htmlUsers",
			dataType: 'json',
			async: false,
			data: {userName: $("#username").val(), password: $("#password").val()},
			success: function(result){
				test = JSON.parse(JSON.stringify(result));						
			},
			error: function(result){
				alert(JSON.stringify(result));
			}
		});
		document.cookie = "userUID=" + test.userUID;
		document.cookie = "firstName=" + test.firstName;
	        document.cookie = "lastName=" + test.lastName;
		document.cookie = "email=" + test.email;
		document.cookie = "phone=" + test.phone;
		document.cookie = "dateOfBirth=" + test.dateOfBirth;
		document.cookie = "coins=" + test.coins;
		document.cookie = "tier=" + test.tier;
		document.cookie = "diamonds=" + test.diamonds;	
		document.getElementById("userUID").value = test.userUID;
		document.getElementById("email").value = test.email;

		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/myGuild/" + test.userUID,
			dataType: 'json',
			async: false,
			success: function(result){
				test2 = JSON.parse(JSON.stringify(result));						
			},
			error: function(result){
				alert(JSON.stringify(result));
			}
		});
		document.getElementById("myGuilds").innerHTML = "My Guild List";
		$.each(test2, function(index, value) {
	 		 document.getElementById("myGuilds").innerHTML += '<br><button type="button" onclick="gotoGuild(\'' + value.guild[0].name + '\', \'' + value.guild[0].rank + '\')" class="btn btn-success">' + value.guild[0].name + ' </button><br>';
		});
	});
	</script>



  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <!-- Custom styles for this template -->
  <link href="guild.css" rel="stylesheet">
</head>

<script type="text/JavaScript">
function createGuild(){
	$(document).ready(function()
	{
		$.ajax({
			type: "POST",
			crossDomain: true,
			url: "http://35.235.118.188:3000/guild/" + $("#userUID").val() + "/" + (document.getElementById("Guild Name").value).replace(" ", "<>"),
			dataType: 'json',
			async: false,
			success: function(result){
				if (result.responseText == "Guild Exists"){
					alert("Guild already exists, try a new name");
				} else if (result.responseText == "Guild Created"){
					alert("You are now the founders of " + document.getElementById("Guild Name").value);
					window.location = "http://35.235.118.188/guild/guild.php";					

				} else if (result.responseText == "Insufficient Diamonds"){
					alert("Insufficient Diamonds");
				}		
			},
			error: function(result){
				if (result.responseText == "Guild Created"){
					alert("You are now the founders of " + document.getElementById("Guild Name").value);
					window.location = "http://35.235.118.188/guild/guild.php";					
				} else if (result.responseText == "Guild Exists"){
					alert("Guild already exists, try a new name");
				} else if (result.responseText == "Insufficient Diamonds"){
					alert("Insufficient Diamonds");
				}	
			},
		});
	});
}

function newGuild(){
	$(document).ready(function()
	{
		document.getElementById("Main Panel").innerHTML = '<h3> New Guild </h3>';
		document.getElementById("Main Panel").innerHTML += '<input type="text" id="Guild Name" placeHolder="Guild Name"/>';
		document.getElementById("Main Panel").innerHTML += '<button type="button" onclick="createGuild()">Create Guild</button><br>';
		document.getElementById("Main Panel").innerHTML += 'Note: It cost 1 Diamond to create a new Guild';
	});
}

function joinGuild(){
	$(document).ready(function()
	{

		var test2;
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/guild",
			dataType: 'json',
			async: false,
			success: function(result){
				test2 = JSON.parse(JSON.stringify(result));						
			},
			error: function(result){
				alert(JSON.stringify(result));
			}
		});
		document.getElementById("Main Panel").innerHTML = '<h3> Join Guild </h3>';
		
		$.each(test2, function(index, value) {
			document.getElementById("Main Panel").innerHTML +=  '<div class="col-sm-3"><div class="panel panel-success"><div class="panel-heading">' + value.name + '#' + value.rank + ' </div>' +
				'<div class="panel-body"><img src="../img/diamondpile3.png" height="500" width="500" class="img-responsive" alt="Image"></div>' +
				'<div class="panel-footer"> members: ' + value.members + ' <button type="button" onclick="requestGuild(\'' + value.name + '\')"class="btn btn-success" style="margin-left: auto; display: block;">Join Guild</button></div></div></div>';
		});	
	});
}

function requestGuild(guildName){
	$(document).ready(function()
	{
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/guild/" + $("#userUID").val() + "/" + (guildName).replace(" ", "<>"),
			dataType: 'json',
			async: false,
			success: function(result){
			},
			error: function(result){
				alert("You have requested to joined the " + guildName);
				$.ajax({
					type: "POST",
					crossDomain: true,
					url: "http://35.235.118.188:3000/message",
					dataType: 'json',
					async: false,
					data: {
						to: result.responseText, 
						from: "FATco", 
						subject: $("#username").val() + " wants to join " + guildName,
						msg: "Dear " + result.responseText + "\n\n" + $("#username").val() + " would like to join the guild " + guildName
						},
					success: function(result2){
					},
					error: function(result2){
					}
				});
				window.location = "http://35.235.118.188/guild/guild.php";						
			}
		});
	});
}

function gotoGuild(guildName, guildRank) {
	$(document).ready(function()
	{
		document.getElementById("firstTab").innerHTML = '<h3>' + guildName + ' #' + guildRank + '</h3>';
		document.getElementById("secondTab").innerHTML = '<h3> Members </h3>';

		var test2;
		var leader;
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/aGuild/" + guildName,
			dataType: 'json',
			async: false,
			success: function(result){
				test2 = JSON.parse(JSON.stringify(result));						
			},
			error: function(result){
				alert(JSON.stringify(result));
			}
		});
		$.each(test2, function(index, value) {
			document.getElementById("secondTab").innerHTML += value.member[0].userName + '<br>';
			if (value.member[0].userName == $("#username").val() && value.leader == true){
				leader = true;
			}
		});
		if (leader){
			document.getElementById("secondTab").innerHTML += '<br><button type="button" onclick="promoteNewLeader(\'' + guildName + '\')">Promote new Leader</button>';
			document.getElementById("secondTab").innerHTML += '<button type="button" onclick="leaveGuild(\'' + guildName + '\')">Leave Guild</button>';
			document.getElementById("secondTab").innerHTML += '<button type="button" onclick="kickGuild(\'' + guildName + '\')">Kick Member</button>';
		} else {
			document.getElementById("secondTab").innerHTML += '<br><button type="button" onclick="leaveGuild(\'' + guildName + '\')">Leave Guild</button>';
		}	
	})
}

function leaveGuild(guildName){
	$(document).ready(function()
	{
		var check1 = prompt("Would you like to leave " + guildName + "?");
		if (check1 == $("#email").val()){
		var test2;
			$.ajax({
				type: "GET",
				crossDomain: true,
				url: "http://35.235.118.188:3000/leaveGuild/" + $("#userUID").val() + "/" + (guildName).replace(" ", "<>"),
				dataType: 'json',
				async: false,
				success: function(result){
					alert("You have left " + guildName);
					window.location = "http://35.235.118.188/guild/guild.php";
				},
				error: function(result){
					if (result.responseText == "Guild left"){
						alert("You have left " + guildName);
						window.location = "http://35.235.118.188/guild/guild.php";
					} else if (result.responseText == "Remove Guild"){
						alert("You were the last one to leave the guild");
						window.location = "http://35.235.118.188/guild/guild.php";
					}
				}
			});
		} else {
			alert("Invalid Email");
		}
	})
}
function kickGuild(guildName){
	$(document).ready(function()
	{
		var test2;
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/aGuild/" + guildName,
			dataType: 'json',
			async: false,
			success: function(result){
				test2 = JSON.parse(JSON.stringify(result));
				document.getElementById("secondTab").innerHTML = "";
				$.each(test2, function(index, value) {
					if (value.leader == false){
						document.getElementById("secondTab").innerHTML += '<button type="button" onclick="kickMember(\'' + value.member[0].userName + '\' , \'' + guildName + '\')">' + value.member[0].userName + '</button><br>';
					}
				});
			},
			error: function(result){
			}
		});
	})
}

function kickMember(getMember, guildName){
	$(document).ready(function()
	{
		var check1 = confirm("Would you like to kick " + getMember + "?");
		if (check1){
		var test2;
			$.ajax({
				type: "POST",
				crossDomain: true,
				url: "http://35.235.118.188:3000/kickFromGuild/",
				data: {user: getMember, guild: guildName},
				dataType: 'json',
				async: false,
				success: function(result){
					alert("You have kicked " + getMember + " from " + guildName);
					window.location = "http://35.235.118.188/guild/guild.php";
				},
				error: function(result){
					if (result.responseText == "Guild left"){
						alert("You have kicked " + getMember + " from " + guildName);
						//alert("You have left " + guildName);
						window.location = "http://35.235.118.188/guild/guild.php";
					} else if (result.responseText == "Remove Guild"){
						alert("You were the last one to leave the guild");
						window.location = "http://35.235.118.188/guild/guild.php";
					}
				}
			});
		} else {
			alert("Invalid Email");
		}
	})
}

function promoteNewLeader(guildName){
	$(document).ready(function()
	{
		var test2;
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/aGuild/" + guildName,
			dataType: 'json',
			async: false,
			success: function(result){
				test2 = JSON.parse(JSON.stringify(result));
				document.getElementById("secondTab").innerHTML = "";
				$.each(test2, function(index, value) {
					if (value.leader == false){
						document.getElementById("secondTab").innerHTML += '<button type="button" onclick="promoteMember(\'' + value.member[0].userName + '\' , \'' + guildName + '\')">' + value.member[0].userName + '</button><br>';
					}
				});
			},
			error: function(result){
			}
		});
	})
}

function promoteMember(getMember, guildName){
	$(document).ready(function()
	{
		var test2;
		var leader;
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/promote/" + $("#username").val() + "/" + getMember + "/" + guildName.replace(" ", "<>"),
			dataType: 'json',
			async: false,
			success: function(result){
				test2 = JSON.parse(JSON.stringify(result));						
			},
			error: function(result){
				alert("You have now promoted " + getMember + " as a new leader of " + guildName);
				window.location = "http://35.235.118.188/guild/guild.php";
			}
		});
		document.getElementById("secondTab").innerHTML = "";
		$.each(test2, function(index, value) {
			document.getElementById("secondTab").innerHTML += '<button type="button" onclick="promoteMember(\'' + value.member[0].userName + '\')">' + value.member[0].userName + '</button><br>';
		});
	})
}
</script>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Food Adventure Co.</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/home/home.php">Home</a></li>
      <li><a href="/shop/shop.php">Shop</a></li>
      <li><a href="/guild/guild.php" class="active">Guild</a></li>
      <li><a href="/avatar/avatar.php">Avatar</a></li>
<!--      <li><a href="/gacha_page/gacha.html">Gacha</a></li> -->

      <li style="position: absolute; right: 150px;"><a href="/account/account.php">My Account</a></li>
      <li style="position: absolute; right: 80px;"><a href="/">Logout</a></li>
    </ul>
  </div>
</nav>
  <div class="container text-center">
    <div class="row">
      <div class="col-sm-3 well">

	<div class="well" id="firstTab">
	 <p id="myGuilds"></p>
        </div>

	<div class="well" id="secondTab">
	 <button type="button" onclick="newGuild()">New Guild</button>
	 <button type="button" onclick="joinGuild()">Join Guild</button>
        </div>

      </div>

     <div class="col-sm-7">

        <div class="row">
          <div class="col-sm-12">
            <div class="panel panel-default text-left">
              <div class="panel-body" id="Main Panel">
              <div class="panel-body" id="Main Panel">
                  <p contenteditable="true">
<!---                    <h4>2 Guilds ready to battle near you!</h4>  -->
                  </p>
              <!--    <button class="button button1" style="margin-left: auto; display: block;">Find a Battle</button> -->


              </div>
            </div>
          </div>
	</div>
       </div>



</body>
