<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

require_once 'Ice.php';
// Ruta relativa del domain.php (resultado de compilar el domain.ice)
// Asume que el domain.php esta en la siguiente ubicación /ucn-parking/web/domain.php
//require_once __DIR__."/../domain.php";
require_once base_path().'/domain.php';

Route::get('/', function () {

    return view('welcome');
});



Route::resource('registro', 'RegistrosController');
Route::get('/home', ['uses' => 'HomeController@index', 'as' => 'home']);


/**
 *
 */
Route::get('/home', function(){
   return view('home');
})->name('home');

/**
 * Test connection with the backend. Get delay.
 */
Route::get('/delay', function(){

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

    // Calls interface method
    $delay = $sistema->getDelay($client_time);
    $times['client'] = $client_time;
    $times['delay'] = $delay;
    return view('delay',['times'=>$times]);
})->name('delay');

/**
 * Show access register
 */
Route::get('/accesos', function(){

    $communicator = \Ice\Initialize();
    $sistema_proxy = $communicator->StringToProxy("Sistema:tcp -z -t 15000 -p 3000");
    $sistema = \model\SistemaPrxHelper::uncheckedCast($sistema_proxy);
    $accesos = $sistema->getAccesos();

    return view('accesos',['accesos'=>$accesos]);
})->name('accesos');



