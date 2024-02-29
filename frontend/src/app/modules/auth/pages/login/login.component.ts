import { Component } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { LoginService } from '@services/login.service';
import { Router } from '@angular/router';

// import { NotifyService } from '@services/notify.service';
import { emailValidator } from '@utils/validator';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;
  showPassword: boolean = false;
  error: string = 'Error en la aplicacion';

  constructor(
    private loginService: LoginService,
    private readonly fb: FormBuilder,

    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, emailValidator]],
      password: ['', [Validators.required, Validators.minLength(8)]],
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

    console.log(this.loginForm.value);

    this.loginService.Login(this.loginForm.value).subscribe((res: any) => {
      this.loginService.id = res.id;
      this.loginService.token = res.token;
      localStorage.setItem('id', res.id);
      localStorage.setItem('token', res.token);
    });

    console.log(this.loginForm.markAsTouched);

    this.loginService.Login(this.loginForm.value).subscribe({
      next: () => {
        Swal.fire({
          title: '¡Ingreso exitoso!',
          text: `¡Hola,Bienvenido a esta iniciativa ambiental¡.`,
          icon: 'success',
        }).then(() => this.loginForm.reset());
        this.router.navigate(['/home']);
      },
      error: (error) => {
        Swal.fire({
          title: 'Ha ocurrido un error...',
          text: error.error,
          icon: 'error',
        });
      },
    });

    return;
  }

  handleShowPassword(): void {
    this.showPassword = !this.showPassword;
  }
}
