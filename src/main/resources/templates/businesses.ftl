<!DOCTYPE HTML>
<html>
<head>
    <title>Business Directory</title>

</head>
<body>
<#list listOfBusinesses as businesslisting>
    <a href="/business/${businesslisting.businessName}">${businesslisting.businessName}</a>
    ${businesslisting.getBusinessStatus().statusName}
    ${businesslisting.getBusinessType().getBusinessType()}<br>
    <#list businesslisting.businessAddresses as businessAddress>
        ${businessAddress.city}
    </#list>
</#list>
</body>
</html>