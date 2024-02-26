import { Component } from '@angular/core';import { greenpoint } from '@models/greenpoint.model';
import { GreenpointService } from '@services/greenpoint.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss'],
})
export class PrincipalComponent {
  greenpoints: greenpoint[]=[];
  filter = "todos";
  constructor(
    private greenpoint:GreenpointService
  ){}

  ngOnInit() {
    this.getGreenpoints();
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
