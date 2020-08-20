@extends('layouts.layout')

@section('content')
    <style>
        .btn-custom1{
            background-color: #035ec5;
            color: white;
        }
    </style>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <div class="card-header">Menú</div>

                    <div class="card-body">
                        <a href ="{{route('registro.create')}}" class="btn btn-custom1 btn-custom1 btn-block">Registrar Usuario</a>
                        <a href ="{{route('personas')}}" class="btn btn-primary btn-custom1 btn-block">Mostrar Personas</a>
                        <a href ="{{route('vehiculos')}}" class="btn btn-primary btn-custom1 btn-block">Mostrar Vehiculos</a>
                        <a href ="{{route('accesos')}}" class="btn btn-primary btn-custom1 btn-block">Mostrar Accesos</a>
                        <a href ="{{route('delay')}}" class="btn btn-primary btn-custom1 btn-block">Delay Test</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
