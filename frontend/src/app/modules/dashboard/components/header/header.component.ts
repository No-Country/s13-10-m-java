import { Component, EventEmitter, Output } from '@angular/core';
import { tokenData } from '@models/token.model';
import { TokenService } from '@services/token.service';
import Swal from 'sweetalert2';
import { AuthService } from '@services/auth.service';
import { UserResponse } from '@models/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  @Output() emitShowSidebar = new EventEmitter<boolean>();
  @Output() emitShowModal = new EventEmitter<boolean>();
  tokenData!: tokenData | null | undefined;
  
  UserResponse: UserResponse | null = null;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.tokenData = this.tokenService.getDecodedToken();
      this.authService.userLogged$.subscribe((res) => (this.UserResponse = res));
  }
  showSidebar() {
    this.emitShowSidebar.emit(true);
  }

  showModal() {
    this.emitShowModal.emit(true);
  }
  showOptions() {
    var boton = document.getElementById('dropdownDivider');
    boton?.classList.toggle('hidden');
  }
  onLogout() {
    Swal.fire({
      title: 'Deseas cerrar sesion?',
      text: 'Seras dirigido a nuestro inicio de sesion!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Quiero salir!',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.authService.logout();
      }
    });
  }
}
