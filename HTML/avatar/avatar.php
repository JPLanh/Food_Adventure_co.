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
  <input type="hidden" id="HairStyle" value=0>
  <input type="hidden" id="HairColor" value=0>
  <input type="hidden" id="EyeColor" value=0>
  <input type="hidden" id="FurColor" value=0>
  <input type="hidden" id="Animal" value=0>

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
 	var test2;
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/avatar/" + test.userUID,
		dataType: 'json',
		async: false,
		success: function(result){
			test2 = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			alert(JSON.stringify(result));
		}
	});

	$.each(test2, function(index, value){
		document.getElementById("Avatar " + index).innerHTML = value.avatarID[0].name;
		if (value.main == false) document.getElementById("Avatar " + index).innerHTML += '<button type="button" onclick="defaultAvatar(\'' + value.avatarID[0].name + '\')">Make Default Avatar</button>';
		if (value.main == true) document.getElementById("Avatar " + index).innerHTML += '<button type="button">Current Default Avatar</button>';
		document.getElementById("Avatar " + index).innerHTML += '<button type="button" onclick="checkAvatar(\'' + value.avatarID[0].name + '\')">Check Avatar</button>';
		document.getElementById("Avatar " + index).innerHTML += '<button type="button" onclick="deleteAvatar(\'' + value.avatarID[0].name + '\')">Delete Avatar</button>';
		document.getElementById("Avatar " + index + " info").innerHTML = '<img src="/img/fox.jpg" class="displayAvatar" height="200" width="200" alt="Avatar">';
		//document.getElementById("Avatar " + index + " info").innerHTML = value.avatarID[0].name;
	});
		///CONTINUE ERERE
});
</script>


  <!-- Custom styles for this template -->
  <link href="avatar.css" rel="stylesheet">
</head>

<script type="text/JavaScript">
function returnBack(){
	window.location = "http://35.235.118.188/avatar/avatar.php";
}

function searchAvatar()
{
	var check1 = prompt("What avatar would you like to search for?");
	var test2;
	var index = 0;
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/searchAvatar/" + check1,
		dataType: 'json',
		async: false,
		success: function(result){
			test2 = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			test2 = Json.parse(JSON.stringify(result));
		}
	});
	document.getElementById("Main Panel").innerHTML = '<div id="Avatar ' + index + '" class="tabAvatar"><img src="/img/fox.jpg" class="img-circle" height="200" width="200" alt="Avatar"></div>';
	//document.getElementById("Main Panel").innerHTML = '<div id="Avatar ' + index + '" class="tab"><button type="button">Create Avatar</button></div>';
	document.getElementById("Main Panel").innerHTML += '<div id="Avatar ' + index + ' info" class="tabcontentAvatar"></div>';
	document.getElementById("Avatar " + index + " info").innerHTML = test2.name + '<br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br>Level: ' + test2.level +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Health: ' + test2.health +' <br><br><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br>Dexterity: ' + test2.dexterity +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Intellect: ' + test2.intellect +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Agility: ' + test2.agility +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Strength: ' + test2.strength +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Stamina: ' + test2.stamina +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><br><button type="button" onclick="returnBack()">Back</button>';
}

function checkAvatar(getAvatar)
//function checkAvatar(index)
{
	var index = 0;
	var test2;
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/searchAvatar/" + getAvatar,
		//url: "http://35.235.118.188:3000/avatar/" + $("#userUID").val(),
		dataType: 'json',
		async: false,
		success: function(result){
			test2 = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			test2 = Json.parse(JSON.stringify(result));
		}
	});
	var items;

	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/rewards/" + $("#userUID").val(),
		dataType: 'json',
		async: false,
