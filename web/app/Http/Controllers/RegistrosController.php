<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use http\Url;
use Illuminate\Http\Request;
use model\Persona;
use model\Vehiculo;
use Freshwork\ChileanBundle\Rut;

require_once 'Ice.php';
require_once base_path() . '/domain.php';

class RegistrosController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\View\View
     */
    public function create()
    {
        return view('userRegister');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param Request $request
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(Request $request)
    {
        // Validar datos de la persona
        $personaValidatedData = $request->validate([
            'rut' => 'required|alpha_dash',
            'nombre' => 'required',
            'email' => 'required|email',
            'fono' => '',
            'movil' => 'required',
            'unidadAcademica' => '',
            'rol' => 'required',
            'genero' => 'required'
        ]);

        // Validar RUT
        if (Rut::parse($personaValidatedData['rut'])->validate()) {
            $personaValidatedData['rut'] = Rut::parse($personaValidatedData['rut'])->normalize();
        } else {
            // Invalid rut, redirect
            return redirect('registro/create')->withErrors(['Rut invalido']);
        }

        // Validar datos del vehículo
        $vehiculoValidatedData = $request->validate([
            'patente' => 'required|alpha_num|between:5,6',
            'marca' => 'required',
            'modelo' => 'required',
            'anio' => 'required|integer',
            'observacion' => 'nullable',
            'color' => 'required'
        ]);


        // Create Persona
        $persona = new Persona(
            $personaValidatedData['rut'],
            $personaValidatedData['nombre'],
            intval($personaValidatedData['genero']),
            $personaValidatedData['email'],
            $personaValidatedData['fono'],
            $personaValidatedData['movil'],
            $personaValidatedData['unidadAcademica'],
            intval($personaValidatedData['rol'])
        );

        // Create Vehiculo
        $vehiculo = new Vehiculo(
            $personaValidatedData['rut'],
            $vehiculoValidatedData['patente'],
            strtoupper($vehiculoValidatedData['marca']),
            $vehiculoValidatedData['modelo'],
            intval($vehiculoValidatedData['anio']),
            $vehiculoValidatedData['observacion'],
            $vehiculoValidatedData['color']
        );

        try {
            $communicator = \Ice\Initialize();
            $contratos_proxy = $communicator->StringToProxy("Contratos:tcp -z -t 15000 -p 3000");
            $contratos = \model\ContratosPrxHelper::uncheckedCast($contratos_proxy);

            // Calls interface method
            $contratos->registrarPersona($persona);
            $contratos->registrarVehiculo($vehiculo);


        } catch (\Exception $ex) {
            // Ice exception
            return redirect('registro/create')->withErrors([$ex->msg]);
        }


        return redirect('/')->with('status', 'Registro añadido correctamente');
    }

    /**
     * Display the specified resource.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public
    function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public
    function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public
    function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public
    function destroy($id)
    {
        //
    }
}
