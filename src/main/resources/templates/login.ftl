<!DOCTYPE html>
<html>
<head>
    <style type="text/css" media="screen">
                @import "../resources/css/bootstrap.min.css";
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
    <script src="../resources/js/bootstrap.js" type="text/javascript">
     </script>
    <title>Spring Security Example</title>
</head>
<body>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<#--<div>
    <#if RequestParameters.error != null>
    Invalid username and password.
    </#if>
</div>
<div>
    <#if RequestParameters.logout>
    You have been logged out.
    </#if>
</div>-->
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
<div>
    <a href="/signup">Register</a>
</div>
</body>
</html>