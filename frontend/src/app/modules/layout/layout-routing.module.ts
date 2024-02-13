import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
  {path:"", component:LayoutComponent, children:[
    {path:"" , redirectTo:"/home", pathMatch:"full"},
    {path:"login", loadChildren:()=>import("../auth/pages/login/login.module").then(m=>m.LoginModule)},
    {path:"register", loadChildren:()=>import("../auth/pages/register/register.module").then(m=>m.RegisterModule)},
    {path:"home", loadChildren:()=>import("../home/home.module").then(m=>m.HomeModule)}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
