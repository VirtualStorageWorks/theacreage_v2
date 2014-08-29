<!DOCTYPE HTML>
<html>
<head>
    <#assign title = "User Account">
    <#assign userFirstName = "">
    <#if user.firstName??>
        <#assign userFirstName = "${user.firstName}">
        <#assign title = "${user.firstName} Account">
    </#if>
    <title>${title}</title>

</head>
<body>
<h1>${userFirstName}</h1>


<#list user.getClassifieds() as userClassified>
    <#if userClassified.title??>
    ${userClassified.title}<br>
    </#if>
</#list>
</body>



</html>