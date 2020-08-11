@extends('layouts.layout')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <div class="card-header">Menú</div>

                    <div class="card-body">
                        <a href ="{{route('buscarVehiculo')}}" class="btn btn-primary btn-medium btn-block">Buscar Vehículo</a>
                        <a href ="#" class="btn btn-primary btn-medium btn-block">Crud Vehículos</a>
                        <a href ="{{route('home')}}" class="btn btn-secondary btn-medium btn-block">Volver</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
