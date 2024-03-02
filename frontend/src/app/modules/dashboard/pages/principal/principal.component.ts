import { Component, OnInit } from '@angular/core';
import { greenPointResponse, greenpoint } from '@models/greenpoint.model';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { GreenpointService } from '@services/greenpoint.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss'],
})
export class PrincipalComponent implements OnInit {
  greenpoints: greenPointResponse[] = [];
  filter = 'todos';
  user: UserResponse | null = null;

  constructor(
    private greenpoint: GreenpointService,
    private readonly authService: AuthService
  ) {}

  ngOnInit() {
    this.getGreenpoints();
    this.authService.userLogged$.subscribe((res) => (this.user = res));
  }

  getGreenpoints() {
    this.greenpoint.getAllGreenpoints().subscribe({
      next: (res) => {
        console.log('get greenpoints', res);
        this.greenpoints = res;
      },
      error: (err) => console.error('ocurrio un error', { err }),
    });
  }
}
