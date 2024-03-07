import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { UserResponse } from '@models/user.model';
import Swal from 'sweetalert2';
import { UserService } from '@services/user.service';
import { TokenService } from '@services/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  isLogged = false;
  imagen = "assets/img/user-icon.svg";
  isMenuOpen: boolean = false;
  UserResponse: UserResponse | null = null;

  constructor(
    private readonly router: Router,
    private userService: UserService,
    private tokenService:TokenService,
    
    private authService: AuthService
  ) {
    this.isLogged = !!localStorage.getItem('token');
  }

  ngOnInit(): void {
    // window.addEventListener('storage', this.handleStorageChange);
    this.authService.userLogged$.subscribe((res) => {
       this.UserResponse = res;
        console.log(res); 
    });

    const tokenData = this.tokenService.getDecodedToken();
    if(this.isLogged){
      this.userService.getUser(tokenData!.USER_ID).subscribe({
        next:(res)=>this.UserResponse=res,
        error:(err)=>console.log("erorr al obtener el user", err)
      })
    }
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  goToRegister() {
    this.router.navigateByUrl('/auth/register');
  }

  handleStorageChange(event: any) {
    console.log(event);
    if (event.key === 'token') {
      window.location.reload();
    }
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
