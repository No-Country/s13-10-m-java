import { Component, EventEmitter, Output } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { position } from '@models/address.model';
import { greenpointDTO, greenpointForm } from '@models/greenpoint.model';
import { GreenpointService } from '@services/greenpoint.service';
import { validatorOpeningHour } from '@utils/validator';

type formErrorMessage = {
  [prop: string]: string;
};

@Component({
  selector: 'app-modal-register-greenpoint',
  templateUrl: './modal-register-greenpoint.component.html',
  styleUrls: ['./modal-register-greenpoint.component.scss'],
})
export class ModalRegisterGreenpointComponent {
  @Output() closeModal = new EventEmitter<boolean>();

  readonly days = [
    'lunes',
    'martes',
    'miercoles',
    'jueves',
    'viernes',
    'sabado',
    'domingo',
  ];
  readonly recicledTypes = [
    'PLASTICO',
    'PAPELCARTON',
    'VIDRIO',
    'METAL',
  ];
  form!: FormGroup;
  errorsMessage: formErrorMessage = {};

  constructor(
    private fb: FormBuilder,
    private greenpointService:GreenpointService
    ) {}
  ngOnInit() {
    this.initForm();
  }

  createGreenpoint() {
    this.ValidateForm()
    if(this.form.invalid){
      console.log("Un campo es invalido", this.form.value)
      return;
    };
    const newGreenpoint = this.parseFormToGreenpoint(this.form);

    this.greenpointService.createGreenpoint(newGreenpoint).subscribe({
      next:(res)=>console.log(res),
      error:(err)=>console.log("ocurrio un error al crear el greepoint", err)
    })
  }

  initForm() {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(5)]],
      dni: [
        '',
        [
          Validators.required,
          Validators.maxLength(15),
          Validators.minLength(6),
        ],
      ],
      phone: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(15),
        ],
      ],
      openTime: ['08:00', [Validators.required, validatorOpeningHour]],
      closeTime: ['20:00', [Validators.required, validatorOpeningHour]],
      openDay: ["lunes", [Validators.required]],
      closeDay: ["domingo", [Validators.required]],
      papelcarton:[true],
      plastico:[false],
      vidrio:[false],
      metal:[false],
      address: ['', [Validators.required]],
      lat: ['', [Validators.required]],
      lng: ['', [Validators.required]],
    });
  }
  receivePosition(position: position) {
    this.form.setValue({
      ...this.form.value,
      ...position,
    });
  }

  validateField(field: string) {
    let message = '';
    const error = this.form.controls[field].errors ?? {};
    if (error['required']) message = 'Este campo es requerido.';
    if (error['minlength']) {
      message = `Mínimo ${error['minlength'].requiredLength} caracteres.`;
    }
    if (error['maxlength']) {
      message = `Máximo ${error['maxlength'].requiredLength} caracteres.`;
    }
    if (error['invalidTime']) {
      message = `Horario Inválido`;
    }
    this.errorsMessage[field] = message;
  }
  validateTime(){
    const openTimeControl = this.form.get("openTime")!
    const closeTimeControl = this.form.get("closeTime")!

    openTimeControl.updateValueAndValidity()
    closeTimeControl.updateValueAndValidity()
    this.validateField("openTime")
    this.validateField("closeTime")
  }
  onlyKeyNumber(event: KeyboardEvent) {
    const numberRegex = /^[0-9]+$/;
    if (!numberRegex.test(event.key)) {
      event.preventDefault();
    }
  }
  ValidateForm(){
    this.validateField("name");
    this.validateField("dni")
    this.validateField("phone")
    this.validateTime()
    this.validateField("lat")
    this.form.markAllAsTouched();
  }

  parseFormToGreenpoint(formGroup : FormGroup){
    const data : greenpointForm = formGroup.value;
    let recicledType :string[]=[];
    if(data.metal) recicledType.push("METAL")
    if(data.papelcarton) recicledType.push("PAPELCARTON")
    if(data.vidrio) recicledType.push("VIDRIO")
    if(data.plastico) recicledType.push("PLASTICO")

    const parseData:greenpointDTO ={
      userId:"polla",
      nombre:data.name,
      dni:data.dni,
      telefono:data.phone,
      horarioAtencion:`Abierto de ${data.openTime} a ${data.closeTime}`,
      diasAtencion:`Atencion de ${data.openDay} a ${data.closeDay}`,
      materialesAceptados: recicledType,
      direccion:data.address,
      latitud:data.lat,
      longitud:data.lng
    }
    return parseData;
  }

  hiddenModal(){
    this.closeModal.emit(false)
  }
}
