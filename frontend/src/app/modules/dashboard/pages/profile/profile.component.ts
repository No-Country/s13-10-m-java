import { Component } from '@angular/core';
import { UserResponse } from '@models/user.model';
import { UserService } from '@services/user.service';
import { tokenData } from '@models/token.model';
import { TokenService } from '@services/token.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  GeneralValidator,
  emailValidator,
  numericSpecialCharacterValidator,
  passwordValidator,
} from '@utils/validator';
import { AuthService } from '@services/auth.service';
import Swal from 'sweetalert2';
import { UserImage } from '@models/image.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent {
  form: FormGroup;

  UserResponse: UserResponse | null = null;
  tokenData!: tokenData;

  showPassword: boolean = false;
  showRepeatPassword: boolean = false;

  constructor(
    private readonly fb: FormBuilder,
    private readonly validator: GeneralValidator,

    private tokenService: TokenService,
    private userService: UserService,
    private readonly authService: AuthService
  ) {
    this.form = this.fb.group({
      nombre: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(60),
          numericSpecialCharacterValidator,
        ],
      ],
      apellido: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(60),
          numericSpecialCharacterValidator,
        ],
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(255),
          emailValidator,
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(128),
          passwordValidator,
        ],
      ],
      repeatPassword: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.authService.userLogged$.subscribe((res) => (this.UserResponse = res));
    this.updateImage();
  }

  updateImage() {

    const input = document.querySelector<HTMLInputElement>('#img-picker');
    const preview = document.querySelector<HTMLImageElement>('#preview');

    const miImagen = document.querySelector<HTMLInputElement>('#miImagen')!.addEventListener('click', function() {
      console.log("Clicando")
      input!.click();
      
    })
    console.log(miImagen)
  

    input!.addEventListener('change', updateImageDisplay);

    function updateImageDisplay() {
      console.log('update image');
      const file = input!.files![0];
      const reader = new FileReader();

      reader.onload = function () {
        preview!.setAttribute('src', reader.result as string);
      };

      if (file) {
        reader.readAsDataURL(file);
      }
    }
  }

  showEdit() {
    var sectionEdit = document.getElementById('edit');
    sectionEdit?.classList.toggle('hidden');
  }

  uploadImage(event: any) {
    const file = event.target!.files[0];
    if (file) {
      const formData = new FormData();
      formData.append('imagen', file);

      this.userService.PostImageUser(formData).subscribe({
        next: (res) => console.log(res),
        error(err) {
          console.log('error con la imagen', err);
        },
      });
    }
  }
  onSubmit() {
    // Evitar que el formulario se actualice al hacer submit

    if (
      this.form.invalid ||
      this.form.value.password !== this.form.value.repeatPassword
    ) {
      return this.form.markAllAsTouched();
    }

    this.userService
      .updateUser(this.UserResponse!.userId, this.form.value)
      .subscribe({
        next: () => {
          console.log(this.selectedImage!);
          Swal.fire({
            title: '¡Datos guardados exitosamente!',
            text: `Tus datos han sido actualizado`,
            icon: 'success',
          }).then(() => {
            window.location.reload();
          });
        },
        error: (error) => {
          console.log(error);
          Swal.fire({
            title: 'Ha ocurrido un error...',
            text: error.error,
            icon: 'error',
          });
        },
      });
  }
  selectedImage: UserImage | null = null;
  imageUrl: string | null = null;

  onFileSelected(event: any) {
    this.selectedImage = event.target.files[0];
  }

  handleShowPassword(): void {
    this.showPassword = !this.showPassword;
  }

  handleShowRepeatPassword(): void {
    this.showRepeatPassword = !this.showRepeatPassword;
  }

  hasError(name: string, error: string) {
    return this.validator.hasError(this.form, name, error);
  }

  get nameErrorMessage() {
    const control = 'nombre';

    if (this.hasError(control, 'required')) {
      return 'El nombre es requerido';
    }

    if (
      this.hasError(control, 'minlength') ||
      this.hasError(control, 'maxlength')
    ) {
      return 'Debe tener entre 2 y 60 caracteres';
    }

    if (this.hasError(control, 'numericSpecialCharacter')) {
      return 'No se admiten caracteres numéricos ni especiales';
    }

    return '';
  }

  get lastNameErrorMessage() {
    const control = 'apellido';

    if (this.hasError(control, 'required')) {
      return 'El apellido es requerido';
    }

    if (this.hasError(control, 'numericSpecialCharacter')) {
      return 'No se admiten caracteres numéricos ni especiales';
    }

    if (
      this.hasError(control, 'minlength') ||
      this.hasError(control, 'maxlength')
    ) {
      return 'Debe tener entre 2 y 60 caracteres';
    }
    return '';
  }

  get emailErrorMessage() {
    if (this.hasError('email', 'required')) {
      return 'El email es requerido';
    }

    if (this.hasError('email', 'email')) {
      return 'Ingresa un email válido';
    }

    if (
      this.hasError('email', 'minlength') ||
      this.hasError('email', 'maxlength')
    ) {
      return 'Debe tener entre 8 y 255 caracteres';
    }

    return '';
  }

  get passwordErrorMessage() {
    if (this.hasError('password', 'required')) {
      return 'La contraseña es requerida';
    }

    if (
      this.hasError('password', 'minlength') ||
      this.hasError('password', 'maxlength')
    ) {
      return 'Debe tener entre 8 y 128 caracteres';
    }

    if (this.hasError('password', 'password')) {
      return 'Debe contener al menos 1 minúscula, 1 mayúscula, 1 número y 1 caracter especial';
    }

    return '';
  }

  get repeatPasswordErrorMessage() {
    if (this.hasError('repeatPassword', 'required')) {
      return 'Por favor, confirma tu contraseña';
    }

    if (
      this.form.controls['repeatPassword'].touched &&
      this.form.value.password !== this.form.value.repeatPassword
    ) {
      return 'Las contraseñas no coinciden';
    }

    return '';
  }
}
