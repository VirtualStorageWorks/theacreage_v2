<!DOCTYPE HTML>
<html>
<head>
    <title>Business Listing ${businessListing.businessName}</title>

</head>
    <body>
    <#assign readOnly = "readonly='readonly'" />
    <#if CurrentUser??>
        <#if businessListing.getUser().id == CurrentUser.id>
        <form name="user" action="/business/create" method="post">
            <#assign readOnly = "" />
        </#if>
    </#if>
        Business Name: <input type="text" value="${(businessListing.businessName)!""}" name="businessName" ${readOnly} /> <br/>
    <#list businessListing.getBusinessAddresses() as businessAddress>
        Street Address: <input type="text" value="${(businessAddress.city)!""}" name="address" ${readOnly} />   <br/>
        City: <input type="text" value="${(businessListing.businessAddress[0].city)!""}" name="city" ${readOnly} />   <br/>
        State: <input type="text" value="${(businessListing.state)!""}" name="state" ${readOnly} />   <br/>
        Zipcode: <input type="text" value="${(businessListing.zip)!""}" name="zip"${readOnly}  />   <br/>
    </#list>
    <#if CurrentUser??>
        <#if businessListing.getUser().getId() == CurrentUser.id>
            <input type="submit" value="   Save   " />
        </#if>
    </#if>
    </form>
    </body>



    </html>