<?php


namespace App\Http\Controllers;


class AccesosController
{
    /**
     * Show the accesos menu.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        return view('accesos');
    }
}
