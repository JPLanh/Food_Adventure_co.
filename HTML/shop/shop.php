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
	document.getElementById("playerCurrency").innerHTML = "Coins: " + test.coins;
	document.cookie = "userUID=" + test.userUID;
	document.cookie = "firstName=" + test.firstName;
        document.cookie = "lastName=" + test.lastName;
	document.cookie = "email=" + test.email;
	document.cookie = "phone=" + test.phone;
	document.cookie = "dateOfBirth=" + test.dateOfBirth;
	document.cookie = "coins=" + test.coins;
	document.cookie = "tier=" + test.tier;
	document.cookie = "diamonds=" + test.diamonds;
	});
	</script>
  <!-- Custom styles for this template -->
  <link href="shop.css" rel="stylesheet">
</head>

<script type="text/JavaScript">

function confirmPurchase(getString)
{
//	alert(encodeURI(getString, "UTF-8"));
	var check1=prompt("You are now purchasing " + getString +  ", Please enter the email of your account");
	{
//		alert(urldecode(getString));
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/rewards/" + check1 + "/" + $("#userUID").val() + "/" + encodeURI(getString),//getString.replace(/ /g, "+"),
			dataType: 'json',
			async: false,
			data: {userName: $("#username").val(), password: $("#password").val()},
			success: function(result){
				var check2=confirm("Thank you for your purchase!");
			},
			error: function(result){
				var check2=confirm("Invalid Email");
			}
		});
	}
  refreshCoin();	
}

