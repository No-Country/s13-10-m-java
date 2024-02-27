import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StreetMapResponse } from '@models/streetMap.model';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StreetService {
  private readonly urlTemplate = "https://nominatim.openstreetmap.org/reverse?lat=<value>&lon=<value>&format=json&addressdetails=1";
  constructor(private http:HttpClient) { }

  getStreet(position:L.LatLng){
    const url = this.createURL(position);
    return this.http.get<StreetMapResponse>(url).pipe(
      map(({display_name}:StreetMapResponse)=>({ display_name }) ));
  }

  private createURL(position:L.LatLng){
    const uri = this.urlTemplate.replace("<value>",position.lat+"")
                        .replace("<value>",position.lng+"")
    return uri;
  }
}
