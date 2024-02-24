import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  isMenuOpen: boolean = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  isLogged = false;

  constructor(private readonly router: Router) {
    // Verifica si hay un token presente en el localStorage al inicializar el componente
    this.isLogged = !!localStorage.getItem('token');
  }

  goToRegister() {
    this.router.navigateByUrl('/auth/register');
  }
}
