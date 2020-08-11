<?php


namespace App\Http\Controllers;


class PersonasController
{
    /**
     * Show the personas menu.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        return view('personas');
    }
}
