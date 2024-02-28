import {
  Component,
  ElementRef,
  EventEmitter,
  Output,
  ViewChild,
} from '@angular/core';
import { position } from '@models/address.model';
import { MapService } from '@services/map.service';
import { StreetService } from '@services/street.service';
import { LatLng } from 'leaflet';

@Component({
  selector: 'app-register-map',
  templateUrl: './register-map.component.html',
  styleUrls: ['./register-map.component.scss'],
})
export class RegisterMapComponent {
  @ViewChild('map') mapContainer!: ElementRef;
  @Output() positionEmiter = new EventEmitter<position>();
  map!: L.Map;
  marker!: L.Marker;
  constructor(
    private mapService: MapService,
    private streetService: StreetService
  ) {}

  ngAfterViewInit() {
    this.mapService.createMap(this.mapContainer.nativeElement).then((map) => {
      this.map = map;
      this.map.on('dblclick', this.addNewMarker);
    });
  }

  private addNewMarker = (event: L.LeafletMouseEvent) => {
    if (this.marker) this.marker.remove();
    const iconUrl = 'assets/img/custom-pinmap.svg';
    const icon = this.mapService.createIcon(iconUrl);

    const position: L.LatLngExpression = event.latlng;
    this.marker = this.mapService.createMarker(position, icon);
    this.marker.bindPopup('Aquí estara el nuevo marcador');
    this.marker.addTo(this.map);
    this.streetService.getStreet(position as LatLng).subscribe({
      next: (res) => {
        const data = this.formatPosition(position, res);
        this.positionEmiter.emit(data);
      },
      error: (err) => console.log('error obteniendo la calle', err),
      complete: () => console.log('finalizo la consulta de la calle'),
    });
  };

  private formatPosition(
    position: L.LatLngExpression,
    address: { display_name: string }
  ) {
    const coordinates = position as LatLng
    return {
      lat:coordinates.lat+"",
      lng:coordinates.lng+"",
      address:address.display_name
    };
  }
}
