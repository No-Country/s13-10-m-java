import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { TokenService } from '@services/token.service';
import { of, switchMap } from 'rxjs';
import Swal from 'sweetalert2';

const dashboardGuard: CanActivateFn = () => {
  const tokenService = inject(TokenService);

  if (tokenService.getToken() === null) {
    showLoginAlert();

    inject(Router).navigateByUrl('/auth/login');

    return false;
  }

  const authService = inject(AuthService);

  if (tokenService.isExpired()) {
    showExpiredTokenAlert(() => {
      tokenService.clearToken();

      authService.logout();

      return false;
    });
  }

  return authService.getUserLogged().pipe(
    switchMap(() => {
      if (authService.userLogged$.value?.validEmail) {
        return of(true);
      }

      showInvalidEmailAlert();

      authService.logout();

      return of(false);
    })
  );
};

const showLoginAlert = () => {
  Swal.fire({
    title: 'Vaya, no haz iniciado sesión',
    text: 'Ingresa a tu cuenta para acceder a la plataforma',
    icon: 'error',
  });
};

const showExpiredTokenAlert = (f: Function) => {
  Swal.fire({
    title: 'Tiempo de sesión expirado',
    text: 'Inicia sesión nuevamente',
    icon: 'error',
  }).then(() => f());
};

const showInvalidEmailAlert = () => {
  Swal.fire({
    title: 'Oops... tu cuenta no está validada',
    text: 'Valida tu cuenta con el enlace que enviamos a tu correo.',
    icon: 'error',
  });
};

export default dashboardGuard;
