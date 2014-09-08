<!DOCTYPE HTML>
<html>
<head>
    <title>Classified Listings</title>

</head>
<body>
<#list userList as user>
    <#if user.firstName??>
        ${user.firstName}<br>
    </#if>
</#list>
</body>



</html>