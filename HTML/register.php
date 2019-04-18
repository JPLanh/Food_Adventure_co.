<!DOCTYPE html>
<html lang="en">
<head>
  <title>Food Adventure Co.</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

  <!-- Custom styles for this template -->
  <link href="register.css" rel="stylesheet">
</head>
<body>

  <div class="login-page">
    <div class="form">
     <!-- <form method="POST" action="http://35.235.118.188:3000/htmlUsers" class="login-form"> -->
	<h3>Sign Up</h3>
	<script>
		$(document).ready(function(){
			$("#submit").click(function(){
				if ( $("#password").val() == $("#confirm").val() ){
					var test;
					$.ajax({
						type: "POST",
						crossDomain: true,
						url: "http://35.235.118.188:3000/htmlUsers",
						dataType: 'json',
						async: false,
						data: {"userUID": $("#UserUID").val(), "userName": $("#userName").val(), "password": $("#password").val()},
						success: function(result){
							test = JSON.parse(JSON.stringify(result));
						},
						error: function(result){
							if (result.responseText == "User already exist"){
								alert("User Already Exist");
							}
							alert("Failed");
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
				document.cookie = "username=" + test.userName;
				document.cookie = "password=" + $("#password").val();
				window.location = "http://35.235.118.188";

				} else {
					alert("Confirm password does not match");
				}
			});
		});
	</script>
        <input type="text" id="UserUID" placeholder="userID"/>
        <input type="text" id="userName" placeholder="username"/>
        <input type="password" id="password" placeholder="password"/>
        <input type="password" id="confirm" placeholder="confirm password"/>
        <input type="button" id="submit" value="submit">
        <p class="message">Already have an account? <a href="../index.php">Login</a></p>
     <!-- </form> -->
    </div>
  </div>

</body>
</html>
