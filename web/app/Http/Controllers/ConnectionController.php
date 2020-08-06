<?php
namespace App\Http\Controllers;
use Illuminate\Http\Request;
require 'Ice.php';
//require 'test.ice';
class ConnectionController
{
    public function personForm()
    {
        return view('connection');
    }

    public function sendPerson(Request $request)
    {
        $rut = $request->input('Rut');
        $nombre = $request->input('Nombre');
        $email = $request->input('email');
        $fono = $request->input('fono');
        $movil = $request->input('movil');
        $unidadAcademica = $request->input('unidadAcademica');
        $rol = $request->input('rol');
        $genero = $request->input('sexo');

        $communicator = null;
        try
        {
            $communicator = Ice\initialize();
            $sistema_proxy = $communicator->StringToProxy("Sistema:tcp -z -t 15000 -p 3000");
            $sistema = \model\PersonaPrxHelper::uncheckedCast($sistema_proxy);
            // Calls interface method
            $sistema->_construct($rut,$nombre,$genero,$email,$fono,$movil,$unidadAcademica,$rol);
            echo "<br>";

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
