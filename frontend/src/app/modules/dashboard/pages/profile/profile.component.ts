import { Component, OnInit } from '@angular/core';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  user: UserResponse | null = null;

  constructor(private readonly authService: AuthService) {}

  ngOnInit(): void {
    this.authService.userLogged$.subscribe((res) => (this.user = res));
  }
}
