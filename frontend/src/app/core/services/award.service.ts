import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '@environments/environment';
import { award as ModalAward } from '@models/award.model';
import { TokenService } from '@services/token.service';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AwardService {
  private readonly URL = environment.apiUrl + '/api/premio';

  private readonly URLPRIZE = environment.apiUrl + '/canjes';
  constructor(private http: HttpClient, private tokenService: TokenService) {}

  getAllPrizes(): Observable<ModalAward[]> {
    return this.http.get<ModalAward[]>(this.URL);

  }

  postPrize(id: string): Observable<any> {

    // Construye el encabezado con el token
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.tokenService.getToken()}`
    });

    // Realiza la solicitud POST
    return this.http.post<any>(`${this.URLPRIZE}/${id}`, null, { headers: headers });
  }
}
