import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { UserResponse } from '@models/user.model';
import { UserService } from '@services/user.service';
import { TokenService } from '@services/token.service';

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
    private userService: UserService,
    private tokenService:TokenService
  ) {
    this.isLogged = !!localStorage.getItem('token');
  }

  ngOnInit(): void {

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
}
