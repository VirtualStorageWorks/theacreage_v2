<!DOCTYPE HTML>
<html>
<head>
    <title>Business Directory</title>

</head>
<body>
<#list listOfBusinesses as businesslisting>
    <a href="/business/${businesslisting.id}">${businesslisting.businessName}</a><br>

</#list>
</body>



</html>