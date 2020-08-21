<?php
namespace App\Http\Controllers;
use App\Http\Requests\StoreUserValidate;
use http\Url;
use Illuminate\Http\Request;
use model\Persona;
use model\Vehiculo;
require_once 'Ice.php';
require_once base_path().'/domain.php';

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
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('userRegister');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \app\Http\Requests\RegistrosController  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreUserValidate $request)
    {
        $request;

        $rut = $request->input('rut');
        $nombre = $request->input('nombre');
        $email = $request->input('email');
        $fono = $request->input('fono');
        $movil = $request->input('movil');
        $unidadAcademica = $request->input('unidadAcademica');
        $rol = intval($request->input('rol'));
        $genero = intval($request->input('genero'));

        $patente = $request->input('patente');
        $marca = $request->input('marca');
        $modelo = $request->input('modelo');
        $anio = intval($request->input('anio'));
        $observacion = $request->input('observacion');
        $color = $request->input('color');

        try
        {
            $communicator = \Ice\Initialize();
            $persona = new Persona($rut, $nombre, $genero, $email, $fono, $movil, $unidadAcademica, $rol);
            $vehiculo = new Vehiculo($rut, $patente, $marca, $modelo, $anio, $observacion, $color);
            $sistema_proxy = $communicator->StringToProxy("Contratos:tcp -z -t 15000 -p 3000");
            $sistema = \model\ContratosPrxHelper::uncheckedCast($sistema_proxy);
            // Calls interface method
            $sistema->registrarPersona($persona);
            $sistema->registrarVehiculo($vehiculo);

            if(!$sistema_proxy)
            {
                throw new RuntimeException("Invalid proxy");
            }
        }
        catch(Exception $ex)
        {
            echo $ex;
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}