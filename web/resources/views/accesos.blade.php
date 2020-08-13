@extends('layouts.layout')

@section('content')
    <div class="row align-content-center">
        <div class="col-8">
            <h1>Registro de Accesos</h1>
        </div>
    </div>
    <div class="row align-content-center mt-3">
        <div class="col-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Hora de entrada</th>
                    <th>Porteria</th>
                    <th>Patente</th>
                </tr>
                </thead>
                <tbody>
                @foreach($accesos as $acceso)
                    <tr>
                        <td>{{$acceso->uid}}</td>
                        <td>{{$acceso->horaEntrada}}</td>
                        <td>
                            @switch($acceso->porteria)
                                @case(0)
                                SUR
                                @break
                                @case(1)
                                MANCILLA
                                @break
                                @case(2)
                                SANGRA
                                @break
                            @endswitch

                        </td>
                        <td>{{$acceso->patente}}</td>
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
