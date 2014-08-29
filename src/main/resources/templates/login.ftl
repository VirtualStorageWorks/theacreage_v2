<!DOCTYPE html>
<html>
<head>
    <style type="text/css" media="screen">
        @import "/css/bootstrap.min.css";
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
    <script src="./js/bootstrap.js" type="text/javascript">
    </script>
    <title>Spring Security Example</title>
    <style type="text/css">
        /* Override some defaults */
        html, body {
            background-color: #eee;
        }
        body {
            padding-top: 40px;
        }
        .container {
            width: 300px;
        }

        /* The white background content wrapper */
        .container > .content {
            background-color: #fff;
            padding: 20px;
            margin: 0 -20px;
            -webkit-border-radius: 10px 10px 10px 10px;
            -moz-border-radius: 10px 10px 10px 10px;
            border-radius: 10px 10px 10px 10px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
            box-shadow: 0 1px 2px rgba(0,0,0,.15);
        }

        .login-form {
            margin-left: 65px;
        }

        legend {
            margin-right: -50px;
            font-weight: bold;
            color: #404040;
        }

    </style>
</head>
<body>

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
<div class="container">
    <div class="content">
        <div class="row">
            <div class="login-form">
                <h2>Login</h2>
                <div class="register">
                    <a href="/register">Register</a>
                </div>
                <form action="/login" method="post">
                    <fieldset>
                        <div class="clearfix">
                            <input type="text" name="username" placeholder="Username"/>
                        </div>
                        <div class="clearfix">
                            <input type="password" name="password" placeholder="Password"/>
                        </div>
                            <button class="btn primary" type="submit">Sign in</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- /container -->

</body>
</html>