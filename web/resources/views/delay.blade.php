@extends('layouts.layout')

@section('content')
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">Test Delay</div>
                <div class="card-body">
                    <div class="row align-content-center">
                        <div class="col-12">
                            <h6> Client Time: {{$times['client']}}</h6>
                            <h6> Server Delay: {{$times['delay']}}</h6>
                        </div>
                    </div>
                    <div class="row mt-5 justify-content-center">
                        <div class="col-2">
                            <a href ="{{route('home')}}" class="btn btn-primary btn-medium btn-block">Volver</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
