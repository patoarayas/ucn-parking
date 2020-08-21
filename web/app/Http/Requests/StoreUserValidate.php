<?php

namespace App\Http\Requests;
use App\Rules\RutValidate;
use App\Rules\MovilValidate;
use App\Rules\PatenteValidate;
use Illuminate\Foundation\Http\FormRequest;
use DateTime;

class storeUserValidate extends FormRequest
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
        $Object = new DateTime();
        $Date = $Object->format("d-m-Y");
        $Year = $Object->format("Y");
        return [
            'rut' => [
                'required',
                new RutValidate(),
            ],
            'nombre' => [
                'required',
            ],
            'email' => [
                'required',
                'email:rfc,dns',
            ],
            'genero' => [
                'required',
            ],
            'fono' => [
                'required',
                'digits_between:6,9',
            ],
            'movil' => [
                'required',
                'digits:9',
                new MovilValidate(),
            ],
            'unidadAcademica' => [
                'required',
            ],
            'rol' => [
                'required',
            ],
            'patente' => [
                'required',
                'size:8',
                new PatenteValidate(),
            ],
            'marca' => [
                'required',
            ],
            'modelo' => [
                'required',
            ],
            'anio' => [
                'required',
                'integer',
                ('min:'.strval(intval($Year-100))),
                ('max:'.strval($Year)),
            ],
            'color' => [
                'required',
                'between:3,15',
            ],
            'observacion' => [
                'nullable',
            ],
        ];
    }
}
