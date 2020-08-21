@extends('layouts.layout')

@section('content')
    <div class="row align-content-center">
        <div class="col-8">
            <h1>Registro de Personas</h1>
        </div>
    </div>
    <div class="row align-content-center mt-3">
        <div class="col-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Rut</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Genero</th>
                    <th>Fono</th>
                    <th>Movil</th>
                    <th>Unidad Academica</th>
                    <th>Rol</th>
                </tr>
                </thead>
                <tbody>
                @foreach($personas as $persona)
                    <tr>
                        <td>{{$persona->rut}}</td>
                        <td>{{$persona->nombre}}</td>
                        <td>{{$persona->email}}</td>
                        <td>
                            @switch($persona->genero)
                                @case(0)
                                Masculino
                                @break
                                @case(1)
                                Femenino
                                @break
                                @case(2)
                                Otro
                                @break
                            @endswitch
                        </td>
                        <td>{{$persona->fono}}</td>
                        <td>{{$persona->movil}}</td>
                        <td>{{$persona->unidadAcademica}}</td>
                        <td>
                            @switch($persona->rol)
                                @case(0)
                                ESTUDIANTE
                                @break
                                @case(1)
                                ACADEMICO
                                @break
                                @case(2)
                                FUNCIONARIO
                                @break
                            @endswitch

                        </td>
                    </tr>
                @endforeach
                </tbody>
            </table>
        </div>
    </div>
    <div class="row mt-5 justify-content-center">
        <div class="col-2">
            <a href ="{{route('home')}}" class="btn btn-primary btn-medium btn-block">Volver</a>
        </div>
    </div>
@endsection
