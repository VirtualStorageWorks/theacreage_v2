<!DOCTYPE HTML>
<html>
<head>
    <title>Business Directory</title>

</head>
<body>
<#list listOfBusinesses as businesslisting>
    <#if businesslisting.getBusinessStatus().statusName == "Active">
    <a href="/business/${businesslisting.businessName}">${businesslisting.businessName}</a>
    ${businesslisting.businessStatus.statusName}
    ${businesslisting.businessType.businessType}<br>
    <#list businesslisting.businessAddresses as businessAddress>
        ${businessAddress.city}
    </#list>
    </#if>
</#list>
</body>
</html>