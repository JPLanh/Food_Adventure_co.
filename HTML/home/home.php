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
  <input type="hidden" id="email" value="<?php echo $_COOKIE['email'];?>"/>
  <input type="hidden" id="phone" value="<?php echo $_COOKIE['phone'];?>"/>
  <input type="hidden" id="dateOfBirth" value="<?php echo $_COOKIE['dateOfBirth'];?>"/>
  <input type="hidden" id="coins" value ="<?php echo $_COOKIE['coins'];?>"/>
  <input type="hidden" id="tier" value="<?php echo $_COOKIE['tier'];?>"/>
  <input type="hidden" id="diamonds" value="<?php echo $_COOKIE['diamonds'];?>"/>
  <input type="hidden" id="firstName" value="<?php echo $_COOKIE['firstName'];?>"/>
  <input type="hidden" id="lastName" value="<?php echo $_COOKIE['lastName'];?>"/>
  <input type="hidden" id="username" value="<?php echo $_COOKIE['username'];?>">
  <input type="hidden" id="password" value="<?php echo $_COOKIE['password'];?>"/>

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
	document.getElementById("Welcome").innerHTML = "Welcome, " + test.firstName + " " + test.lastName;
	document.getElementById("User").innerHTML = test.userName + " #" + test.tier;
	document.getElementById("Coin").innerHTML = test.coins;
	document.getElementById("Diamond").innerHTML = test.diamonds;
//	document.getElementById("tier").value = test.tier;
//	document.getElementById("diamonds").value = test.diamonds;
	document.getElementById("username").value = test.userName;

	});
	</script>

  <!-- Custom styles for this template -->
  <link href="style.css" rel="stylesheet">

</head>
<body>

  <script type="text/JavaScript">

  function redeemCoupon()
  {
  	var check1=confirm("Are you sure you want to redeem this coupon?");
  	if (check1)
  	{
  		var check2=confirm("Thank you! Enjoy your food!");
  	}
  	else
  	{
  		return false;
  	}
  }
  function gotoMessage(){
	window.location = "http://35.235.118.188/message/messages.php";
  }
  function searchAvatar(){
	  var check1=prompt("Who would you like to search up?");
	  $(document).ready(function(){
	  	document.cookie = "searchAvatar=" + check1;
		alert(document.cookie);
	  });
	  window.location = "http://35.235.118.188/avatar/avatar.php";
  }
  function viewBattle(){
	  $(document).ready(function(){
	        var test;
		$.ajax({
			type: "GET",
			crossDomain: true,
			url: "http://35.235.118.188:3000/battles/" + $("#userUID").val(),
			dataType: 'json',
			async: false,
			//data: {userName: $("#username").val(), password: $("#password").val()},
			success: function(result){
				test = JSON.parse(JSON.stringify(result));						
			},
			error: function(result){
				alert("Notice: Your avatars has not yet participated in any battle");
			}
		});
		  document.getElementById("mainFrame").innerHTML = '<div class="row"><div class="col-sm-12"><div class="panel panel-default text-left"><div class="panel-body"><p contenteditable="true"></p><h4>Battles</h4><ul class="list-group">';
		test.forEach(function(eachBattle){
			document.getElementById("mainFrame").innerHTML += '<li class="list-group-item">';
			  if (eachBattle[0].result == true) document.getElementById("mainFrame").innerHTML += "Victory";
			  else if (eachBattle[0].result == false) document.getElementById("mainFrame").innerHTML += "Defeat";
			  document.getElementById("mainFrame").innerHTML += ': Battle ' + eachBattle[0].room[0].room + ', Avatar: ' + eachBattle[0].participant[0].name + ', Damage: ' + eachBattle[0].damage + '</li>';
                /*<li
                  class="list-group-item">Sura: Free Appetizer
                  <div><img src="/img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">9,000<button type="button" onclick="redeemCoupon()"class="btn btn-success" style="margin-left: auto; display: block;">Redeem</button></div>
                </li>
                <li
                  class="list-group-item">LooseLeaf: 10% Off Regular Drink
                  <div><img src="/img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">5,500<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Redeem</button></div>
		  </li>*/
		  });
              document.getElementById("mainFrame").innerHTML += '</ul></div></div></div>';

	  });;
  }

  </script>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Food Adventure Co.</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/home/home.php" class="active">Home</a></li>
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
<h1 id="Welcome">Welcome, <?php echo $_COOKIE['firstName'];?>  <?php echo $_COOKIE['lastName'];?></h1>
</div>

