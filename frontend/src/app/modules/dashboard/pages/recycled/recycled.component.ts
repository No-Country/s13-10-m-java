import { Component, OnInit } from '@angular/core';
import { SelectedGreenPointResponse } from '@models/greenpoint.model';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { IRecyclingRes } from '@models/recycling.model';
import { RecyclingService } from '@services/recycling.service'

@Component({
  selector: 'app-recycled',
  templateUrl: './recycled.component.html',
  styleUrls: ['./recycled.component.scss']
})
export class RecycledComponent implements OnInit {

  user: UserResponse | null = null;
  // showDetails: boolean[] = []

  recyclingData: IRecyclingRes[] = [];

  constructor(
    private readonly authService: AuthService,
    private recyclingService: RecyclingService
  ) { }

  ngOnInit(): void {
    this.authService.userLogged$.subscribe(res => { this.user = res, console.log(res?.userId)});

    this.recyclingService.get().subscribe(
      (data: IRecyclingRes[]) => {
        this.recyclingData = data;
        console.log(data)
      },
      (error) => {
        console.error('Error al obtener los datos de reciclaje:', error);
      }
    );
  }

  // toggleDetails(index: number): void {
  //   this.showDetails[index] = !this.showDetails[index];
  // }

}
