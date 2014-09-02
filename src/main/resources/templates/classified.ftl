<!DOCTYPE HTML>
<html>
<head>
    <title>Classified Listings</title>

</head>
<body>
<#assign readOnly = "readonly='readonly'" />
<#if CurrentUser??>
    <#if classified.getUser().getId() == CurrentUser.id>
        <#assign readOnly = "" />
    </#if>
</#if>
<form name="user" action="/classified/create" method="post">
    Classified Title: <input type="text" value="${classified.title}" name="title" ${readOnly}/> <br/>
    Description: <input type="text" value="${classified.body}" name="body"${readOnly} />   <br/>
    Main Phone: <input type="text" value="${(classified.phone)!""}" name="phone" ${readOnly} />   <br/>
    Cell Phone: <input type="text" value="${(classified.cellPhone)!""}" name="cellPhone" ${readOnly} />   <br/>
    Email: <input type="text" value="${(classified.email)!""}" name="email" ${readOnly} />   <br/>
    Address: <input type="text" value="${(classified.address)!""}" name="address" ${readOnly} />   <br/>
    City: <input type="text" value="${classified.city}" name="city" ${readOnly} />   <br/>
    State: <input type="text" value="${classified.state}" name="state" ${readOnly} />   <br/>
    Zip: <input type="text" value="${classified.zip}" name="zip" ${readOnly} />   <br/>
    Latitude: <input type="text" value="${(classified.latitude)!""}" name="latitude" ${readOnly} />   <br/>
    Longitude: <input type="text" value="${(classified.longitude)!""}" name="longitude" ${readOnly} />   <br/>

<#if CurrentUser??>
    <#if classified.getUser().getId() == CurrentUser.id>
        <input type="submit" value="   Save   " />
    </#if>
</#if>
</form>
</body>



</html>