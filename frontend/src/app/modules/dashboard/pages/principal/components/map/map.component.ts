import {
  Component,
  ElementRef,
  Input,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import * as L from 'leaflet';
import { greenPointResponse } from '@models/greenpoint.model';
import { MapService } from '@services/map.service';

@Component({
  selector: 'app-customMap',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss'],
})
export class CustomMapComponent {
  @ViewChild('mapContainer') mapContainer!: ElementRef;
  @Input() greenPoints: greenPointResponse[] = [];
  @Input() filters = '';
  markers: L.Marker[] = [];
  map!: L.Map;

  constructor(private mapService: MapService) {}

  ngAfterViewInit() {
    this.mapService.createMap(this.mapContainer.nativeElement).then((map) => {
      this.map = map;
      this.greenPoints.forEach((point) => this.addMarkerToMap(point));
      this.addCurrentPositionMarkerToMap();
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

  addMarkerToMap( data: greenPointResponse) {
    let iconUrl = 'assets/img/custom-pinmap.svg';
    const lat = parseFloat(data.latitud)
    const lng = parseFloat(data.longitud)
    const icon = this.mapService.createIcon(iconUrl);
    const marker = this.mapService.createMarker([lat, lng], icon);
    const content = this.mapService.createContent(data);
    marker
      .addTo(this.map)
      .bindPopup(content)
      .on('click', () => marker.openPopup());
    this.markers.push(marker);
  }

  addCurrentPositionMarkerToMap(){
    const iconUrl = "assets/img/human-male.svg";
    const icon = this.mapService.createIcon(iconUrl);
    this.mapService.getCurrentPosition()
      .then((position)=>{
        const marker = this.mapService.createMarker(position, icon);
        marker.addTo(this.map).bindPopup("Tu estas aquí.")
      })
      .catch(()=>{
        console.log("no se pudo obtener la posicion")
      })
  }

  private filterMarkers(filter: string) {
    this.cleanMarkers();
    if (filter === 'todos') {
      this.greenPoints.forEach((point) => this.addMarkerToMap(point));
      return;
    }
    let filteredData = this.greenPoints.filter((data) => data.materialesAceptados.includes(filter));
    filteredData.forEach((point) => this.addMarkerToMap(point));
  }

  private cleanMarkers() {
    this.markers.forEach((marker) => {
      marker.remove();
    });
    this.markers = [];
  }
}
