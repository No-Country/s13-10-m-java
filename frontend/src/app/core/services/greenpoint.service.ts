import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { greenPointResponse, greenpoint, greenpointDTO } from '../models/greenpoint.model';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root',
})
export class GreenpointService {
  private readonly URL = environment.apiUrl+"/api/puntosVerde";

  constructor(private http: HttpClient) {}
  /**
   * this method returns an array observable of greenpoints
   * @returns {Observable<greenpoint>}
   */
  getAllGreenpoints(): Observable<greenpoint[]> {
    return new Observable<greenpoint[]>((observer) => {
      fetch('assets/dummyData.json')
        .then((res) => res.json())
        .then((data) => {
          observer.next(data);
          observer.complete();
        })
        .catch((err) => observer.error(err));
    });
    // return this.http.get<greenpoint[]>(this.URL);
  }
  createGreenpoint(data:greenpointDTO){
    return this.http.post<greenPointResponse>(this.URL, data);
  }
}
