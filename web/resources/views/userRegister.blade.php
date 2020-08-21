@extends('layouts.layout')

@section('content')
    <div class="row">
        <div class="col-12">
            @if ($errors->any())
                <div class="alert alert-danger">
                    <ul>
                        @foreach ($errors->all() as $error)
                            <li>{{ $error }}</li>
                        @endforeach
                    </ul>
                </div>
            @endif
            <h1> Registro de Usuarios </h1><br/>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <form class="needs-validation" action="{{action('RegistrosController@store')}}" method="post" novalidate>
            {{csrf_field()}}
            <!-- Input Rut -->
                <div class="form-group row">
                    <div class="col-sm-12">
                        <h4> Datos de la persona </h4>
                    </div>
                </div>
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
                        <input type="number" class="form-control" id="inputFono" name="fono" placeholder="552111111">
                    </div>
                </div>
                <!-- Input Movil -->
                <div class="form-group row">
                    <label for="inputMovil" class="col-sm-2 col-form-label">Fono movil</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputMovil" name="movil" placeholder="911111111">
                    </div>
                </div>
                <!-- Input Unidad Academica -->
                <div class="form-group row">
                    <label for="inputUnidadAcademica" class="col-sm-2 col-form-label">Unidad Academica</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputUnidadAcademica" name="unidadAcademica" placeholder="--------">
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
                <div class="form-group row">
                    <div class="col-sm-12">
                        <h4> Datos del vehículo </h4>
                    </div>
                </div>
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
                        <a href ="{{route('home')}}" class="btn btn-secondary btn-medium btn-block">Volver</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script src="bootstrap-validate.js"></script>
    <script>
        bootstrapValidate()
    </script>
@endsection
