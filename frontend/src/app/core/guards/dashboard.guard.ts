import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { TokenService } from '@services/token.service';
import { map, of, switchMap } from 'rxjs';
import Swal from 'sweetalert2';

const dashboardGuard: CanActivateFn = () => {
  if (inject(TokenService).getToken() === null) {
    showLoginAlert();

    inject(Router).navigateByUrl('/auth/login');

    return false;
  }

  const authService = inject(AuthService);

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

const showInvalidEmailAlert = () => {
  Swal.fire({
    title: 'Oops... tu cuenta no es válida',
    text: 'Valida tu cuenta con el enlace que enviamos a tu correo.',
    icon: 'error',
  });
};

export default dashboardGuard;
