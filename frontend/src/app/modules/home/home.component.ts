import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { UserResponse } from '@models/user.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  isLogged = false;
  isMenuOpen: boolean = false;
  UserResponse: UserResponse | null = null;

  constructor(
    private readonly router: Router,
    private authService: AuthService
  ) {
    this.isLogged = !!localStorage.getItem('token');
  }

  ngOnInit(): void {
    // window.addEventListener('storage', this.handleStorageChange);
    this.authService.userLogged$.subscribe((res) => {this.UserResponse = res; console.log(res)});
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