function confirmRefund(itemName, itemUID)
{
	var check1=prompt("You are now refunding " + itemName +  ", Please enter the email of your account");
	{
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/refunds/" + check1 + "/" + $("#userUID").val() + "/" + itemUID,
			dataType: 'json',
			async: false,
			data: {userName: $("#username").val(), password: $("#password").val()},
			success: function(result){
				var check2=confirm("We have refunded " + result.reward[0].coin + " to your account");
			},
			error: function(result){
				var check2=confirm("Invalid Email");
			}
		});

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
		refreshCoin();
	  document.getElementById("My Redemption").innerHTML = "";
	  $.each(test2, function(index, value) {
		if (value.redeemedDate == null)  document.getElementById("My Redemption").innerHTML += '<div class="col-sm-4"><div class="panel panel-primary"><div class="panel-heading">' + value.reward[0].name + '</div>' +
			'<div class="panel-body outer"><img src="../img/' + value.reward[0].name.replace(/ /g, "").toLowerCase() + '.png" class="img-responsive" style="width:100%" alt="Image">' +
			'<div class="overlay"><p class="text">Strength: +' + value.reward[0].strength + '<br>Agility: +' + value.reward[0].agility +  '<br>Dexterity: +' + value.reward[0].dexterity + '<br>Intellect: +' + value.reward[0].intellect + '<br>Stamina: +' + value.reward[0].stamina + '<br>Health: +' + value.reward[0].health +' </p></div></div>' +
			'<div class="panel-footer"><img src="../img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">' + value.reward[0].coin + 
			'<button type="button" onclick="confirmRefund(\'' + value.reward[0].name + '\', \'' + value._id +  '\')"class="btn btn-success" style="margin-left: auto; display: block;">Refund</button></div></div></div>';
	
	});
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

  refreshCoin();	
  if(tabName == "Items"){
 	var test2;
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/rewards",
		dataType: 'json',
		async: false,
		success: function(result){
			test2 = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			alert(JSON.stringify(result));
		}
	});
	  document.getElementById("Items").innerHTML = ""; 
	$.each(test2, function(index, value) {
		if (value.type == "Item") document.getElementById("Items").innerHTML += '<div class="col-sm-4"><div class="panel panel-primary"><div class="panel-heading">' + value.name + '</div>' +
			'<div class="panel-body outer"><img src="../img/' + value.name.replace(/ /g, "").toLowerCase() + '.png" class="img-responsive" style="width:100%" alt="Image">' +
			'<div class="overlay"><p class="text">Strength: +' + value.strength + '<br>Agility: +' + value.agility +  '<br>Dexterity: +' + value.dexterity + '<br>Intellect: +' + value.intellect + '<br>Stamina: +' + value.stamina + '<br>Health: +' + value.health +' </p></div></div>' +
			'<div class="panel-footer"><img src="../img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">' + value.coin + 
			'<button type="button" onclick="confirmPurchase(\'' + value.name + '\')"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div></div></div>';
	});
  }

  if(tabName == "Coupons"){
 	var test2;
	$.ajax({
		type: "GET",
		crossDomain: true,
		url: "http://35.235.118.188:3000/rewards",
		dataType: 'json',
		async: false,
		success: function(result){
			test2 = JSON.parse(JSON.stringify(result));						
		},
		error: function(result){
			alert(JSON.stringify(result));
		}
	});
	  document.getElementById("Coupons").innerHTML = ""; 
	$.each(test2, function(index, value) {
		if (value.type == "Coupon") {
			document.getElementById("Coupons").innerHTML += '<div class="col-sm-4"><div class="panel panel-primary"><div class="panel-heading">' + value.name + '</div>' +
			'<div class="panel-body outer"><img src="../img/' +encodeURI( value.name.replace(/ /g, "").toLowerCase()) + '.png" class="img-responsive" style="width:100%" alt="Image">' +
			//'<div class="overlay"><p class="text">Strength: +' + value.strength + '<br>Agility: +' + value.agility +  '<br>Dexterity: +' + value.dexterity + '<br>Intellect: +' + value.intellect + '<br>Stamina: +' + value.stamina + '<br>Health: +' + value.health +' </p></div></div>' +
			'<div class="panel-footer"><img src="../img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">' + value.coin + 
			'<button type="button" onclick="confirmPurchase(\'' + value.name + '\')"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div></div></div>';
	}	
    });
  }

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
	  document.getElementById("My Redemption").innerHTML = "";
	  $.each(test2, function(index, value) {
		if (value.redeemedDate == null)  document.getElementById("My Redemption").innerHTML += '<div class="col-sm-4"><div class="panel panel-primary"><div class="panel-heading">' + value.reward[0].name + '</div>' +
			'<div class="panel-body outer"><img src="../img/' + encodeURI( value.reward[0].name.replace(/ /g, "").toLowerCase())/* value.reward[0].name.replace(/ /g, "").toLowerCase()*/ + '.png" class="img-responsive" style="width:100%" alt="Image">' +
			'<div class="overlay"><p class="text">Strength: +' + value.reward[0].strength + '<br>Agility: +' + value.reward[0].agility +  '<br>Dexterity: +' + value.reward[0].dexterity + '<br>Intellect: +' + value.reward[0].intellect + '<br>Stamina: +' + value.reward[0].stamina + '<br>Health: +' + value.reward[0].health +' </p></div></div>' +
			'<div class="panel-footer"><img src="../img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">' + value.reward[0].coin + 
			'<button type="button" onclick="confirmRefund(\'' + value.reward[0].name + '\', \'' + value._id +  '\')"class="btn btn-success" style="margin-left: auto; display: block;">Refund</button></div></div></div>';
	
	});
  }
}

// Get the element with id="defaultOpen" and click on it

</script>


<body>

<script type="Text/JavaScript">

function refreshCoin(){
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
	document.getElementById("playerCurrency").innerHTML = "Coins: " + test.coins;
	});
}
</script>
<!--  <div class="jumbotron">
  <div class="container text-center">
    <h1>Online Store</h1>
    <p>New Summer Accessories!</p>
  </div>
  </div> -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Food Adventure Co.</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/home/home.php">Home</a></li>
      <li><a href="/shop/shop.php" class="active">Shop</a></li>
      <li><a href="/guild/guild.php">Guild</a></li>
      <li><a href="/avatar/avatar.php">Avatar</a></li>
<!--      <li><a href="/gacha_page/gacha.html">Gacha</a></li> -->

      <li style="position: absolute; right: 150px;"><a href="/account/account.php">My Account</a></li>
      <li style="position: absolute; right: 80px;"><a href="/">Logout</a></li>
    </ul>
  </div>
