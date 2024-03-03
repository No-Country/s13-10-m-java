import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { userData } from '@models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly URL = environment.apiUrl+"/api/usuario"
  constructor(private http:HttpClient) { }

  getUser(id:string){
    return this.http.get<userData>(this.URL+`/${id}`)
  }
}