<div class="container text-center">
  <div class="row">
    <div class="col-sm-3 well">

      <div class="well">
      <h3 id="User"><?php echo $_COOKIE['username'];?> # <?php echo $_COOKIE['tier'];?></h3>
        <img src="/img/Avatar_Penguin.png" class="img-circle" height="200" width="200" alt="Avatar">
      </div>

      <div class="well">
        <h4>
          <img src="/img/coin.png" class="img-circle" height="50" width="50" alt="Coin Currency">
	  <p id="Coin"><?php echo $_COOKIE['coins']; ?></p>
        </h4>

        <h4>
          <img src="/img/diamond.jpg" class="img-circle" height="50" width="70" alt="Diamond Currency">
	  <p id="Diamond"><?php echo $_COOKIE['diamonds']; ?></p>
        </h4>



<!--        <div class="alert alert-success fade in">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
          <p><strong>Limited Time!</strong></p>
          Premium package is 50% off!
        </div> -->
      </div>

      <ul class="nav flex-column">
	<li class="nav-item">
<!--	  <button type="button" onclick="gotoMessage()">Messages</button> -->
	  <a class="nav-link" href="../message/messages.php">Messages </a>
<!--	  <a class="nav-link" href="../message/messages.php">View Other Avatar </a> -->
	  <a class="nav-link" onclick="viewBattle()">Battle History </a>
<!--<span class="badge">3</span></a>-->
        </li>
 <!--       <li class="nav-item">
          <a class="nav-link" href="../friend_page/friends.html">Friends <span class="badge">1</span></a>
        </li>-->
      </ul>

    </div>
    <div id="mainFrame" class="col-sm-7">
<!--
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default text-left">
            <div class="panel-body">
              <p contenteditable="true">
                <input type="image" src="/img/location_picker.png" width="15" height="15" />
                Location: Long Beach, CA
              </p>
              <br>
              <h4>Top Coupons Near You!</h4>
              <ul class="list-group">
                <li
                  class="list-group-item">Pizza x Two: Free Drink with Pizza
                  <div><img src="/img//coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">8,000<button type="button" onclick="redeemCoupon()"class="btn btn-success" style="margin-left: auto; display: block;">Redeem</button></div>
                </li>
                <li
                  class="list-group-item">Sura: Free Appetizer
                  <div><img src="/img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">9,000<button type="button" onclick="redeemCoupon()"class="btn btn-success" style="margin-left: auto; display: block;">Redeem</button></div>
                </li>
                <li
                  class="list-group-item">LooseLeaf: 10% Off Regular Drink
                  <div><img src="/img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">5,500<button type="button" onclick="confirmPurchase()"class="btn btn-success" style="margin-left: auto; display: block;">Redeem</button></div>
                </li>
              </ul>
              <a href="../shop/shop.php">View more coupons</a>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default text-left">
            <div class="panel-body">
              <p contenteditable="true">
                Earn more Coins!
                <br>
                4 users ready to battle near you!
              </p>
              <button class="button button1" style="margin-left: auto; display: block;">Find a Battle</button>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default text-left">
            <div class="panel-body">
              <p contenteditable="true">
                New! Sporty Collection Gacha
              </p>
              <button class="button button1" style="margin-left: auto; display: block;">Play <img src="../img/coin.png" class="img-circle" height="30" width="30" alt="Coin Currency">500</button>
            </div>
          </div>
        </div>
      </div> -->
</body>
</html>