//		data: {userName: $("#userUID").val()},
		success: function(result){
			items = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			alert(JSON.stringify(result));
		}
	});

	var wearing;
	$.ajax({
		type: "POST",
		crossDomain: true,
		url: "http://35.235.118.188:3000/getWear",
		dataType: 'json',
		async: false,
		data: {name: test2.name},
		success: function(result){
			wearing = JSON.parse(JSON.stringify(result));
		},
		error: function(result){}
	});
	document.getElementById("Main Panel").innerHTML = '<div id="Avatar ' + index + '" class="tabAvatar"><img src="/img/fox.jpg" class="img-circle" height="200" width="200" alt="Avatar"></div>';
	//document.getElementById("Main Panel").innerHTML = '<div id="Avatar ' + index + '" class="tab"><button type="button">Create Avatar</button></div>';
	document.getElementById("Main Panel").innerHTML += '<div id="Avatar ' + index + ' info" class="tabcontentAvatar"></div>';
	document.getElementById("Avatar " + index + " info").innerHTML = test2.name + '<br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br>Level: ' + test2.level +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Health: ' + test2.health +' <br><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br>Dexterity: ' + test2.dexterity +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Intellect: ' + test2.intellect +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Agility: ' + test2.agility +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Strength: ' + test2.strength +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += 'Stamina: ' + test2.stamina +' <br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><br><button type="button" onclick="returnBack()">Back</button>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><br><br>Equiptment:<br>';
	if (wearing == null){
		$.each(items, function(itemIndex, value) {
			if (value.redeemedDate == null){
				if (value.reward[0].type == "Item") document.getElementById("Avatar " + index + " info").innerHTML += '<br><button type="button" onclick="wearItem(\'' + test2.name + '\' , \'' + value._id + '\')">' + value.reward[0].name + '</button>';
			}
		})
	} else {
		document.getElementById("Avatar " + index + " info").innerHTML += '<br>' + wearing.name + '<br>';
		document.getElementById("Avatar " + index + " info").innerHTML += '<br> +' + wearing.health + ' health<br>';
		document.getElementById("Avatar " + index + " info").innerHTML += '<br> +' + wearing.dexterity + ' dexterity<br>';
		document.getElementById("Avatar " + index + " info").innerHTML += '<br> +' + wearing.intellect + ' intellect<br>';
		document.getElementById("Avatar " + index + " info").innerHTML += '<br> +' + wearing.agility + ' agility<br>';
		document.getElementById("Avatar " + index + " info").innerHTML += '<br> +' + wearing.strength + ' strength<br>';
		document.getElementById("Avatar " + index + " info").innerHTML += '<br> +' + wearing.stamina + ' stamina<br>';
		document.getElementById("Avatar " + index + " info").innerHTML += '<br><Button type="button" onclick="unequipItem(\'' + test2.name + '\')">Unequip ' + wearing.name + '</button>';
	}
}

function unequipItem(avatarName){
	$.ajax({
		type: "POST",
		crossDomain: true,
		url: "http://35.235.118.188:3000/unwear",
		dataType: 'json',
		async: false,
		data: {name: avatarName},
		success: function(result){	
		},
		error: function(result){
			checkAvatar(avatarName);
		}
	  });

}

function wearItem(avatarName, itemID){
	$.ajax({
		type: "POST",
		crossDomain: true,
		url: "http://35.235.118.188:3000/wear",
		dataType: 'json',
		async: false,
		data: {user: avatarName, item: itemID},
		success: function(result){
			if (result == "Wearing item") alert("Item is currently being worn");
			items = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			checkAvatar(avatarName);
		}
	  });
}

function deleteAvatar(getAvatar)
{
	var check1 = prompt("To delete " + getAvatar + ", please input the email address that correspond to this account");
	if ($("#email").val() == check1){
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/deleteAvatar/" + getAvatar,
			dataType: 'json',
			async: false,
			success: function(result){
			},
			error: function(result){
				alert(getAvatar + " has been deleted");
				window.location = "http://35.235.118.188/avatar/avatar.php";
			}
		});
	} else {
		alert("Invalid Email");
	}

}

function defaultAvatar(getAvatar)
{
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/defaultAvatar/" + $("#userUID").val() + "/" + getAvatar,
		dataType: 'json',
		async: false,
		success: function(result){
			alert("Test");
		},
		error: function(result){
			alert(getAvatar + " has been set as default");
			window.location = "http://35.235.118.188/avatar/avatar.php";
		}
	});
}

function createAvatar(index)
{
	document.getElementById("Main Panel").innerHTML = '<div id="Avatar ' + index + '" class="tabAvatar"><img src="/img/fox.jpg" class="img-circle" height="200" width="200" alt="Avatar"></div>';
	//document.getElementById("Main Panel").innerHTML = '<div id="Avatar ' + index + '" class="tab"><button type="button">Create Avatar</button></div>';
	document.getElementById("Main Panel").innerHTML += '<div id="Avatar ' + index + ' info" class="tabcontentAvatar"></div>';
	document.getElementById("Avatar " + index + " info").innerHTML = '<input id="avatarName" type="text" value=""><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><button type="button" onclick="modifyValue(\'HairStyle\', \'Minus\', 1)">\<</button> Hair Style <button type="button" onclick="modifyValue(\'HairStyle\', \'Plus\', 1)">\></button><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><button type="button" onclick="modifyValue(\'HairColor\', \'Minus\', 1)">\<</button> Hair Color <button type="button" onclick="modifyValue(\'HairColor\', \'Plus\', 1)">\></button><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><button type="button" onclick="modifyValue(\'EyeColor\', \'Minus\', 1)">\<</button> Eye Color <button type="button" onclick="modifyValue(\'EyeColor\', \'Plus\', 1)">\></button><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><button type="button" onclick="modifyValue(\'FurColor\', \'Minus\', 1)">\<</button> Fur Color <button type="button" onclick="modifyValue(\'FurColor\', \'Plus\', 1)">\></button><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><button type="button" onclick="modifyValue(\'Animal\', \'Minus\', 1)">\<</button> Animal <button type="button" onclick="modifyValue(\'Animal\', \'Plus\', 1)">\></button><br>';
	document.getElementById("Avatar " + index + " info").innerHTML += '<br><button type="button" onclick="SubmitAvatar()">Submit Avatar</button>';

}

