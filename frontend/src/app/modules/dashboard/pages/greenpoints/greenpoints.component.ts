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
  isShowModal = false;

  constructor(private readonly greenpointService: GreenpointService) {  }
  ngOnInit(){
    this.getGreenpointsData()
  }
  getGreenpointsData(){
    this.greenpointService.getUserGreenpoints().subscribe({
      next: (res) => {
        console.log('res: ', res);
        this.greenpoints = res;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
  receiveModalStatus(status: boolean) {
    this.isShowModal = status;
  }

  handleSelected(index: number) {
    console.log(index);
    this.greenpoints.forEach((greenpoint, i) => {
      greenpoint.selected = index === i;
    });
    this.selectedGreenpoint = this.greenpoints[index];

    this.idPuntoVerde = this.selectedGreenpoint?.puntoVerdeId;
  }

  success(value: boolean) {
    console.log('inside the success method');
    Swal.fire({
      title: 'Registro exitoso',
      text: 'El usuario ha sido registrado exitosamente',
      icon: 'success',
    }).then(() => window.location.reload());

    // this.greenpointService.getUserGreenpoints().subscribe({
    //   next: (res) => {
    //     this.greenpoints = res;
    //   },
    //   error: (error) => {
    //     console.error('Error', error);
    //   },
    // });
  }

  deleteGreenpoint(id:string) {
    Swal.fire({
      icon:"question",
      title:"Seguro que deseas eliminar el greenpoint?",
      showCancelButton:true,
      showConfirmButton:true,
      confirmButtonColor:"red"
    })
    .then((confirm)=>{
      if(confirm.isConfirmed){
        this.greenpointService.deleteGreenpoint(id).subscribe({
          next:(res)=>{
            this.getGreenpointsData()
            console.log("greenpoint eliminado", res)
          },
          error:(err)=>console.log("error al borrar el greenpoint", err)
        })
      }
    })
  }

  showNewGreenPointModal() {
    alert('ajksd');
  }

}
