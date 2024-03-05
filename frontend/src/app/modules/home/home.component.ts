import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  isLogged = false;
  isMenuOpen: boolean = false;

  constructor(private readonly router: Router) {
    this.isLogged = !!localStorage.getItem('token');
  }

  ngOnInit(): void {
    // window.addEventListener('storage', this.handleStorageChange);
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
}
