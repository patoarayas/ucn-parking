<?php
namespace App\Http\Controllers;
use http\Url;
use Illuminate\Http\Request;
use model\Persona;
require_once 'Ice.php';
require_once base_path().'/domain.php';

class RegistrosController
{
    public function createUser()
    {
        return view('connection');
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
        try
        {
            $communicator = \Ice\Initialize();
            $persona = new Persona($rut, $nombre, $genero, $email, $fono, $movil, $unidadAcademica, $rol);
            $sistema_proxy = $communicator->StringToProxy("Contratos:tcp -z -t 15000 -p 3000");
            $sistema = \model\ContratosPrxHelper::uncheckedCast($sistema_proxy);
            // Calls interface method
            $sistema->registrarPersona($persona);

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
