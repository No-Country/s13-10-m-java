import { Injectable } from '@angular/core';
import { AbstractControl, FormGroup } from '@angular/forms';

export function numericSpecialCharacterValidator(control: AbstractControl) {
  const specialCharacter = /[^a-zA-Zá-ýÁ-Ý\s]/g;

  const value = control.value as string;

  if (specialCharacter.test(value)) {
    return {
      numericSpecialCharacter: true,
    };
  }

  return null;
}

export function emailValidator(control: AbstractControl) {
  const specialCharacter =
    /^[a-zA-Z0-9._-]{2,}@[a-zA-Z0-9-]{2,}\.[a-zA-Z]{2,}$/;

  const value = control.value as string;

  if (!specialCharacter.test(value)) {
    return {
      email: true,
    };
  }

  return null;
}

export function passwordValidator(control: AbstractControl) {
  const regex =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[-_+|!@#$%^&.])(?!.*[-*"/(){}[\]=?¡¿'`~;,:<>\°])[A-Za-z\d_+\-|¡!@#$%^&\.]+$/g;

  const value = control.value as string;
  const isValid = regex.test(value);

  if (!isValid) {
    return { password: true };
  }

  return null;
}

export function validatorOpeningHour(controller: AbstractControl) {
  const openTime: string = controller.root.get('openTime')?.value;
  const closeTime: string = controller.root.get('closeTime')?.value;

  const openDate = new Date(`2000-01-01T${openTime}`)
  const closeDate = new Date(`2000-01-01T${closeTime}`)
  if(openDate > closeDate){
    return {invalidTime : true}
  }
  return null;
}

export function onlyAlphanumerics(controller:AbstractControl){
  const value:string = controller.value;
  const regex = /[^a-zA-Zá-ýÁ-Ý\s\d]/
  if(regex.test(value)){
    return {specialChar:true}
  }
  return null;
}
export function onlyNumbers(controller:AbstractControl){
  const value:string = controller.value;
  const regex = /[^\d]/
  if(regex.test(value)){
    return {
      noNumericChar:true}
    }
    return null;
  }
  export function phoneValidator(controller:AbstractControl){
    const value:string = controller.value;
    const regex = /^\+549[\d]+$/
    if(!regex.test(value)){
      return { invalidPhone:true }
    }
    return null;
}

@Injectable({
  providedIn: 'root',
})
export class GeneralValidator {
  hasError(form: FormGroup, control: string, error: string) {
    return (
      form.controls[control].touched && form.controls[control].hasError(error)
    );
  }
}
