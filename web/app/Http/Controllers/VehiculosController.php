<?php


namespace App\Http\Controllers;


class VehiculosController
{
    /**
     * Show the vehiculos menu.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        return view('vehiculos');
    }
}
