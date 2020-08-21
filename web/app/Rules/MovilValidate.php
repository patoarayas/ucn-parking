<?php

namespace App\Rules;

use Illuminate\Contracts\Validation\Rule;
use Regex;

class MovilValidate implements Rule
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
            if(preg_match("/^[9]{1}[0-9]{8}$/", $value)) {
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
        return 'Recuerde agregar un 9 antes de su número telefónico';
    }
}
