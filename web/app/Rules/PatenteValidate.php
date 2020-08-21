<?php

namespace App\Rules;

use Illuminate\Contracts\Validation\Rule;

class PatenteValidate implements Rule
{
    /**
     * Create a new rule instance.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    /**
     * Determine if the validation rule passes.
     *
     * @param  string  $attribute
     * @param  mixed  $value
     * @return bool
     */
    public function passes($attribute, $value)
    {
        try {
            $value = strtoupper($value);
            if(preg_match("/^[A-Z]{2}\*[A-Z]{2}-[0-9]{2}$/", $value)) {
                return true;
            }else{
                return false;
            }
        } catch (Exception $e) {
            return false;
        }
    }

    /**
     * Get the validation error message.
     *
     * @return string
     */
    public function message()
    {
        return 'Formato de patente no válido';
    }
}
