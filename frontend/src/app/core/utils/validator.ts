import { AbstractControl } from '@angular/forms';

export function textValidator(control: AbstractControl) {
  const specialCharacter = /[^a-zA-Z0-9á-ýÁ-Ý\s]/g;

  const value = control.value as string;

  if (specialCharacter.test(value)) {
    return {
      invalidChar: true,
    };
  }

  return null;
}
