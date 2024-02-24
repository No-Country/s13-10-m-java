import { AbstractControl } from '@angular/forms';

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
