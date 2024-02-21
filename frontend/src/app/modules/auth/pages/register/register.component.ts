import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import {
  emailValidator,
  numericSpecialCharacterValidator,
  customPasswordValidator,
} from 'src/app/core/utils/validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  form: FormGroup;

  constructor(
    private readonly fb: FormBuilder,
    private readonly authService: AuthService
  ) {
    this.form = this.fb.group({
      nombre: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(60),
          numericSpecialCharacterValidator,
        ],
      ],
      apellido: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(60),
          numericSpecialCharacterValidator,
        ],
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(255),
          emailValidator,
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(128),
          customPasswordValidator,
        ],
      ],
      repeatPassword: ['', Validators.required],
    });
  }

  onSubmit() {
    console.log(this.form);

    if (this.form.invalid) {
      return this.form.markAllAsTouched();
    }

    if (
      this.form.get('password')?.value !==
      this.form.get('repeatPassword')?.value
    ) {
      alert('Las contraseñas no coinciden');
      return;
    }

    console.log(this.form.value);

    this.authService.register(this.form.value).subscribe({
      next: (res) => console.log(res),
      error: (error) => console.log(error),
    });
  }

  hasError(name: string, error: string) {
    return (
      this.form.controls[name].touched &&
      this.form.controls[name].hasError(error)
    );
  }
}
