import { Component } from '@angular/core';
import { greenpoint } from 'src/app/core/models/greenpoint.model';
import { GreenpointService } from 'src/app/core/services/greenpoint.service';
import { ApiService } from 'src/app/services/api.services';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent {
  greenpoints: greenpoint[]=[];
  filter = "todos";
  constructor(
    private greenpoint:GreenpointService,private _apiService: ApiService
  ){}

  ngOnInit(){
    this.getGreenpoints();
  }
  getGreenpoints(){
    this.greenpoint.getAllGreenpoints().subscribe({
      next:(res)=>{
        console.log("get greenpoints", res)
        this.greenpoints = res;
      },
      error:(err)=>(console.error("ocurrio un error",{err}))
    })
  }
  
  clickRequest(): void {/* 
		this._apiService.getDitto().subscribe(); */
		this._apiService.getCharizard().subscribe();/* 
		this._apiService.getDitto().subscribe(); */
	}
}
