import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from '@services/token.service';

const authGuard: CanActivateFn = () => {
  if (inject(TokenService).getToken() !== null) {
    inject(Router).navigateByUrl('/home');

    return false;
  }

  return true;
};

export default authGuard;
