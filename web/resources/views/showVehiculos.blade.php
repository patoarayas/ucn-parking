@extends('layouts.layout')

@section('content')
    <div class="row align-content-center">
        <div class="col-8">
            <h1>Registro de Vehiculos</h1>
        </div>
    </div>
    <div class="row align-content-center mt-3">
        <div class="col-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Patente</th>
                    <th>Rut</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Año</th>
                    <th>Color</th>
                    <th>Observacion</th>
                </tr>
                </thead>
                <tbody>
                @foreach($vehiculos as $vehiculo)
                    <tr>
                        <td>{{$vehiculo->patente}}</td>
                        <td>{{$vehiculo->rut}}</td>
                        <td>{{$vehiculo->marca}}</td>
                        <td>{{$vehiculo->modelo}}</td>
                        <td>{{$vehiculo->anio}}</td>
                        <td>{{$vehiculo->color}}</td>
                        <td>{{$vehiculo->observacion}}</td>
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
