import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AwardComponent } from './award.component';

const routes: Routes = [
  {path:"", component:AwardComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AwardRoutingModule { }
