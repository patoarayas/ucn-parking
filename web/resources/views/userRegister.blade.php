<!DOCTYPE html>
<html lang="en">
<head>
    <!--Required meta tags-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, inicial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Connection Test</title>
</head>
<body>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<br>
<!--
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
    <p>Genero:
        <input type="radio" name="genero" value="0"> Masculino
        <input type="radio" name="genero" value="1"> Femenino
        <input type="radio" name="genero" value="2"> Otro
    </p>
    <p>
        <input type="submit" value="Enviar">
        <input type="reset" value="Borrar">
    </p>
</form>
<br/>
-->

<section>
    <div class="m-2">
        <div class="container p-2">
            <h1> Registro de Usuarios </h1>
            <br/>
            <form action="{{action('RegistrosController@storeUser')}}" method="post">
            {{csrf_field()}}
            <!-- Input Rut -->
                <div class="form-group row">
                    <label for="inputRut" class="col-sm-2 col-form-label">Rut</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputRut" name="rut" placeholder="12345678-9">
                    </div>
                </div>
                <!-- Input Nombre -->
                <div class="form-group row">
                    <label for="inputNombre" class="col-sm-2 col-form-label">Nombre</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputNombre" name="nombre" placeholder="Pedro Castillo Gonzalez">
                    </div>
                </div>
                <!-- Input Email -->
                <div class="form-group row">
                    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail" name="email" placeholder="example@mail.com">
                    </div>
                </div>
                <!-- Input Fono -->
                <div class="form-group row">
                    <label for="inputFono" class="col-sm-2 col-form-label">Fono fijo</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputFono" name="fono" placeholder="XXXXXX">
                    </div>
                </div>
                <!-- Input Movil -->
                <div class="form-group row">
                    <label for="inputMovil" class="col-sm-2 col-form-label">Fono movil</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputMovil" name="movil" placeholder="9XXXXXXXX">
                    </div>
                </div>
                <!-- Input Unidad Academica -->
                <div class="form-group row">
                    <label for="inputUnidadAcademica" class="col-sm-2 col-form-label">Unidad Academica</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputUnidadAcademica" name="unidadAcademica" placeholder="--------">
                    </div>
                </div>
                <!-- Input Genero -->
                <div class="form-group row">
                    <label for="Genero" class="col-sm-2 col-form-label">Genero </label>
                    <div class="col-2">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="genero" id="generoRadio1" value="0" checked>
                            <label class="form-check-label" for="generoRadio1">
                                Masculino
                            </label>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="genero" id="generoRadio2" value="1" checked>
                            <label class="form-check-label" for="generoRadio2">
                                Femenino
                            </label>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="genero" id="generoRadio3" value="2" checked>
                            <label class="form-check-label" for="generoRadio3">
                                Otro
                            </label>
                        </div>
                    </div>
                </div>
                <!-- Input Rol -->
                <div class="form-group row">
                    <label for="Rol" class="col-sm-2 col-form-label">Rol </label>
                    <div class="col-2">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rol" id="rolRadio1" value="0" checked>
                            <label class="form-check-label" for="rolRadio1">
                                Estudiante
                            </label>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rol" id="rolRadio2" value="1" checked>
                            <label class="form-check-label" for="rolRadio2">
                                Academico
                            </label>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rol" id="rolRadio3" value="2" checked>
                            <label class="form-check-label" for="rolRadio3">
                                Funcionario
                            </label>
                        </div>
                    </div>
                </div>
                <br/>
                <hr/>
                <br/>
                <!-- Input Patente -->
                <div class="form-group row">
                    <label for="inputPatente" class="col-sm-2 col-form-label">Patente</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputPatente" name="patente" placeholder="AA*AA-00">
                    </div>
                </div>
                <!-- Input Marca -->
                <div class="form-group row">
                    <label for="inputMarca" class="col-sm-2 col-form-label">Marca</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputMarca" name="marca" placeholder="Hyundai">
                    </div>
                </div>
                <!-- Input Modelo -->
                <div class="form-group row">
                    <label for="inputModelo" class="col-sm-2 col-form-label">Modelo</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputModelo" name="modelo" placeholder="Grand i10 Sedan">
                    </div>
                </div>
                <!-- Input Anio -->
                <div class="form-group row">
                    <label for="inputAnio" class="col-sm-2 col-form-label">Año</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputAnio" name="anio" placeholder="AAAA">
                    </div>
                </div>
                <!-- Input Observacion -->
                <div class="form-group row">
                    <label for="inputObservacion" class="col-sm-2 col-form-label">Observacion</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputObservacion" name="observacion" placeholder="--------">
                    </div>
                </div>
                <!-- Input Color -->
                <div class="form-group row">
                    <label for="inputColor" class="col-sm-2 col-form-label">Color</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputColor" name="color" placeholder="Rojo">
                    </div>
                </div>
                <br/>
                <!-- Boton de Registro -->
                <div class="form-group row">
                    <div class="col-1">
                        <button type="submit" class="btn btn-primary">Registrar</button>
                    </div>
                    <div class="col-1">
                        <button type="submit" class="btn btn-dark">Volver</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
