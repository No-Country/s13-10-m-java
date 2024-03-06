import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IRecyclingReq } from '@models/recycling.model';
import { RecyclingService } from '@services/recycling.service';
import { StaticData } from '@utils/staticData';
import { GeneralValidator, emailValidator } from '@utils/validator';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-recycler-user-form',
  templateUrl: './recycler-user-form.component.html',
  styleUrls: ['./recycler-user-form.component.scss'],
  providers: [StaticData],
})
export class RecyclerUserFormComponent {
  form: FormGroup;
  materiales: string[] = [];
  @Input() idPuntoVerde: string | undefined = '';
  @Output() closeEmitter = new EventEmitter<boolean>(false);
  @Output() successEmitter = new EventEmitter<boolean>();

  constructor(
    private readonly fb: FormBuilder,
    private readonly recyclingService: RecyclingService,
    private readonly validator: GeneralValidator,
    private readonly staticData: StaticData
  ) {
    this.form = this.fb.group({
      emailUsuario: ['', [Validators.required, emailValidator]],
      plastico: [false],
      papelcarton: [false],
      vidrio: [false],
      metal: [false],
      descripcion: ['', Validators.required],
    });
    this.materiales = this.staticData.recicledTypes;
  }

  private toValue() {
    const materiales = [
      this.form.get('plastico'),
      this.form.get('vidrio'),
      this.form.get('metal'),
      this.form.get('papelcarton'),
    ];

    const materialesRecibidos: string[] = [];

    materiales.forEach((material) => {
      if (material?.value) {
        materialesRecibidos.push(`${material}`);
      }
    });

    console.log(materialesRecibidos);

    return materialesRecibidos;
  }

  onSubmit() {
    if (this.form.invalid) {
      return this.form.markAllAsTouched();
    }

    const request: IRecyclingReq = {
      emailUsuario: this.form.value.emailUsuario,
      descripcion: this.form.value.descripcion,
      materialesRecibidos: this.toValue(),
      idPuntoVerde: this.idPuntoVerde,
    };

    this.closeEmitter.emit(true);

    this.recyclingService.save(request).subscribe({
      next: () => {
        this.form.reset();
        console.log('emitiendo');
        this.successEmitter.emit(true);
        console.log('emitido con éxito');
      },
      error: (error) => {
        console.error('Error:', error);

        Swal.fire({
          title: 'Ha ocurrido un error...',
          text: error.error,
          icon: 'error',
        });
      },
    });
  }

  get emailErrorMessage() {
    const control = 'emailUsuario';

    if (this.hasError(control, 'required')) {
      return 'El email es requerido';
    }

    if (this.hasError(control, 'email')) {
      return 'Ingresa un email válido';
    }

    return '';
  }

  get descriptionErrorMessage() {
    const control = 'descripcion';

    if (this.hasError(control, 'required')) {
      return 'La descripción es requerida';
    }

    return '';
  }

  private hasError(control: string, error: string) {
    return this.validator.hasError(this.form, control, error);
  }
}
