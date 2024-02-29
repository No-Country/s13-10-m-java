import { Injectable } from '@angular/core';
import { greenPointResponse } from '@models/greenpoint.model';
import * as L from 'leaflet';

@Injectable({
  providedIn: 'root',
})
export class MapService {
  constructor() {}

  async createMap(container: HTMLElement): Promise<L.Map> {
    const map = L.map(container);
    let currentPosition: L.LatLngExpression;

    try {
      currentPosition = await this.getCurrentPosition();
      map.setView(currentPosition, 13);
    } catch (error) {
      currentPosition = [-18, -70];
      map.setView(currentPosition, 4);
    }
    this.addTileToMap(map);

    return map;
  }

  getCurrentPosition(): Promise<L.LatLngExpression> {
    return new Promise((resolve, reject) => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          function (position) {
            let lat = position.coords.latitude;
            let lng = position.coords.longitude;
            console.log("posicion actual", [lat,lng])
            return resolve([lat, lng]);
          },
          function (error) {
            console.error(
              'Error al obtener la geolocalización:',
              error.message
            );
            reject([0, 0]);
          }
        );
      } else {
        reject([0, 0]);
      }
    });
  }

  private addTileToMap(map: L.Map) {
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution:
        '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }).addTo(map);
  }

  createMarker(position:L.LatLngExpression, icon:L.Icon){
    return L.marker(position, {icon})
  }

  createIcon(iconUrl:string) {
    return L.icon({
      iconUrl,
      iconSize: [38, 95],
      iconAnchor: [20, 60],
      popupAnchor: [-3, -35],
    });
  }
  createContent(data:greenPointResponse){
    const content =`
      <div class="grid grid-cols-4 w-[300px] gap-y-2 items-start">
        <span class="font-bold">Nombre: </span>
        <span class="col-span-3">${data.nombre}</span>
        <span class="font-bold">Tel&eacute;fono:</span>
        <span class="col-span-3">${data.telefono}</span>
        <span class="font-bold">Horario de atenci&oacute;n:</span>
        <span class="col-span-3">${data.horarioAtencion}</span>
        <span class="font-bold">D&iacute;as de atenci&oacute;n:</span>
        <span class="col-span-3">${data.diasAtencion}</span>
        <span class="font-bold">Materiales aceptados:</span>
        <span class="col-span-3">${data.materialesAceptados.join(", ")}</span>
        <span class="font-bold">Direcci&oacute;n:</span>
        <span class="col-span-3">${data.direccion}</span>
      </div>
    `;
    return content;
  }

  createCircle(position:L.LatLngExpression){
    const circle = L.circle(position, {
      color:"red",
      fillColor:"blue",
    })
  }
}
