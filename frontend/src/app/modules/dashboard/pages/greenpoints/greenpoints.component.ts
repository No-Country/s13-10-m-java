import { Component, ViewChild } from '@angular/core';
import { SelectedGreenPointResponse } from '@models/greenpoint.model';
import { GreenpointService } from '@services/greenpoint.service';
import { RecyclerUserModalComponent } from './components/recycler-user-modal/recycler-user-modal.component';
import Swal from 'sweetalert2';

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
  idPuntoVerde: string | undefined = '';

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
    console.log(index);
    this.greenpoints.forEach((greenpoint, i) => {
      greenpoint.selected = index === i;
      this.selectedGreenpoint = index === i ? greenpoint : null;
    });

    this.idPuntoVerde = this.selectedGreenpoint?.puntoVerdeId;
  }

  success() {
    console.log('inside the success method');
    Swal.fire({
      title: 'Registro exitoso',
      text: 'El usuario ha sido registrado exitosamente',
      icon: 'success',
    });

    this.greenpointService.getAllGreenpoints().subscribe({
      next: (res) => {
        this.greenpoints = res;
      },
      error: (error) => {
        console.error('Error', error);
      },
    });
  }
}
