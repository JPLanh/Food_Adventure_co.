<!DOCTYPE html>
<html lang="en">
<head>
  <title>Food Adventure Co.</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
	$(document).ready(function(){
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

	var messages;
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/message/"+$("#username").val(),
		dataType: 'json',
		async: false,
		success: function(result){
			messages = JSON.parse(JSON.stringify(result));
		},
		error: function(result){
			alert(JSON.stringify(result));
		}
	});
	document.getElementById("messageList").innerHTML = "";
	messages.forEach(function(eachMsg){
		var time = (eachMsg.date.split("T")[1]).split(":");
        document.getElementById("messageList").innerHTML += '<div class="col-sm-12"><div class="col-sm-2">' +
       		 eachMsg.from[0].userName + '</div><div class="col-sm-8">' +
		'<h4>Subject: ' + eachMsg.subject + '</h4>' +
          	'<p>' + eachMsg.bodyMsg + '</p></div><div class="col-sm-2"><p>' + eachMsg.date.split("T")[0] + " " + time[0] +":"+time[1] + '</p>' +
            //<img src="../images/replyicon.png" alt="group" width="40px" />
            //<img src="../images/newmessage.png" alt="group" width="40px" />
		'<br></div></div><div class="clearfix"></div>';
		document.getElementById("messageList").innerHTML += '<button type="button" onclick="replyMessage(\'' + eachMsg.from[0].userName + '\' , \'' + eachMsg.subject + '\')">Reply</button>  ';
		document.getElementById("messageList").innerHTML += '<button type="button" onclick="deleteMessage(\'' + eachMsg._id + '\')">Delete</button>  '; 
		if (eachMsg.from[0].userName == "FATco") 
		{
			document.getElementById("messageList").innerHTML += '<button type="button" onclick="admitGuild(\'' + eachMsg.subject.split(" ")[0] + '\' , \'' + eachMsg.subject.split("join ")[1] + '\', \'Admit\')">Admit</button>  '; 

			document.getElementById("messageList").innerHTML += '<button type="button" onclick="admitGuild(\'' + eachMsg.subject.split(" ")[0] + '\' , \'' + eachMsg.subject.split("join ")[1] + '\', \'Deny\')">Deny</button>'; 
		}		
		document.getElementById("messageList").innerHTML += '<hr />';
	});
	});
	</script>
  <!-- Custom styles for this template -->
  <link href="messages.css" rel="stylesheet">
</head>
<body>

<script type="text/JavaScript">
function admitGuild(getUser, getGuild, decision){
	$.ajax({
		type: "POST",
		crossDomain: true,
		url: "http://35.235.118.188:3000/manageGuildMem/",
		dataType: 'json',
		data: {userName: getUser, guildName: getGuild, admittance: decision},
		async: false,
		success: function(result){
			alert("Finish");
		},
		error: function(result){
			alert(JSON.stringify(result));
			window.location = "http://35.235.118.188/message/messages.php";
		},
	});
}

function deleteMessage(getMSGID){
	$.ajax({
		type: "POST",
		crossDomain: true,
		url: "http://35.235.118.188:3000/removeMessage",
		dataType: 'json',
		data: {id: getMSGID},
		async: false,
		success: function(result){
			alert("Finish");
		},
		error: function(result){
			alert("Message has been deleted");
			window.location = "http://35.235.118.188/message/messages.php";
		},
	});
}

function submitReply(getFrom, getSubject){
	$.ajax({
		type: "POST",
		crossDomain: true,
		url: "http://35.235.118.188:3000/message",
		dataType: 'json',
		data: {to: getFrom, from: $("#username").val(), subject: getSubject, msg: document.getElementById("body").value},
		async: false,
		success: function(result){
			alert("Finished");
		},
		error: function(result){
			alert("Reply has been sent");
			window.location = "http://35.235.118.188/message/messages.php";
		},
	});
}
function submitMessage(){
	$.ajax({
		type: "POST",
		crossDomain: true,
		url: "http://35.235.118.188:3000/message",
		dataType: 'json',
		data: {to: document.getElementById("recepient").value, from: $("#username").val(), subject: document.getElementById("subject").value, msg: document.getElementById("body").value},
		async: false,
		success: function(result){
			alert("Finished");
		},
		error: function(result){
			alert("Message has been sent");
			window.location = "http://35.235.118.188/message/messages.php";
		},
	});
}

