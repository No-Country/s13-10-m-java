import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { IAuthRegister } from '@models/auth.model';
import { UserResponse } from '@models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly UrlUser = environment.apiUrl+"/api/usuario"
  //private readonly UrlImage = environment.apiUrl+"/img"
  constructor(private http:HttpClient) { }

  getUser(id:string){
    return this.http.get<UserResponse>(this.UrlUser+`/${id}`)
  }
  
  updateUser(id:string,req: IAuthRegister) {
    return this.http.put(this.UrlUser+`/${id}`, req);
  }
  
  /* 
  postUser(id:string){
    return this.http.get<UserResponse>(this.UrlUser+`/${id}`)
  }
  PostImageUser(id:string){
    return this.http.get<UserResponse>(this.UrlImage+`/${id}`)
  } */
}
