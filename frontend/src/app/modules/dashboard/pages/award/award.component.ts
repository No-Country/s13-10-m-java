import { Component, OnInit } from '@angular/core';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { AwardService } from '@services/award.service';
import { award as ModalAward } from '@models/award.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-award',
  templateUrl: './award.component.html',
  styleUrls: ['./award.component.scss'],
})
export class AwardComponent implements OnInit {
  user: UserResponse | null = null;
  awards: ModalAward[] = [];
  selectedAwardId: string | null = null;
  constructor(
    private award: AwardService,
    private readonly authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getAwards();
    this.authService.userLogged$.subscribe((res) => {
      this.user = res;
    });
  }

  getAwards() {
    this.award.getAllPrizes().subscribe({
      next: (res) => {
        this.awards = res;
      },
      error: (err) => alert('Error al buscar los premios' + err),
    });
  }

  redeemAward(id: string) {
    this.award.postPrize(id).subscribe(
      () => {
        Swal.fire({
          title: 'Felicidades',
          html: 'Revisa tu correo para confirmar <br> ¡Has ganado un premio!',
          icon: 'success',
          confirmButtonText: 'Recibido',
        }).then(() => window.location.reload());
      },
      (error) => {
        Swal.fire({
          title: 'Operacion invalida',
          text: 'No tienes los suficientes puntos, crea mas reciclaje para obtener mas puntos',
          icon: 'error',
          confirmButtonText: 'Ok',
        });
      }
    );
  }
}
