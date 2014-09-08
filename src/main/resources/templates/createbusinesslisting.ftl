<html>
<head><title>List New Business</title>
<body>
<div id="header">
</div>

<div id="content">

    <fieldset>
        <legend>List Your Business</legend>
        <form name="user" action="/business/create" method="post">
            Business Name: <input type="text" name="businessName" /> <br/>
            Street Address: <input type="text" name="address" />   <br/>
            City: <input type="text" name="city" />   <br/>
            State: <input type="text" name="state" />   <br/>
            Zipcode: <input type="text" name="zip" />   <br/>
            <input type="submit" value="   Save   " />
        </form>
    </fieldset>
    <br/>
</div>
</body>
</html>