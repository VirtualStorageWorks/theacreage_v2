<!DOCTYPE HTML>
<html>
<head>
    <title>Classified Listings</title>

</head>
<body>
<#list listOfClassifieds as classified>
    <a href="/classified/${classified.id}">${classified.title}</a><br>
    ${classified.user.username}

</#list>
</body>



</html>