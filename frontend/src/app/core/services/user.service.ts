import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { UserResponse } from '@models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly URL = environment.apiUrl + '/usuario';
  constructor(private http: HttpClient) {}

  getUser(id: string | undefined) {
    return this.http.get<UserResponse>(this.URL + `/${id}`);
  }
}
