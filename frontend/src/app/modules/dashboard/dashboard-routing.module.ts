import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

const routes: Routes = [
  { path:"", component:DashboardComponent, children:[
    {
      path:"", redirectTo:"principal", pathMatch:"full"
    },
    {
      path:"principal",
      loadChildren: ()=>import("./pages/home/home-routing.module").then(m=>m.HomeRoutingModule)
    },
    {
      path:"profile",
      loadChildren: ()=>import("./pages/profile/profile-routing.module").then(m=>m.ProfileRoutingModule)
    },
    {
      path:"recycled",
      loadChildren: ()=>import("./pages/recycled/recycled-routing.module").then(m=>m.RecycledRoutingModule)
    },
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
