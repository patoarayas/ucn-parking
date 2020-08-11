@extends('layouts.layout')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <div class="card-header">Menú</div>

                    <div class="card-body">
                        <a href ="#" class="btn btn-primary btn-medium btn-block">Personas</a>
                        <a href ="#" class="btn btn-primary btn-medium btn-block">Vehiculos</a>
                        <a href ="#" class="btn btn-primary btn-medium btn-block">Accesos</a>
                        <a href ="{{route('registroUsuario')}}" class="btn btn-primary btn-medium btn-block">Registrar Usuario</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
