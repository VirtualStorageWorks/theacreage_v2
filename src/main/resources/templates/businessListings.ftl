<!DOCTYPE HTML>
<html>
<head>
    <title>Business Listings</title>

</head>
<body>
<#list listOfBusinesses as businesslisting>
    <a href="/businesslisting/${businesslisting.id}">${businesslisting.businessName}</a><br>

</#list>
</body>



</html>