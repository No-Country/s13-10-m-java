import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { IAuthRegister } from '../models/auth.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly url: string = `${environment.apiUrl}/auth`;

  constructor(private readonly http: HttpClient) {}

  register(req: IAuthRegister) {
    return this.http.post(`${this.url}/register`, req);
  }
}
