<?php


namespace App\Http\Controllers;

use Illuminate\Http\Request;

class ConnectionController
{
    public function connection(){
        return view('connection');
    }

    public function receive(Request $request){
        $data = $request->input('testData');
        var_dump($data);
    }
}
