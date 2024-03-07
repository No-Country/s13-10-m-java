import { Component, OnInit } from '@angular/core';
import { greenPointResponse, greenpoint } from '@models/greenpoint.model';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { GreenpointService } from '@services/greenpoint.service';
import { StaticData } from '@utils/staticData';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss'],
  providers:[StaticData]
})
export class PrincipalComponent implements OnInit {
  greenpoints: greenPointResponse[] = [];
  filter = 'todos';
  filters: string[] = [];
  user: UserResponse | null = null;

  constructor(
    private greenpoint: GreenpointService,
    private readonly authService: AuthService,
    private readonly appData: StaticData
  ) {}

  ngOnInit() {
    this.getGreenpoints();
    this.authService.userLogged$.subscribe((res) => (this.user = res));
    this.filters = this.appData.recicledTypes;
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
