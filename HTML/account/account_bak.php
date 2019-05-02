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
	document.cookie = "userUID=" + test.userUID;
	document.cookie = "firstName=" + test.firstName;
        document.cookie = "lastName=" + test.lastName;
	document.cookie = "email=" + test.email;
	document.cookie = "phone=" + test.phone;
	document.cookie = "dateOfBirth=" + test.dateOfBirth;
	document.cookie = "coins=" + test.coins;
	document.cookie = "tier=" + test.tier;
	document.cookie = "diamonds=" + test.diamonds;
	//document.getElementById("UserID").innerHTML = test.userUID;
	
	});
	</script>

  <!-- Custom styles for this template -->
  <link href="account.css" rel="stylesheet">
</head>
<body>

<script type="text/JavaScript">
function editAccount(){
	window.location = "http://35.235.118.188/edit/account.php";
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

      <li style="position: absolute; right: 150px;"><a href="/account/account.php" class="active">My Account</a></li>
      <li style="position: absolute; right: 80px;"><a href="/">Logout</a></li>
    </ul>
  </div>
</nav>
<div class="container">
  <h3>My Account</h3>
</div>


<div class="container">
  <div class="top">
   <!-- <li style="position: absolute; right: 250px;"><a href="../edit/account.php">Edit Account</a></li> -->
    <h2>Account Info</h2>
    <button type="button" onclick="editAccount()">Edit Account</button>
  </div>
  <div class="row">

    <div class="shadow">

      <div class="col-sm-12">
        <div class="col-sm-2">

        </div>
        <div class="col-sm-8">
          <h4>User UID</h4>
	  <p id="UserID"><?php echo $_COOKIE['userUID'];?></p>
        </div>
        <div class="col-sm-2">
          <!--<img src="../im/editicon.png" alt="group" width="40px" />-->
          <br>
        </div>
      </div>
      <div class="clearfix"></div>
      <hr />

      <div class="col-sm-12">
        <div class="col-sm-2">

        </div>
        <div class="col-sm-8">
          <h4>Name</h4>
	  <p><?php echo $_COOKIE['username'];?></p>
        </div>
        <div class="col-sm-2">
          <br>
        </div>
      </div>
      <div class="clearfix"></div>
      <hr />



      <div class="col-sm-12">
        <div class="col-sm-2">

        </div>
        <div class="col-sm-8">
          <h4>Name</h4>
	  <p><?php echo $_COOKIE['firstName'];?> <?php echo $_COOKIE['lastName'];?></p>
        </div>
        <div class="col-sm-2">
          <br>
        </div>
      </div>
      <div class="clearfix"></div>
      <hr />

      <div class="col-sm-12">
        <div class="col-sm-2">

        </div>
        <div class="col-sm-8">
          <h4>E-mail</h4>
          <p><?php echo $_COOKIE['email'];?></p>
        </div>
        <div class="col-sm-2">
          <br>
        </div>
      </div>
      <div class="clearfix"></div>
      <hr />

      <div class="col-sm-12">
      <div class="col-sm-2">
    </div>
      <div class="col-sm-8">
        <h4>Phone</h4>
	<p><?php echo $_COOKIE['phone'];?></p>
      </div>
      <div class="col-sm-2">
        <br>
      </div>
    </div>
    <div class="clearfix"></div>
    <hr />

    <div class="col-sm-12">
      <div class="col-sm-2">

      </div>
      <div class="col-sm-8">
        <h4>Date of Birth</h4>
	<p><?php echo $_COOKIE['dateOfBirth'];?></p>
      </div>
      <div class="col-sm-2">
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
