import { Component, OnInit } from '@angular/core';
import { UserResponse } from '@models/user.model';
import { AwardService } from '@services/award.service';
import { award as ModalAward } from '@models/award.model';
import Swal from 'sweetalert2';
import { UserService } from '@services/user.service';
import { TokenService } from '@services/token.service';

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
    private userService:UserService,
    private tokenService:TokenService
  ) {}

  ngOnInit(): void {
    this.getAwards();
    this.getUserData()
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
        // console.log("canjeo exitos", response)
        Swal.fire({
          title: 'Felicidades',
          html: 'Revisa tu correo para confirmar <br> ¡Has ganado un premio!',
          icon: 'success',
          confirmButtonText: 'Recibido'
        })
        .then(()=>{
          this.getUserData()
        })
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
  getUserData(){
    const id = this.tokenService.getDecodedToken()!.USER_ID;
    this.userService.getUser(id).subscribe({
      next:(res)=>this.user = res,
      error:(err)=>console.log("error al obtener el usuario", err)
    })
  }
}
