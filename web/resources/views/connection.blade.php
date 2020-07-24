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
    <label for="testData">Dato de prueba (Y-m-d H:i:s):</label>
    <input type="text" id="testData" name="testData"><br><br>
    <button type="submit" value="enviar">Submit</button>
</form>

</body>
</html>
