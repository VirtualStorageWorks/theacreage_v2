<!DOCTYPE HTML>
<html>
<head>
    <title>Business Listing ${businessListing.businessName}</title>

</head>
    <body>
    <#assign readOnly = "readonly='readonly'" />
    <#if CurrentUser??>
        <#if businessListing.getUser().getId() == CurrentUser.id>
            <#assign readOnly = "" />
        </#if>
    </#if>
    <form name="user" action="/business/create" method="post">
        Business Name: <input type="text" value="${(businessListing.businessName)!""}" name="businessName" ${readOnly} /> <br/>
        Street Address: <input type="text" value="${(businessListing.address)!""}" name="address" ${readOnly} />   <br/>
        City: <input type="text" value="${(businessListing.city)!""}" name="city" ${readOnly} />   <br/>
        State: <input type="text" value="${(businessListing.state)!""}" name="state" ${readOnly} />   <br/>
        Zipcode: <input type="text" value="${(businessListing.zip)!""}" name="zip"${readOnly}  />   <br/>

    <#if CurrentUser??>
        <#if businessListing.getUser().getId() == CurrentUser.id>
            <input type="submit" value="   Save   " />
        </#if>
    </#if>
    </form>
    </body>



    </html>