</nav>
 
  <div class="tab">
  <button class="tablinks" onclick="openTab(event, 'Items')" id="defaultOpen">Items</button>
<!--  <button class="tablinks" onclick="openTab(event, 'Currency')">Currency</button> -->
<!--  <button class="tablinks" onclick="openTab(event, 'Stat')">Stat</button> -->
  <button class="tablinks" onclick="openTab(event, 'Coupons')">Coupons</button>
  <button class="tablinks" onclick="openTab(event, 'My Redemption')">My Redemption</button>
	<br><br><br><br>
  <p id="playerCurrency">test</p>
</div>

<script type="text/javascript">
$(document).ready(function() {
        $("#defaultOpen").click();
    });
</script>

<div id="Items" class="tabcontent">
<!--
  <h3>Items</h3>

  <div class="dropdown">
  <button class="btn btn-default dropdown-toggle" style="float: right;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Sort By
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">Newest</a></li>
    <li><a href="#">Price: Low to High</a></li>
    <li><a href="#">Price: High to Low</a></li>
    <li><a href="#">Coins Only</a></li>
    <li><a href="#">Diamonds Only</a></li>
  </ul>
</div>


  <div class="container">
    <div class="row" id="row1">


      <div class="col-sm-4">
	<div class="panel panel-primary">
          <div class="panel-heading">Blue Hoodie</div>
          <div class="panel-body outer">
            <img src="../images/redvneck.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Strength: +1
                  <br>
                  Defense: +1
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">20<button type="button" onclick="confirmPurchase('Blue Hoodie')"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Straw Sun Hat</div>
          <div class="panel-body outer">
            <img src="../images/sunhat.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Defense: +2
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">25<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Blue Jeans</div>
          <div class="panel-body outer">
            <img src="../images/jeans.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Attack: +1
                  <br>
                  Defense: +3
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">20<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
    </div>
  </div><br><br>

  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Blue Hoodie</div>
          <div class="panel-body outer">
            <img src="../images/bluehoodie.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Strength: +3
                  <br>
                  Defense: +3
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">30<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Red Halter Dress</div>
          <div class="panel-body outer">
            <img src="../images/reddress.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Strength: +2
                  <br>
                  Defense: +5
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/diamond.jpg" class="img-circle" height="30" width="40" alt="Diamond Currency">5<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Pink Sneakers</div>
          <div class="panel-body outer">
            <img src="../images/sneakers.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Accuracy: +3
                  <br>
                  Defense: +1
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/diamond.jpg" class="img-circle" height="30" width="40" alt="Diamond Currency">10<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
    </div>
  </div><br><br>

  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Summer Dress</div>
          <div class="panel-body outer">
            <img src="../images/summerdress.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Strength: +2
                  <br>
                  Defense: +2
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">100<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Wizard Hat</div>
          <div class="panel-body outer">
            <img src="../images/wizardhat.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Accuracy: +5
                  <br>
                  Strength: +4
                  <br>
                  Defense: +5
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/diamond.jpg" class="img-circle" height="30" width="40" alt="Diamond Currency">25<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="panel panel-primary">
          <div class="panel-heading">Teal Collared Shirt</div>
          <div class="panel-body outer">
            <img src="../images/tealcollar.png" class="img-responsive" style="width:100%" alt="Image">
            <div class="overlay">
                <p class="text">
                  Strength: +1
                  <br>
                  Defense: +1
                </p>
            </div>
          </div>
          <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="40" alt="Coin Currency">60<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
        </div>
      </div>
    </div>
  </div><br><br> -->
</div>
<!--
<div id="Currency" class="tabcontent">
  <h3>Currency</h3>
  <div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: 10,000 Coins</div>
        <div class="panel-body">
          <img src="../img/moneybag.png" height="250" width="250" class="img-responsive" alt="Image">
        </div>
        <div class="panel-footer">
          <img src="../img/50off.png" class="img-circle" height="50" width="50" alt="Avatar">
          <strike>$99.99</strike>
          <font color="green">$49.99</font>
          <button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: 100 Diamonds</div>
        <div class="panel-body"><img src="../img/diamondpile3.png" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer">$99.99<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: Surprise Gift</div>
        <div class="panel-body"><img src="../img/premiumpackage.png" height="250" width="250" class="img-responsive" alt="Image"></div>
        <div class="panel-footer">$89.99<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
  </div>
