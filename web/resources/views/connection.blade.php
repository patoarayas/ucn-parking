<!DOCTYPE html>
<html>
<head>
    <title>Connection Test</title>
</head>
<body>

<h1>Conexión a ZeroC</h1>
<br>
<form action="{{action('ConnectionController@receive')}}" method="POST">
    {{csrf_field()}}
    <label for="testData">Dato de prueba:</label>
    <input type="text" id="testData" name="testData"><br><br>
    <button type="submit">Submit</button>
    <button type="submit" formmethod="post" value="enviar">Submit using POST</button>
</form>

</body>
</html>
