import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StadisticRoutingModule } from './stadistic-routing.module';
import { StadisticComponent } from './stadistic.component';
import { ScoreComponent } from './components/score/score.component';
import { CustomChartComponent } from './components/custom-chart/custom-chart.component';


@NgModule({
  declarations: [
    StadisticComponent,
    ScoreComponent,
    CustomChartComponent
  ],
  imports: [
    CommonModule,
    StadisticRoutingModule,
  ]
})
export class StadisticModule { }
