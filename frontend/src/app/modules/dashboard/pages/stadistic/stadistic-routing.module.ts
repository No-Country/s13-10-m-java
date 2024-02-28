import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StadisticComponent } from './stadistic.component';
import { CustomChartComponent } from './components/custom-chart/custom-chart.component';
import { ScoreComponent } from './components/score/score.component';

const routes: Routes = [
  {path:"", component:ScoreComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StadisticRoutingModule { }
