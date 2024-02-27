import { Component } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { LoginService } from '@services/login.service';
import { Router } from '@angular/router';
import { Login } from '@models/login.model';

// import { NotifyService } from '@services/notify.service';
import { emailValidator } from '@utils/validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    // private notifySvc: NotifyService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, emailValidator]],
      password: ['', Validators.required, Validators.minLength(8)],
    });
  }

  get email() {
    return this.loginForm.get('email');
  }
  get password() {
    return this.loginForm.get('password');
  }

  login() {
    if (this.loginForm.invalid) {
      return this.loginForm.markAllAsTouched();
    }

    this.loginService.Login(this.loginForm.value).subscribe((res: any) => {
      this.loginService.id = res.id;
      this.loginService.token = res.token;
      //localStorage.setItem('id', res.id);
      //localStorage.setItem('token', res.token);
      this.router.navigate(['home']);
    });

    console.log(this.loginForm.value);
    console.log('conexion completada  redirigido a home');
  }

  register() {
    this.router.navigate(['home']);
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      // Realizar la lógica de inicio de sesión
      // this.notifySvc.showError(
      //   'Error al iniciar sesión',
      //   'Error con tus datos'
      // );

      return;
    }
    const user: Login = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    };
    /* this.loginService.Login(user).subscribe({
      next: () => {
        this.notifySvc.showSuccess(
          'Inicio de sesión exitoso',
          'Bienvenido a QuantumGamer'
        );
        this.router.navigate(['']);
        setTimeout(() => {
          location.href = '/quantum/home';
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
}
