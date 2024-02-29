import { Component } from '@angular/core';import { greenPointResponse, greenpoint } from '@models/greenpoint.model';
import { UserResponse } from '@models/user.model';
import { GreenpointService } from '@services/greenpoint.service';
import { TokenService } from '@services/token.service';
import { UserService } from '@services/user.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss'],
})
export class PrincipalComponent {
  greenpoints: greenPointResponse[]=[];
  filter = "todos";
  userData!:UserResponse
  constructor(
    private greenpoint:GreenpointService,
    private userService : UserService,
    private tokenService:TokenService
  ){}

  ngOnInit() {
    this.getGreenpoints();
    this.getUserData()
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
  getUserData(){
    const userId = this.tokenService.getTokenDecoded()!;
    this.userService.getUser(userId.USER_ID).subscribe({
      next:(res)=>{
        this.userData = res
      },
      error:(err)=>console.log("error al obtener user", err)
    })
  }

}
