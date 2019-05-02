<!DOCTYPE html>
<html lang="en">
<head>
  <title>Food Adventure Co.</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"> </script>


<script type = "text/javascript">
         <!--
            function ReadCookie() {
               var allcookies = document.cookie;
               document.write ("All Cookies : " + allcookies );
               
               // Get all the cookies pairs in an array
               cookiearray = allcookies.split(';');
               
               // Now take key value pair out of this array
               for(var i=0; i<cookiearray.length; i++) {
                  name = cookiearray[i].split('=')[0];
                  value = cookiearray[i].split('=')[1];
                  document.write ("Key is : " + name + " and Value is : " + value);
               }
            }
         //-->
      </script>   


  <!-- Custom styles for this template -->
  <link href="index.css" rel="stylesheet">
</head>
<body>
  <div class="login-page">
    <div class="form">
      <h3>Login</h3>
	<script>
		$(document).ready(function(){
			var user, pass;
			$("#submit").click(function(){
				var test;
				$.ajax({
					type: "GET",
					crossDomain: true,
					url: "http://35.235.118.188:3000/htmlUsers",
					dataType: 'json',
					async: false,
					data: {userName: $("#userName").val(), password: $("#password").val()},
					success: function(result){
						test = JSON.parse(JSON.stringify(result));						
					},
					error: function(result){
						alert("Invalid credential");
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
				window.location = "http://35.235.118.188/home/home.php";
			});
		});
	</script>
	<p id="login"> 
		<input type="text" id="userName" placeholder="username"/>
	        <input type="password" id="password" placeholder="password"/>
		<input type="button" id="submit" value="submit">
	        <p class="message">Not registered? <a href="../register.php">Register an account</a></p>
	</p>
    </div>
  </div>
</body>
</html>
