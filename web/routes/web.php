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

require_once 'C:\Users\David\Dev\DevTools\php-7.3.20\shared\php\ice\Ice.php';
require_once 'Ice.php';
// Ruta relativa del domain.php (resultado de compilar el domain.ice)
// Asume que el domain.php esta en la siguiente ubicación /ucn-parking/web/domain.php
//require_once __DIR__."/../domain.php";
require_once base_path().'/domain.php';

Route::get('/', function () {

    return view('welcome');
});

Route::get('/test','RegistrosController@createUser');
Route::post('/receive','RegistrosController@storeUser');

/**
 * Test connection with the backend
 */
Route::get('/delay','RegistrosController@testConnection');


