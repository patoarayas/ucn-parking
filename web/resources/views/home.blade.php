@extends('layouts.layout')

@section('content')
    <style>
        .btn-custom1{
            background: rgb(0,212,255);
            background: radial-gradient(circle, rgba(0,212,255,1) 0%, rgba(20,124,240,1) 77%, rgba(0,90,190,1) 100%);
        }
    </style>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <div class="card-header">Menú</div>

                    <div class="card-body">
                        <a href ="#" class="btn btn-custom1 btn-medium btn-block">Personas</a>
                        <a href ="#" class="btn btn-custom1 btn-medium btn-block">Vehiculos</a>
                        <a href ="#" class="btn btn-custom1 btn-medium btn-block">Accesos</a>
                        <a href ="{{route('registroUsuario')}}" class="btn btn-custom1 btn-medium btn-block">Registrar Usuario</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