</div><br>
</div>-->

<div id="My Redemption" class="tabcontent">
<!--  <h3>My Redemption</h3>
  <div class="dropdown">
  <button class="btn btn-default dropdown-toggle" style="float: right;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Sort By
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">Newest</a></li>
    <li><a href="#">Price: Low to High</a></li>
    <li><a href="#">Price: High to Low</a></li>
    <li><a href="#">Coins Only</a></li>
    <li><a href="#">Diamonds Only</a></li>
  </ul>
</div>


  <div class="container">
    <div class="row" id="row1">
	<p id="Testing"> </p>
   </div>
  </div><br><br> -->
</div>


<!--
<div id="Stat" class="tabcontent">
  <h3>Stat</h3>

  <div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: Increase all Stats by 50% (1 hour)</div>
        <div class="panel-body"><img src="../img/battleicon2.png" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../img/diamond.jpg" class="img-circle" height="30" width="45" alt="Diamond Currency">1,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: Increase Gacha Luck by 50% (1 hour)</div>
        <div class="panel-body"><img src="../img/battleicon2.png" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../img/diamond.jpg" class="img-circle" height="30" width="45" alt="Diamond Currency">1,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: Increase Loot Luck by 50% (1 hour)</div>
        <div class="panel-body"><img src="../img/battleicon2.png" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../img/diamond.jpg" class="img-circle" height="30" width="45" alt="Diamond Currency">1,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
  </div>
</div><br>

<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">+5 Defense (2 hours)</div>
        <div class="panel-body"><img src="../img/battleicon2.png" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../img/diamond.jpg" class="img-circle" height="30" width="45" alt="Diamond Currency">50<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">+5 Attack (2 hours)</div>
        <div class="panel-body"><img src="../img/battleicon2.png" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../img/diamond.jpg" class="img-circle" height="30" width="45" alt="Diamond Currency">50<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">+5 Accuracy (2 hours)</div>
        <div class="panel-body"><img src="../img/battleicon2.png" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../img/diamond.jpg" class="img-circle" height="30" width="45" alt="Diamond Currency">50<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
  </div>
</div><br><br>
</div>

-->
<div id="Coupons" class="tabcontent"> 
<!--  <h3>Coupons</h3>
  <p contenteditable="true">
    <input type="image" src="/images/location_picker.png" width="15" height="15" />
    Location: Long Beach, CA
  </p>

  <div class="dropdown">
  <button class="btn btn-default dropdown-toggle" style="float: right;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Sort By
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">Recommended</a></li>
    <li><a href="#">Price: Low to High</a></li>
    <li><a href="#">Price: High to Low</a></li>
    <li><a href="#">Coins Only</a></li>
    <li><a href="#">Diamonds Only</a></li>
  </ul>
</div>

  <div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: Free Entree</div>
        <div class="panel-body"><img src="../images/coupon.jpg" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">20,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: 50% OFf</div>
        <div class="panel-body"><img src="../images/coupon.jpg" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">22,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">LIMITED TIME: BOGO</div>
        <div class="panel-body"><img src="../images/coupon.jpg" height="500" width="500" class="img-responsive" alt="Image"></div>
        <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">15,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
  </div>
</div><br>

<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Pizza x Two: Free Drink with Pizza</div>
        <div class="panel-body"><img src="../images/coupon.jpg" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">8,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Sura: Free Appetizer</div>
        <div class="panel-body"><img src="../images/coupon.jpg" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">9,000<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">LooseLeaf: 10% Off Regular Drink</div>
        <div class="panel-body"><img src="../images/coupon.jpg" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer"><img src="../images/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">5,500<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Purchase</button></div>
      </div>
    </div>
  </div>
</div><br><br> -->


</div>


</body>
</html>
