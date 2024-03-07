import { Component, OnInit } from '@angular/core';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { IRecyclingRes } from '@models/recycling.model';
import { RecyclingService } from '@services/recycling.service';

@Component({
  selector: 'app-recycled',
  templateUrl: './recycled.component.html',
  styleUrls: ['./recycled.component.scss'],
})
export class RecycledComponent implements OnInit {
  user: UserResponse | null = null;

  recyclingData: IRecyclingRes[] = [];

  constructor(
    private readonly authService: AuthService,
    private recyclingService: RecyclingService
  ) {}

  ngOnInit(): void {
    this.authService.userLogged$.subscribe((res) => {
      this.user = res;
      console.log(res?.userId);
    });

    this.recyclingService.get().subscribe(
      (data: IRecyclingRes[]) => {
        // Ordena los datos de manera descendente según la fecha
        data.sort(
          (a, b) => new Date(b.dia).getTime() - new Date(a.dia).getTime()
        );

        // Limita la cantidad de elementos mostrados a 5
        this.recyclingData = data.slice(0, 5);
      },
      (error) => {
        console.error('Error al obtener los datos de reciclaje:', error);
      }
    );
  }
}
