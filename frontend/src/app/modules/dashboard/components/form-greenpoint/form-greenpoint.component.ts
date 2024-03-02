import {
  Component,
  EventEmitter,
  Input,
  Output,
  SimpleChanges,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { position } from '@models/address.model';
import { StaticData } from '@utils/staticData';
import {
  onlyAlphanumerics,
  onlyNumbers,
  phoneValidator,
  validatorOpeningHour,
} from '@utils/validator';

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
  days!: string[];
  recicledTypes!: string[];

  form!: FormGroup;
  errorsMessage: formErrorMessage = {};
  invalidDay = false;
  invalidType = false;
  @Output() emitFormData = new EventEmitter<FormGroup>();
  @Input() localization!: position;

  constructor(private fb: FormBuilder, private data: StaticData) {}

  ngOnInit() {
    this.initForm();
    this.days = this.data.days;
    this.recicledTypes = this.data.recicledTypes;
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['localization'] && this.form) {
      this.form.setValue({
        ...this.form.value,
        ...this.localization,
      });
    }
  }

  sendForm() {
    this.ValidateForm();
    this.validateDay();
    this.validateType();
    if (this.form.invalid || this.invalidDay || this.invalidType) {
      console.log('Un campo es invalido', this.form.value);
      return;
    }
    this.emitFormData.emit(this.form);
    //this.form.reset();
  }

  initForm() {
    this.form = this.fb.group({
      name: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(60),
          onlyAlphanumerics,
        ],
      ],
      dni: [
        '',
        [
          Validators.required,
          Validators.maxLength(15),
          Validators.minLength(6),
          onlyNumbers,
        ],
      ],
      phone: [
        '+549',
        [
          Validators.required,
          Validators.minLength(14),
          Validators.maxLength(14),
          phoneValidator,
        ],
      ],
      openTime: ['08:00', [Validators.required, validatorOpeningHour]],
      closeTime: ['20:00', [Validators.required, validatorOpeningHour]],
      lunes: [true],
      martes: [true],
      miercoles: [true],
      jueves: [true],
      viernes: [true],
      sabado: [true],
      domingo: [true],
      papelcarton: [true],
      plastico: [false],
      vidrio: [false],
      metal: [false],
      address: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(255),
        ],
      ],
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
    if (error['specialChar']) {
      message = 'Solo caracteres alfanuméricos.';
    }
    if (error['noNumericChar']) {
      message = 'Solo caracteres numéricos.';
    }
    if (error['invalidPhone']) {
      message = 'el numero debe comenzar con +549...';
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
  validateDay() {
    const daysControl = [
      this.form.get('lunes')!,
      this.form.get('martes')!,
      this.form.get('miercoles')!,
      this.form.get('jueves')!,
      this.form.get('viernes')!,
      this.form.get('sabado')!,
      this.form.get('domingo')!,
    ];
    this.invalidDay = !daysControl.some((day) => day.value === true);
  }

  validateType() {
    const typesControl = [
      this.form.get('papelcarton')!,
      this.form.get('plastico')!,
      this.form.get('metal')!,
      this.form.get('vidrio')!,
    ];
    const isValidate = typesControl.some(
      (typeControl) => typeControl.value === true
    );
    this.invalidType = !isValidate;
  }

  onlyKeyNumber(event: KeyboardEvent) {
    const numberRegex = /^[0-9\+]+$/;
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
    this.validateField('address');
    this.form.markAllAsTouched();
  }
}
