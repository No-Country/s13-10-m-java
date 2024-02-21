import { Component } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  ngAfterViewInit() {
    this.initMap();
  }

  async initMap(){
    let currentPosition:L.LatLngExpression;
    let map = L.map('map');
    try {
      currentPosition = await this.getCurrentPosition();
      map.setView(currentPosition, 13);
    } catch (error) {
      map.setView([-18,-70], 4)
    }
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution:
        '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }).addTo(map);
  }

  getCurrentPosition():Promise<L.LatLngExpression> {
    return new Promise((resolve, reject)=>{
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          function (position) {
            let lat = position.coords.latitude;
            let lng = position.coords.longitude;
           return resolve([lat, lng]);
          },
          function (error) {
            console.error('Error al obtener la geolocalización:', error.message);
            reject([0,0]);
          }
        );
      }else{
        reject([0,0])
      }
    })
  }
}
