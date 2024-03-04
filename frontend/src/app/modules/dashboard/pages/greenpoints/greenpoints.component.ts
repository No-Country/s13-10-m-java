import { Component, ViewChild } from '@angular/core';
import { SelectedGreenPointResponse } from '@models/greenpoint.model';
import { GreenpointService } from '@services/greenpoint.service';
import { RecyclerUserModalComponent } from './components/recycler-user-modal/recycler-user-modal.component';

@Component({
  selector: 'app-greenpoints',
  templateUrl: './greenpoints.component.html',
  styleUrls: ['./greenpoints.component.scss'],
})
export class GreenpointsComponent {
  @ViewChild(RecyclerUserModalComponent)
  recyclerUserModal!: RecyclerUserModalComponent;
  greenpoints: SelectedGreenPointResponse[] = [];
  selectedGreenpoint: SelectedGreenPointResponse | null = null;

  constructor(private readonly greenpointService: GreenpointService) {
    this.greenpointService.getAllGreenpoints().subscribe({
      next: (res) => {
        console.log(res);
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
