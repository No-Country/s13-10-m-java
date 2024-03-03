import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecycleService {

  private readonly URL = environment.apiUrl + '/api/reciclaje'
  constructor(private http: HttpClient) { }

  getRecycle(id: string | undefined) {
    return this.http.get<any[]>(this.URL + `/${id}`);
  }

}
