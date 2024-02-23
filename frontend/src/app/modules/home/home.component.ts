import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  isMenuOpen: boolean = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  isLogged = false;

  constructor() {
    // Verifica si hay un token presente en el localStorage al inicializar el componente
    this.isLogged = !!localStorage.getItem('token');
  }
}
