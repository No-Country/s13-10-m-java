import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '@services/auth.service';
import Swal from 'sweetalert2';
import {
  GeneralValidator,
  emailValidator,
  numericSpecialCharacterValidator,
  passwordValidator,
} from '@utils/validator';
import { IAuthRegisterRes } from '@models/auth.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  form: FormGroup;
  showPassword: boolean = false;
  showRepeatPassword: boolean = false;
  isRegistered: boolean = false;
  userRegistered: IAuthRegisterRes | null = null;

  constructor(
    private readonly fb: FormBuilder,
    private readonly authService: AuthService,
    private readonly validator: GeneralValidator
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
          passwordValidator,
        ],
      ],
      repeatPassword: ['', Validators.required],
    });
  }

  onSubmit() {
    if (
      this.form.invalid ||
      this.form.value.password !== this.form.value.repeatPassword
    ) {
      return this.form.markAllAsTouched();
    }

    this.authService.register(this.form.value).subscribe({
      next: (res) => {
        this.userRegistered = res;

        Swal.fire({
          title: '¡Registro exitoso!',
          text: `¡Hola, ${res.nombre}! Hemos enviado a tu correo un enlace de confirmación, por favor ingresa para validar tu cuenta.`,
          icon: 'success',
        }).then(() => {
          this.form.reset();
          this.isRegistered = true;
        });
      },
      error: (error) => {
        console.log(error);
        Swal.fire({
          title: 'Ha ocurrido un error...',
          text: error.error,
          icon: 'error',
        });
      },
    });
  }

  handleShowPassword(): void {
    this.showPassword = !this.showPassword;
  }

  handleShowRepeatPassword(): void {
    this.showRepeatPassword = !this.showRepeatPassword;
  }

  hasError(name: string, error: string) {
    return this.validator.hasError(this.form, name, error);
  }

  get nameErrorMessage() {
    const control = 'nombre';

    if (this.hasError(control, 'required')) {
      return 'El nombre es requerido';
    }

    if (
      this.hasError(control, 'minlength') ||
      this.hasError(control, 'maxlength')
    ) {
      return 'Debe tener entre 2 y 60 caracteres';
    }

    if (this.hasError(control, 'numericSpecialCharacter')) {
      return 'No se admiten caracteres numéricos ni especiales';
    }

    return '';
  }

  get lastNameErrorMessage() {
    const control = 'apellido';

    if (this.hasError(control, 'required')) {
      return 'El apellido es requerido';
    }

    if (this.hasError(control, 'numericSpecialCharacter')) {
      return 'No se admiten caracteres numéricos ni especiales';
    }

    if (
      this.hasError(control, 'minlength') ||
      this.hasError(control, 'maxlength')
    ) {
      return 'Debe tener entre 2 y 60 caracteres';
    }
    return '';
  }

  get emailErrorMessage() {
    if (this.hasError('email', 'required')) {
      return 'El email es requerido';
    }

    if (this.hasError('email', 'email')) {
      return 'Ingresa un email válido';
    }

    if (
      this.hasError('email', 'minlength') ||
      this.hasError('email', 'maxlength')
    ) {
      return 'Debe tener entre 8 y 255 caracteres';
    }

    return '';
  }

  get passwordErrorMessage() {
    if (this.hasError('password', 'required')) {
      return 'La contraseña es requerida';
    }

    if (
      this.hasError('password', 'minlength') ||
      this.hasError('password', 'maxlength')
    ) {
      return 'Debe tener entre 8 y 128 caracteres';
    }

    if (this.hasError('password', 'password')) {
      return 'Debe contener al menos 1 minúscula, 1 mayúscula, 1 número y 1 caracter especial';
    }

    return '';
  }

  get repeatPasswordErrorMessage() {
    if (this.hasError('repeatPassword', 'required')) {
      return 'Por favor, confirma tu contraseña';
    }

    if (
      this.form.controls['repeatPassword'].touched &&
      this.form.value.password !== this.form.value.repeatPassword
    ) {
      return 'Las contraseñas no coinciden';
    }

    return '';
  }
}
