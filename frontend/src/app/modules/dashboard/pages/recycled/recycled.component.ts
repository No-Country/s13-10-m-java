import { Component, OnInit, Input } from '@angular/core';
import { SelectedGreenPointResponse } from '@models/greenpoint.model';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { GreenpointService } from '@services/greenpoint.service';

@Component({
  selector: 'app-recycled',
  templateUrl: './recycled.component.html',
  styleUrls: ['./recycled.component.scss']
})
export class RecycledComponent implements OnInit {

  user: UserResponse | null = null;
  greenpoints: SelectedGreenPointResponse[] = [];
  showDetails: boolean[] = []

  constructor(
    private readonly authService: AuthService,
    private readonly greenpointService: GreenpointService
  ) { }

  ngOnInit(): void {
    this.authService.userLogged$.subscribe(res => this.user = res);
    this.greenpointService.getAllGreenpoints().subscribe({
      next: (res) => {
        this.greenpoints = res;
        this.greenpoints.forEach((greenpoint) => {
          // console.log('Reciclajes del punto verde:', greenpoint);
          greenpoint
        });
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  toggleDetails(index: number): void {
    this.showDetails[index] = !this.showDetails[index];
  }

}
