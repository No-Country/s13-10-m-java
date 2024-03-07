import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { IAuthRegisterReq } from '@models/auth.model';
import { UserImage, UserImageResponse } from '@models/image.model';
import { UserResponse } from '@models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly UrlUser = environment.apiUrl + '/api/usuario';
  private readonly UrlImage = environment.apiUrl + '/img';
  constructor(private http: HttpClient) {}

  getUser(id: string) {
    return this.http.get<UserResponse>(this.UrlUser + `/${id}`);
  }

  updateUser(id: string, req: IAuthRegisterReq) {
    return this.http.put(this.UrlUser + `/${id}`, req);
  }

  PostImageUser(image: any) {
    return this.http.post<UserImageResponse>(this.UrlImage, image);
  }
}
