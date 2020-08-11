<?php


namespace App\Http\Controllers;


class BuscarVehiculoController
{
    /**
     * Show the buscar vehiculo.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        return view('buscarVehiculo');
    }
}
