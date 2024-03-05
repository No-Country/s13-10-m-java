import { Component, OnInit } from '@angular/core';
import { UserResponse } from '@models/user.model';
import { AuthService } from '@services/auth.service';
import { AwardService } from '@services/award.service';
import { award as ModalAward } from '@models/award.model'

@Component({
  selector: 'app-award',
  templateUrl: './award.component.html',
  styleUrls: ['./award.component.scss'],
})
export class AwardComponent implements OnInit {
  user: UserResponse | null = null;
  awards: ModalAward[] = [];

  constructor(
    private award: AwardService,
    private readonly authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getAwards()
    this.authService.userLogged$.subscribe((res) => (this.user = res));
  }

  getAwards() {
    this.award.getAllPrizes().subscribe({
      next: (res) => {
        console.log(res);
        this.awards = res;
      },
      error: (err) => alert('Error al buscar los premios' + err),
    });
  }
}
