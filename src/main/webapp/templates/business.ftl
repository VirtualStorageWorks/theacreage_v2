<!DOCTYPE HTML>
<html>
<head>
    <title>Business Listing ${businessListing.businessName}</title>

</head>
    <body>
    <#assign readOnly = "readonly='readonly'" />
    <#if CurrentUser??>
        <#if businessListing.getUser().id == CurrentUser.id>
        <form name="user" action="/business/${businessListing.businessName}/update" method="post">
            <#assign readOnly = "" />
        </#if>
    </#if>
        Business Name: <input type="text" value="${(businessListing.businessName)!""}" name="businessName" ${readOnly} /> <br/>
    <#list businessListing.getBusinessAddresses() as businessAddress>
        <#if businessAddress.status == true>
            Street Address: <input type="text" value="${(businessAddress.address)!""}" name="address" ${readOnly} />   <br/>
            City: <input type="text" value="${(businessAddress.city)!""}" name="city" ${readOnly} />   <br/>
            State: <input type="text" value="${(businessAddress.state)!""}" name="state" ${readOnly} />   <br/>
            Zipcode: <input type="text" value="${(businessAddress.zip)!""}" name="zip"${readOnly}  />   <br/>
            <input type="hidden" name="status" value=true />
            Active? <input type="checkbox" value=false name="status"/> <br />
        </#if>
    </#list>
    <#if CurrentUser??>
        <#if businessListing.getUser().getId() == CurrentUser.id>
            <input type="submit" value="   Save   " />
        </#if>
    </#if>
    </form>
    </body>



    </html>