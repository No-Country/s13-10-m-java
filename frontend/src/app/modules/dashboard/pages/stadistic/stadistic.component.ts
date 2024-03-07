import { Component } from '@angular/core';
import { stadisticResponse } from '@models/stadistic.model';
import { StadisticService } from '@services/stadistic.service';

@Component({
  selector: 'app-stadistic',
  templateUrl: './stadistic.component.html',
  styleUrls: ['./stadistic.component.scss']
})
export class StadisticComponent {
  data!:stadisticResponse

  constructor(
    private readonly stadisticService : StadisticService
  ){}

  ngOnInit(){
    this.stadisticService.getStadistics().subscribe({
      next:(res)=>this.data = res
    })
  }
}