function replyMessage(from, subject){
	document.getElementById("messageList").innerHTML = "";
	document.getElementById("messageList").innerHTML += '   recepient: ' + from + ' <br><br>  '
	document.getElementById("messageList").innerHTML += '   subject: ' + subject + ' <br><br> '
	document.getElementById("messageList").innerHTML += '   <textarea type="text" rows="10" cols="150" name="body" id="body"></textarea><br><br>'
	document.getElementById("messageList").innerHTML += '<button type="button" onclick="submitReply(\'' + from + '\' , \'' + subject + '\')">Submit</button>';
}
function newMessage(){
	document.getElementById("messageList").innerHTML = "";
	document.getElementById("messageList").innerHTML += '   recepient: <input type="text" size="50" name="recepient" id="recepient"><br><br>'
	document.getElementById("messageList").innerHTML += '   subject: <input type="text" size="100" name="subject" id="subject"><br><br> Body:<br>'
	document.getElementById("messageList").innerHTML += '   <textarea type="text" rows="10" cols="150" name="body" id="body"></textarea><br><br>'
	document.getElementById("messageList").innerHTML += '<button type="button" onclick="submitMessage()">Submit</button>';
}


</script>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Food Adventure Co.</a>
      </div>
      <ul class="nav navbar-nav">
      <li><a href="/home/home.php">Home</a></li>
      <li><a href="/shop/shop.php">Shop</a></li>
      <li><a href="/guild/guild.php">Guild</a></li>
      <li><a href="/avatar/avatar.php">Avatar</a></li>
<!--      <li><a href="/gacha_page/gacha.html">Gacha</a></li> -->

      <li style="position: absolute; right: 150px;"><a href="/account/account.php">My Account</a></li>
      <li style="position: absolute; right: 80px;"><a href="/">Logout</a></li>
 
      </ul>
    </div>
  </nav>

  <div class="container">
    <div class="top">
      <h2>My Messages</h2>
    </div>
    <div class="row">
	<button type="button" onclick="newMessage()"> Compose new Message </button>
      <div class="shadow" id="messageList">

        <div class="col-sm-12">
          <div class="col-sm-2">
            <img src="../images/Avatar_Cat.png" class="img-circle" width="60px">
          </div>
          <div class="col-sm-8">
            <h4>Subject: When can you play?</h4>
            <p>Can you battle Saturday?</p>
          </div>
          <div class="col-sm-2">
            <p>02/20/19 11:37:23</p>
            <img src="../images/replyicon.png" alt="group" width="40px" />
            <img src="../images/newmessage.png" alt="group" width="40px" />
            <br>
          </div>
        </div>
        <div class="clearfix"></div>
        <hr />

        <div class="col-sm-12">
          <div class="col-sm-2">
            <img src="../images/avatar_seal.png" class="img-circle" width="60px">
          </div>
          <div class="col-sm-8">
            <h4>Subject: hello!</h4>
            <p>Pls add me.</p>
          </div>
          <div class="col-sm-2">
            <p>02/20/19 09:12:22</p>
            <img src="../images/replyicon.png" alt="group" width="40px" />
            <img src="../images/newmessage.png" alt="group" width="40px" />
            <br>
          </div>
        </div>
        <div class="clearfix"></div>
        <hr />

        <div class="col-sm-12">
          <div class="col-sm-2">
            <img src="../images/avatar_seal.png" class="img-circle" width="60px">
          </div>
          <div class="col-sm-8">
            <h4>Subject: hi</h4>
            <p>Let's be friends!</p>
          </div>
          <div class="col-sm-2">
            <p>02/19/19 16:08:52</p>
            <img src="../images/replyicon.png" alt="group" width="40px" />
            <img src="../images/newmessage.png" alt="group" width="40px" />
            <br>
          </div>
        </div>
        <div class="clearfix"></div>
        <hr />


        <div class="col-sm-12">
          <div class="col-sm-2">
            <img src="../images/avatar_dog.png" class="img-circle" width="60px">
          </div>
          <div class="col-sm-8">
            <h4>Subject: GG</h4>
            <p>THAT BATTLE THO.</p>
          </div>
          <div class="col-sm-2">
            <p>02/16/19 02:45:09</p>
            <img src="../images/replyicon.png" alt="group" width="40px" />
            <br>
          </div>
        </div>
        <div class="clearfix"></div>
        <hr />

        <div class="col-sm-12">
          <div class="col-sm-2">
            <img src="../images/Avatar_Cat.png" class="img-circle" width="60px">
          </div>
          <div class="col-sm-8">
            <h4>Subject: Let's go get boba</h4>
            <p>Boba & Battle? ;)</p>
          </div>
          <div class="col-sm-2">
            <p>02/08/19 06:36:42</p>
            <img src="../images/replyicon.png" alt="group" width="40px" />
            <br>
          </div>
        </div>
        <div class="clearfix"></div>
        <hr />

        <div class="col-sm-12">
          <div class="col-sm-2">
            <img src="../images/avatar_pig.jpeg" class="img-circle" width="60px">
          </div>
          <div class="col-sm-8">
            <h4>Subject: thx</h4>
            <p>thx for the add.</p>
          </div>
          <div class="col-sm-2">
            <p>01/22/19 07:02:29</p>
            <img src="../images/replyicon.png" alt="group" width="40px" />
            <br>
          </div>
        </div>
        <div class="clearfix"></div>
        <hr />


      </div>
    </div>
  </div>



</body>
</html>
