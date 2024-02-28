import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { position } from '@models/address.model';
import { StaticData } from '@utils/staticData';
import { validatorOpeningHour } from '@utils/validator';

type formErrorMessage = {
  [prop: string]: string;
};

@Component({
  selector: 'app-form-greenpoint',
  templateUrl: './form-greenpoint.component.html',
  styleUrls: ['./form-greenpoint.component.scss'],
  providers: [StaticData],
})
export class FormGreenpointComponent {
  days !:string[];
  recicledTypes !:string[];

  form!: FormGroup;
  errorsMessage: formErrorMessage = {};
  @Output() emitFormData = new EventEmitter<FormGroup>()
  @Input() localization !: position;

  constructor(private fb: FormBuilder, private data: StaticData) {}

  ngOnInit() {
    this.initForm();
    this.days = this.data.days;
    this.recicledTypes = this.data.recicledTypes;
  }

  ngOnChanges(changes:SimpleChanges){
    if(changes['localization'] && this.form){
      this.form.setValue({
        ...this.form.value,
        ...this.localization,
      });
    }
  }

  sendForm(){
    this.ValidateForm();
    if (this.form.invalid) {
      console.log('Un campo es invalido', this.form.value);
      return;
    }
    this.emitFormData.emit(this.form)
    this.form.reset();
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
          Validators.minLength(10),
          Validators.maxLength(10),
        ],
      ],
      openTime: ['08:00', [Validators.required, validatorOpeningHour]],
      closeTime: ['20:00', [Validators.required, validatorOpeningHour]],
      lunes:[true],
      martes:[true],
      miercoles:[true],
      jueves:[true],
      viernes:[true],
      sabado:[true],
      domingo:[true],
      papelcarton: [true],
      plastico: [false],
      vidrio: [false],
      metal: [false],
      address: ['', [Validators.required]],
      lat: ['', [Validators.required]],
      lng: ['', [Validators.required]],
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

  validateTime() {
    const openTimeControl = this.form.get('openTime')!;
    const closeTimeControl = this.form.get('closeTime')!;

    openTimeControl.updateValueAndValidity();
    closeTimeControl.updateValueAndValidity();
    this.validateField('openTime');
    this.validateField('closeTime');
  }

  onlyKeyNumber(event: KeyboardEvent) {
    const numberRegex = /^[0-9]+$/;
    if (!numberRegex.test(event.key)) {
      event.preventDefault();
    }
  }

  ValidateForm() {
    this.validateField('name');
    this.validateField('dni');
    this.validateField('phone');
    this.validateTime();
    this.validateField('lat');
    this.form.markAllAsTouched();
  }
}
