<html>
<head><title>List New Classified Ad</title>
<body>
<div id="header">
</div>

<div id="content">

    <fieldset>
        <legend>List Classified</legend>
        <form name="user" action="/classified/create" enctype="multipart/form-data" method="post">
            Classified Title: <input type="text" name="title" /> <br/>
            Description: <input type="text" name="body" />   <br/>
            Main Phone: <input type="text" name="phone" />   <br/>
            Cell Phone: <input type="text" name="cellPhone" />   <br/>
            Email: <input type="text" name="email" />   <br/>
            Address: <input type="text" name="address" />   <br/>
            City: <input type="text" name="city" />   <br/>
            State: <input type="text" name="state" />   <br/>
            Zip: <input type="text" name="zip" />   <br/>
            Latitude: <input type="text" name="latitude" />   <br/>
            Longitude: <input type="text" name="longitude" />   <br/>
            File to upload: <input type="file" name="file"><br /> Name: <input
                type="text" name="name"><br /> <br />
            <input type="submit" value="   Save   " />
        </form>
    </fieldset>
    <br/>
</div>
</body>
</html>