@extends('layouts.layout')

@section('content')
    <div class="row align-content-center">
        <div class="col-8">
            <h1>Tabla de Personas</h1>
        </div>
        <div class="col-4">
            <form class="form-inline d-flex justify-content-center md-form form-sm mt-2">
                <input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Buscar Persona"
                       aria-label="Search">
            </form>
        </div>
    </div>
    <div class="row align-content-center">
        <div class="col-12">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Rut</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Fono</th>
                        <th>Movil</th>
                        <th>Genero</th>
                        <th>Unidad Academica</th>
                        <th>Rol</th>
                        <th>Patente</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <a href ="{{route('personas')}}" class="btn btn-primary btn-medium btn-block">Volver</a>
        </div>
    </div>
@endsection
