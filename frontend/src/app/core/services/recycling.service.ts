import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { IRecyclingReq, IRecyclingRes } from '@models/recycling.model';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class RecyclingService {
  private readonly url: string = `${environment.apiUrl}/api/reciclaje`;

  constructor(
    private readonly http: HttpClient,
    private readonly tokenService: TokenService
  ) { }

  save(req: IRecyclingReq) {
    return this.http.post<IRecyclingRes>(`${this.url}/save`, req);
  }

  get() {
    return this.http.get<IRecyclingRes[]>(
      `${this.url}/${this.tokenService.getDecodedToken()?.USER_ID}`
    );
  }
}
