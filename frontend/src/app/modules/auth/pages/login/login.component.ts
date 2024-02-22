import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../../../core/services/login.service';
import { Router } from '@angular/router';
import { Login } from '../../../../core/models/login.model';
import {
  emailValidator,
  numericSpecialCharacterValidator,
  customPasswordValidator,
} from 'src/app/core/utils/validator';

import { NotifyService } from '../../../../services/notify.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;
  showPassword: boolean = false;

  constructor(
    private loginService: LoginService,
    private readonly fb: FormBuilder,
    private notifySvc: NotifyService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
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
    });
    console.log(this.loginForm.value);
    console.log('conexion completada  redirigido a home');
  }

  get email() {
    return this.loginForm.get('email');
  }
  get password() {
    return this.loginForm.get('password');
  }

  login() {
    this.loginService.Login(this.loginForm.value).subscribe((res: any) => {
      this.loginService.id = res.id;
      this.loginService.token = res.token;
      localStorage.setItem('id', res.id);
      localStorage.setItem('token', res.token);
      this.router.navigate(['/home']);
    });
  }

  register() {
    this.router.navigate(['/home']);
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      // Realizar la lógica de inicio de sesión
      /* this.notifySvc.showError(
        'Error al iniciar sesión',
        'Error con tus datos'
      ); */

      return;
    }

    const user: Login = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    };
    /* this.loginService.Login(user).subscribe({
      next: () => {
        this.notifySvc.(
          'Inicio de sesión exitoso',
          'Bienvenido a EcoApp'
        );
        this.router.navigate(['']);
        setTimeout(() => {
          location.href = '/home';
        }, 3000);
      },
      error: (err) => {
        this.notifySvc.showError(
          'Error al iniciar sesión',
          'Error con tus datos'
        );
      },
    }); */
  }
  handleShowPassword(): void {
    this.showPassword = !this.showPassword;
  }
}
