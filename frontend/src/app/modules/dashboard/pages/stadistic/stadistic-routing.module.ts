import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StadisticComponent } from './stadistic.component';

const routes: Routes = [
  {path:"", component:StadisticComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StadisticRoutingModule { }
