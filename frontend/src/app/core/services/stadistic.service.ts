import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { stadisticResponse } from '@models/stadistic.model';

@Injectable({
  providedIn: 'root'
})
export class StadisticService {
  private readonly url = environment.apiUrl+"/resumen";
  constructor(private http:HttpClient) { }
  getStadistics(){
    return this.http.get<stadisticResponse>(this.url);
  }
}
