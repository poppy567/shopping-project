<html>
<head>
    <title>Form to create a new resource</title>
</head>
<body>
<!-- This form will submit a POST request and the POST method defined in TodoResource will respond to it -->
<center><h2>Create a new SHORT</h2></center>
<form action="../jerseyrest/v1/api/shorts" method="POST">
    <label for="name">Name</label>
    <input id="name" name="name" />
    <br/>
    Price:
    <input name="price" ></input>
    <br/>
    Color:
    <input name="color" ></input>
    <br/>
    Description:
    <input name="description" ></input>
    <br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
