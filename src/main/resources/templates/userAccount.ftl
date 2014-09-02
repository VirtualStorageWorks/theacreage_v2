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
    <a href="/classified/${userClassified.id}">${userClassified.title}</a><br>
    </#if>
</#list>

<#list user.getBusinessListings() as userBusinessListing>
    <#if userBusinessListing.businessName??>
    <a href="/business/${userBusinessListing.businessName}">${userBusinessListing.businessName}</a><br>
    </#if>
</#list>

<h4><a href="/business/create">List New Business</a></h4>
<h4><a href="/classified/create">Post Classified Ad</a></h4>
</body>



</html>