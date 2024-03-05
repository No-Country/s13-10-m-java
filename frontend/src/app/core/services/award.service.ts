import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { award as ModalAward } from '@models/award.model'

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AwardService {
  private readonly URL = environment.apiUrl + '/api/premio';

  constructor(
    private http: HttpClient,
  ) {}
  getAllPrizes():Observable<ModalAward[]>{
    return this.http.get<ModalAward[]>(this.URL)
  }
}
