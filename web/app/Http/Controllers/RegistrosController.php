<?php
namespace App\Http\Controllers;
use http\Url;
use Illuminate\Http\Request;
use model\Persona;
use model\Vehiculo;
require_once 'Ice.php';
require_once base_path().'/domain.php';

class RegistrosController
{
    public function createUser()
    {
        return view('userRegister');
    }

    public function storeUser(Request $request)
    {
        $rut = $request->input('Rut');
        $nombre = $request->input('Nombre');
        $email = $request->input('email');
        $fono = $request->input('fono');
        $movil = $request->input('movil');
        $unidadAcademica = $request->input('unidadAcademica');
        $rol = intval($request->input('rol'));
        $genero = intval($request->input('genero'));

        $patente = $request->input('patente');
        $marca = $request->input('marca');
        $modelo = $request->input('modelo');
        $anio = $request->input('anio');
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

}
?>
