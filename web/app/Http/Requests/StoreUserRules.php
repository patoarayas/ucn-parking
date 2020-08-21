<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class storeUserRules extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'rut' => 'required',
            'nombre' => 'required|alfa',
            'email' => 'required|email:rfc,dns',
            'genero' => 'required',
            'fono' => 'required|digits_between:7,10',
            'movil' => 'required|digits_between:8,9',
            'unidadAcademica' => 'required',
            'rol' => 'required',
            'patente' => 'required|size:8',
            'marca' => 'required',
            'modelo' => 'required',
            'anio' => 'required|integer|min:1920|max:2020',
            'color' => 'required|between:3,15',
            'observacion' => 'nullable'
        ];
    }
}
