import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import {
  IAuthLoginReq,
  IAuthLoginRes,
  IAuthRegister,
} from '@models/auth.model';
import { TokenService } from './token.service';
import { BehaviorSubject, tap } from 'rxjs';
import { UserResponse } from '@models/user.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly authUrl: string = `${environment.apiUrl}/auth`;
  private readonly userUrl: string = `${environment.apiUrl}/api/usuario`;
  userLogged$ = new BehaviorSubject<UserResponse | null>(null);

  constructor(
    private readonly http: HttpClient,
    private readonly tokenService: TokenService,
    private readonly router: Router
  ) {}

  register(req: IAuthRegister) {
    return this.http.post(`${this.authUrl}/register`, req);
  }

  login(req: IAuthLoginReq) {
    return this.http
      .post<IAuthLoginRes>(`${this.authUrl}/login`, req)
      .pipe(tap((res) => this.tokenService.addToken(res.token)));
  }

  getUserLogged() {
    return this.http
      .get<UserResponse>(
        `${this.userUrl}/${this.tokenService.getTokenDecoded()?.USER_ID}`
      )
      .pipe(tap((res) => this.userLogged$.next(res)));
  }

  logout() {
    this.tokenService.clearToken();
    this.router.navigate(['/auth/login']);
  }
}
