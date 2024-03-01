import { Location } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { position } from '@models/address.model';
import { greenpointDTO, greenpointForm } from '@models/greenpoint.model';
import { GreenpointService } from '@services/greenpoint.service';
import { TokenService } from '@services/token.service';
import { fadeAnimation } from 'app/core/animations/customAnimation';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modal-register-greenpoint',
  templateUrl: './modal-register-greenpoint.component.html',
  styleUrls: ['./modal-register-greenpoint.component.scss'],
  animations: [fadeAnimation],
})
export class ModalRegisterGreenpointComponent {
  @Output() closeModal = new EventEmitter<boolean>();
  localization!: position;
  form!: FormGroup;

  constructor(
    private greenpointService: GreenpointService,
    private tokenService: TokenService,
    private router: Router
  ) {}
  createGreenpoint(form: FormGroup) {
    const newGreenpoint = this.parseFormToGreenpoint(form);

    this.greenpointService.createGreenpoint(newGreenpoint).subscribe({
      next: (res) => {
        console.log('greepoint creado', res);
        Swal.fire({
          icon: 'success',
          title: '<strong>Punto verde creado con éxito</strong>',
        }).then(() => {
          window.location.reload();
        });
      },
      error: (err) =>
        console.log('ocurrio un error al crear el greepoint', err),
    });
  }

  receivePosition(position: position) {
    this.localization = position;
  }

  parseFormToGreenpoint(formGroup: FormGroup) {
    const tokenData = this.tokenService.getDecodedToken()!;
    const data: greenpointForm = formGroup.value;
    let recicledType: string[] = [];
    if (data.metal) recicledType.push('METAL');
    if (data.papelcarton) recicledType.push('PAPELCARTON');
    if (data.vidrio) recicledType.push('VIDRIO');
    if (data.plastico) recicledType.push('PLASTICO');

    const parseData: greenpointDTO = {
      userId: tokenData.USER_ID,
      nombre: data.name,
      dni: data.dni,
      telefono: '549' + data.phone,
      horarioAtencion: `Abierto de ${data.openTime} a ${data.closeTime}`,
      diasAtencion: `Atencion de ${data.openDay} a ${data.closeDay}`,
      materialesAceptados: recicledType,
      direccion: data.address,
      latitud: data.lat,
      longitud: data.lng,
    };
    return parseData;
  }

  hiddenModal() {
    this.closeModal.emit(false);
  }
}