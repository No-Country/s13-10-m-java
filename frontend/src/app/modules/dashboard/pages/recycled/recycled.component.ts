import { Component, OnInit, Input } from '@angular/core';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { RecycleService } from '@services/recycle.service';

@Component({
  selector: 'app-recycled',
  templateUrl: './recycled.component.html',
  styleUrls: ['./recycled.component.scss']
})
export class RecycledComponent implements OnInit {


  // user: UserResponse | null = null;

  // constructor(private readonly authService: AuthService,
  //   private recycledService: RecycleService) { }

  // @Input() reciclajeId: string | undefined;
  // reciclajeData: any[] = [];
  // ngOnInit() {
  //   this.authService.userLogged$.subscribe(res => this.user = res)

  //   if (this.reciclajeId) {
  //     this.recycledService.getRecycle(this.reciclajeId)
  //       .subscribe(
  //         data => {
  //           this.reciclajeData = data;
  //           console.log('Datos de reciclaje:', this.reciclajeData);
  //         },
  //         error => {
  //           console.error('Error al obtener los datos de reciclaje:', error);
  //         }
  //       );
  //   } else {
  //     console.error('ID de reciclaje no proporcionado.');
  //   }
  // }

  user: UserResponse | null = null;
  reciclajeData: any[] = [];

  constructor(
    private readonly authService: AuthService,
    private recycledService: RecycleService
  ) { }

  ngOnInit(): void {
    this.authService.userLogged$.subscribe(res => {
      this.user = res;
      if (this.user && this.user.userId) {
        this.getRecycleData(this.user.userId);
      }
    });
  }

  getRecycleData(userId: string): void {
    this.recycledService.getRecycle(userId)
      .subscribe(
        data => {
          this.reciclajeData = data;
          console.log('Datos de reciclaje:', this.reciclajeData);
        },
        error => {
          console.error('Error al obtener los datos de reciclaje:', error);
        }
      );
  }

}
