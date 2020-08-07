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

    public function testConnection(){
        // Initialize Ice communicator
        // must have declared -> require_once 'Ice.php'
        $communicator = \Ice\initialize();

        // Initialize proxy
        // Sistema proxy -> "Sistema:tcp -z -t 15000 -p 3000"
        // Contratos proxy -> "Contratos:tcp -z -t 15000 -p 3000"
        $sistema_proxy = $communicator->StringToProxy("Sistema:tcp -z -t 15000 -p 3000");

        // Creates interface
        $sistema = \model\SistemaPrxHelper::uncheckedCast($sistema_proxy);

        $client_time = (int) round(microtime(true)*1000);
        echo("Client time: ".$client_time);

        // Calls interface method
        $delay = $sistema->getDelay($client_time);
        echo "<br>";
        echo("Delay: ".$delay);
    }
}
?>
