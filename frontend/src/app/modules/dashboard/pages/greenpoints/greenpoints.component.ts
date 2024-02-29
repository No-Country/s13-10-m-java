import { Component } from '@angular/core';
import { SelectedGreenPointResponse } from '@models/greenpoint.model';
import { GreenpointService } from '@services/greenpoint.service';

@Component({
  selector: 'app-greenpoints',
  templateUrl: './greenpoints.component.html',
  styleUrls: ['./greenpoints.component.scss'],
})
export class GreenpointsComponent {
  greenpoints: SelectedGreenPointResponse[] = [];
  selectedGreenpoint: SelectedGreenPointResponse | null = null;

  constructor(private readonly greenpointService: GreenpointService) {
    this.greenpointService.getAllGreenpoints().subscribe({
      next: (res) => {
        this.greenpoints = res;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  handleSelected(index: number) {
    this.greenpoints.forEach((greenpoint, i) => {
      greenpoint.selected = index === i;
      this.selectedGreenpoint = index === i ? greenpoint : null;
    });
  }
}
