import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecycledComponent } from './recycled.component';

const routes: Routes = [
  { path:"", component:RecycledComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RecycledRoutingModule { }
