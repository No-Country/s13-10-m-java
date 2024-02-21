import { AbstractControl } from '@angular/forms';

export function numericSpecialCharacter(control: AbstractControl) {
  const specialCharacter = /[^a-zA-Zá-ýÁ-Ý\s]/g;

  const value = control.value as string;

  if (specialCharacter.test(value)) {
    return {
      numericSpecialCharacter: true,
    };
  }

  return null;
}

export function password(control: AbstractControl) {
  const specialCharacter = /[^[a-z][A-Z]*\d)[^\w\d\s]/g;

  const value = control.value as string;

  if (specialCharacter.test(value)) {
    return {
      password: true,
    };
  }

  return null;
}
