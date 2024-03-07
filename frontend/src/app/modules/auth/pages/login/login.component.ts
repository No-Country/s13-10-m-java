import { Component } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

// import { NotifyService } from '@services/notify.service';
import { emailValidator } from '@utils/validator';
import Swal from 'sweetalert2';
import { AuthService } from '@services/auth.service';

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
    private readonly authService: AuthService,
    private readonly fb: FormBuilder,
    private readonly router: Router
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

    this.authService.login(this.loginForm.value).subscribe({
      next: () => {
        Swal.fire({
          title: '¡Ingreso exitoso!',
          text: `¡Hola, bienvenido a esta iniciativa ambiental!`,
          icon: 'success',
        }).then(() => {
          this.loginForm.reset();
          this.router.navigate(['/home']);
        });
      },
      error: (error) => {
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
  
}
