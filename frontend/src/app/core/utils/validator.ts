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
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[-+|!@#$%^&.])[A-Za-z\d_+-|¡!@#$%^&.]+$/g;

  const value = control.value as string;

  console.log(`before: ${regex.test(value)}`);

  // jkdsj fjlk
  //
  //
  //
  console.log(!regex.test(value) ? { password: false } : { password: true });
  return regex.test(value) ? null : { password: true };

  // if (regex.test(value)) {
  //   console.log(`after: ${!regex.test(value)}`);

  //   return {
  //     password: true,
  //   };
  // }

  // return null;
}
