import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { greenPointResponse, greenpointDTO } from '../models/greenpoint.model';
import { environment } from '@environments/environment';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class GreenpointService {
  private readonly URL = environment.apiUrl + '/api/puntosVerde';

  constructor(
    private http: HttpClient,
    private readonly tokenService: TokenService
  ) {}
  /**
   * this method returns an array observable of greenpoints
   * @returns {Observable<greenPointResponse[]>}
   */
  getAllGreenpoints(): Observable<greenPointResponse[]> {
    // return new Observable<greenpoint[]>((observer) => {
    //   fetch('assets/dummyData.json')
    //     .then((res) => res.json())
    //     .then((data) => {
    //       observer.next(data);
    //       observer.complete();
    //     })
    //     .catch((err) => observer.error(err));
    // });
    return this.http.get<greenPointResponse[]>(this.URL);
  }

  createGreenpoint(data: greenpointDTO) {
    return this.http.post<greenPointResponse>(this.URL, data);
  }

  getUserGreenpoints() {
    return this.http.get<greenPointResponse[]>(
      `${this.URL}/usuario/${this.tokenService.getDecodedToken()?.USER_ID}`
    );
  }

  deleteGreenpoint(id:string){
    return this.http.delete(this.URL+`/${id}`)
  }
}
