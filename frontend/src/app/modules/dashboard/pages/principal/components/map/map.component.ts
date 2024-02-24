import {
  Component,
  ElementRef,
  Input,
  SimpleChange,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import * as L from 'leaflet';
import { greenpoint } from '@models/greenpoint.model';
import { MapService } from '@services/map.service';

@Component({
  selector: 'app-customMap',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss'],
})
export class CustomMapComponent {
  @ViewChild('mapContainer') mapContainer!: ElementRef;
  @Input() greenPoints: greenpoint[] = [];
  @Input() filters = '';
  markers: L.Marker[] = [];
  map!: L.Map;

  constructor(private mapService: MapService) {}

  ngAfterViewInit() {
    this.mapService.createMap(this.mapContainer.nativeElement).then((map) => {
      this.map = map;
      this.greenPoints.forEach((point) => this.addMarkerToMap(point));
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['greenPoints'] && this.map) {
      this.greenPoints.forEach((point) => this.addMarkerToMap(point));
    }
    if (changes['filters']) {
      this.filterMarkers(this.filters);
    }
  }

  addMarkerToMap({ lat, lng, nombre, direccion, type }: greenpoint) {
    let iconUrl = '';
    switch (type) {
      case 'vidrio':
        iconUrl = 'assets/img/pin-green.svg';
        break;
      case 'plastico-metal':
        iconUrl = 'assets/img/pin-yellow.svg';
        break;
      case 'papel':
        iconUrl = 'assets/img/pin-blue.svg';
        break;
      default:
        iconUrl = 'assets/img/pin-gray.svg';
        break;
    }
    const icon = this.mapService.createIcon(iconUrl);
    const marker = this.mapService.createMarker([lat, lng], icon);
    marker
      .addTo(this.map)
      .bindPopup(`${nombre}, en ${direccion}`)
      .on('click', () => marker.openPopup());
    this.markers.push(marker);
  }

  private filterMarkers(filter: string) {
    this.cleanMarkers();

    if (filter === 'todos') {
      this.greenPoints.forEach((point) => this.addMarkerToMap(point));
      return;
    }
    let filteredData = this.greenPoints.filter((data) => data.type === filter);
    filteredData.forEach((point) => this.addMarkerToMap(point));
  }

  private cleanMarkers() {
    this.markers.forEach((marker) => {
      marker.remove();
    });
    this.markers = [];
  }
}
