import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { tokenData } from '@models/token.model';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(
    private router:Router
  ) { }
  getTokenDecoded() {
    const token = localStorage.getItem('token');
    if (token) {
      const helper = new JwtHelperService();
      let userData;
      try {
        userData = helper.decodeToken(token);
      } catch (error) {
        console.log("token invalido")
        this.clearToken()
        return;
      }

      if(!this.isValidToken(userData)) {
        console.log("token corrompido o incorrecto")
        this.clearToken()
        return;
      };
      return userData;
    }
    return;
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
  private clearToken(){
    localStorage.removeItem("token")
    this.router.navigate(["/auth/login"])
  }
}
