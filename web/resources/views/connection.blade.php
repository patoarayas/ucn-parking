<!DOCTYPE html>
<html>
<head>
    <title>Connection Test</title>
</head>
<body>

<h1>Registro de Personas</h1>
<br>
<form action="{{action('RegistrosController@storeUser')}}" method="post">
    {{csrf_field()}}
    <p>Rut: <input type="text" name="rut"></p>
    <p>Nombre <input type="text" name="nombre"></p>
    <p>E-Mail: <input type="text" name="email"></p>
    <p>Fono: <input type="text" name="fono"></p>
    <p>Movil: <input type="text" name="movil"></p>
    <p>Unidad Academica: <input type="text" name="unidadAcademica"></p>
    <p>Rol:
        <input type="radio" name="rol" value="0"> Estudiante
        <input type="radio" name="rol" value="1"> Academico
        <input type="radio" name="rol" value="2"> Funcionario
    </p>
    <p>Sexo:
        <input type="radio" name="sexo" value="0"> Masculino
        <input type="radio" name="sexo" value="1"> Femenino
        <input type="radio" name="sexo" value="2"> Otro
    </p>
    <p>
        <input type="submit" value="Enviar">
        <input type="reset" value="Borrar">
    </p>
</form>

</body>
</html>
