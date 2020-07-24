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
            $timeFormat = new DateTime();
            $timeFormat = date("Y-m-d H:i:s", $data);
            $timeNow = $timeFormat->format('U');
        }else{
            $timeNow = time();
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

            $connection->getDelay($timeNow);
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