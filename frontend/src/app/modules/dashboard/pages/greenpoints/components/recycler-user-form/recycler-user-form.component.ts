import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RecyclingService } from '@services/recycling.service';
import { GeneralValidator, emailValidator } from '@utils/validator';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-recycler-user-form',
  templateUrl: './recycler-user-form.component.html',
  styleUrls: ['./recycler-user-form.component.scss'],
})
export class RecyclerUserFormComponent {
  form: FormGroup;
  @Input() idPuntoVerde: string | undefined = '';
  @Output() closeEmitter = new EventEmitter<boolean>(false);
  @Output() successEmitter = new EventEmitter<boolean>(true);

  constructor(
    private readonly fb: FormBuilder,
    private readonly recyclingService: RecyclingService,
    private readonly validator: GeneralValidator
  ) {
    this.form = this.fb.group({
      emailUsuario: ['', [Validators.required, emailValidator]],
      descripcion: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.form.invalid) {
      return this.form.markAllAsTouched();
    }

    const request = {
      ...this.form.value,
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
