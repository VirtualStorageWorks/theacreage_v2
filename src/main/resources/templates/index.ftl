<!DOCTYPE HTML>
<html>
<head>
    <style type="text/css" media="screen">
        @import "/css/bootstrap.min.css";
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
    <script src="./js/bootstrap.js" type="text/javascript">
    </script>
    <title>Account Management</title>


</head>
<body>
<script src="/js/bootstrap.min.js"></script>
<nav role="navigation" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">theAcreage.net</a>
        </div>
        <!-- Collection of nav links and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="/account">Account</a></li>
                <li><a href="/messages">Messages</a></li>
                <li><a href="/classifieds">Classifieds</a></li>
                <li><a href="/businesslistings">Directory</a></li>
                <li><a href="/about">About</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <#if CurrentUser??>
                    <h3>${CurrentUser.username}</h3>
                    <li><a href="/logout">Logout</a></li>
                <#else>
                    <li><a href="/login">Login</a></li>
                </#if>
            </ul>
        </div>
    </div>
</nav>

</body>



</html>