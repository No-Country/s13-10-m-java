import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { tokenData } from '@models/token.model';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private readonly key: string = 'token';

  constructor() {}

  getDecodedToken(): tokenData | null | undefined {
    const token = this.getToken();

    if (!token) {
      return undefined;
    }

    const helper = new JwtHelperService();

    try {
      const userData = helper.decodeToken(token);

      console.log(userData);

      if (!this.isValidToken(userData)) {
        this.clearToken();
      }

      return userData;
    } catch (error) {
      this.clearToken();
      return;
    }

    // if (token) {
    //   const helper = new JwtHelperService();
    //   let userData;
    //   try {
    //     userData = helper.decodeToken(token);
    //   } catch (error) {
    //     console.log('token invalido');
    //     this.clearToken();
    //     return;
    //   }

    //   if (!this.isValidToken(userData)) {
    //     console.log('token corrompido o incorrecto');
    //     this.clearToken();
    //     return;
    //   }
    //   return userData;
    // }
    // return;
  }

  isValidToken(obj: any): obj is tokenData {
    return (
      'iat' in obj &&
      'exp' in obj &&
      'USER_ID' in obj &&
      'ROLE' in obj &&
      'sub' in obj
    );
  }

  addToken(token: string) {
    localStorage.setItem(this.key, token);
  }

  getToken() {
    return localStorage.getItem(this.key);
  }

  clearToken() {
    const token = this.getToken();

    if (token) {
      localStorage.removeItem(this.key);
    }
  }

  isExpired() {
    const expTime = new Date(this.getDecodedToken()!.exp * 1000);
    // const expTime = new Date(1709645553 * 1000);
    const currentTime = new Date();

    return expTime < currentTime;
  }
}