function modifyValue(field, operator, operand)
{
	if (operator == "Minus"){
		var newValue = (parseInt($("#" + field).val(), 10) - 1)%10;
		alert(newValue);
		document.getElementById(field).value = newValue;
	}
	else if (operator == "Plus"){
		var newValue = (parseInt($("#" + field).val(), 10) + 1)%10;
		alert(newValue);
		document.getElementById(field).value = newValue;
	}
}

function SubmitAvatar()
{
	var check1=confirm("Are you sure you want to make " + $("#avatarName").val()+"?");
	if (check1)
	{
		$.ajax({
			type: "POST",
			crossDomain: true,
			url: "http://35.235.118.188:3000/avatar/" + $("#avatarName").val() + "/" + $("#userUID").val(),
			dataType: 'json',
			async: false,
			data: { name: $("#avatarName").val(), hairStyle: $("#HairStyle").val(), hairColor: $("#HairColor").val(), furColor: $("#FurColor").val(), eyeColor: $("#EyeColor").val(), animal: $("#Animal").val() },
			success: function(result){
					var check2=confirm($("#avatarName").val() + " has been born!");
					window.location = "http://35.235.118.188/avatar/avatar.php";
			},
			error: function(result){
				console.log(result);
				if (result.responseText == "Avatar Exists"){
					alert("Avatar with this name already exists");
				}
			}
		});
	}
	else
	{
		return false;
	}
}


function openTab(evt, tabName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
  if(tabName == "My Redemption"){
	  var test2;
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/rewards/" + $("#userUID").val(),
		dataType: 'json',
		async: false,
//		data: {userName: $("#userUID").val()},
		success: function(result){
			test2 = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			alert(JSON.stringify(result));
		}
	  });
	alert(test2);
	$.each(test2, function(index, value) {
		alert( index + ": " + value.reward[0].name );
	
	  document.getElementById("Testing").innerHTML += 
		 '<div class="col-sm-4"><div class="panel panel-success"><div class="panel-heading">'+ value.reward[0].name +'</div><div class="panel-body"><img src="../images/diamondpile3.png" height="500" width="500" class="img-responsive" alt="Image"></div><div class="panel-footer">' + value.reward[0].coin + '<button type="button" onclick="confirmRefund(\''+ value.reward[0].name +'\', \'' + value._id + '\')"class="btn btn-success" style="margin-left: auto; display: block;">Refund</button></div></div></div>';
	});
  }
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
      <li><a href="/guild/guild.php">Guild</a></li>
      <li><a href="/avatar/avatar.php" class="active">Avatar</a></li>
<!--      <li><a href="/gacha_page/gacha.html">Gacha</a></li> -->

      <li style="position: absolute; right: 150px;"><a href="/account/account.php">My Account</a></li>
      <li style="position: absolute; right: 80px;"><a href="/">Logout</a></li>
    </ul>
  </div>
</nav>
<div id="Main Panel" class="container">
  <button type="button" onclick="searchAvatar()">Search Avatar</button>
   <pre>
    <div id="Avatar 0" class="tab">
     <button type="button" onclick="createAvatar(0)">Create Avatar</button>
    </div>
    <div id="Avatar 0 info" class="tabcontent">
     User
    </div>
   </pre>
   <pre>
    <div id="Avatar 1" class="tab">
     <button type="button" onclick="createAvatar(1)">Create Avatar</button>
    </div>
    <div id="Avatar 1 info" class="tabcontent">
     User
    </div>
   </pre>
   <pre>
    <div id="Avatar 2" class="tab">
     <button type="button" onclick="createAvatar(2)">Create Avatar</button>
    </div>
    <div id="Avatar 2 info" class="tabcontent">
     User
    </div>
   </pre>
   <pre>
    <div id="Avatar 3" class="tab">
     <button type="button" onclick="createAvatar(3)">Create Avatar</button>
    </div>
    <div id="Avatar 3 info" class="tabcontent">
     User
    </div>
   </pre>
   <pre>
    <div id="Avatar 4" class="tab">
     <button type="button" onclick="createAvatar(4)">Create Avatar</button>
    </div>
    <div id="Avatar 4 info" class="tabcontent">
     User
    </div>
   </pre>
 </div>

</body>
</html>
