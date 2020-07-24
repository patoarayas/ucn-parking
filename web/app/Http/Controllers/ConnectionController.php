<?php
namespace App\Http\Controllers;
use Illuminate\Http\Request;
require 'Ice.php';
require 'test.ice';
class ConnectionController
{
    public function connection()
    {
        return view('connection');
    }

    public function receive(Request $request)
    {
        $data = $request->input('testData');
        if ($data != null){
            $timeNow = $data;
        }else{
            $timeNow = date("h:i:s");
        }

        $ic = null;
        try
        {
            $ic = Ice\initialize();
            $base = $ic->stringToProxy("TestConnection:default -p 3000");
            $connection = model\SistemaHelper::checkedCast($base);
            if(!$connection)
            {
                throw new RuntimeException("Invalid proxy");
            }

            $connection->getDelay($data);
        }
        catch(Exception $ex)
        {
            echo $ex;
        }

        if($ic)
        {
            $ic->destroy(); // Clean up
        }
    }
}
?>