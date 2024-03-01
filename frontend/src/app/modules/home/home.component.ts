import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(
    private readonly router: Router,
    private readonly authService: AuthService
  ) {
    this.isLogged = !!localStorage.getItem('token');
  }

  ngOnInit(): void {
    this.authService.getUserLogged().subscribe();
  }
  isMenuOpen: boolean = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  isLogged = false;

  goToRegister() {
    this.router.navigateByUrl('/auth/register');
  }
}
