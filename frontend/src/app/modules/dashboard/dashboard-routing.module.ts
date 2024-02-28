import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    children: [
      {
        path: '',
        redirectTo: 'principal',
        pathMatch: 'full',
      },
      {
        path: 'principal',
        loadChildren: () =>
          import('./pages/principal/principal.module').then(
            (m) => m.PrincipalModule
          ),
      },
      {
        path: 'profile',
        loadChildren: () =>
          import('./pages/profile/profile-routing.module').then(
            (m) => m.ProfileRoutingModule
          ),
      },
      {
        path: 'recycled',
        loadChildren: () =>
          import('./pages/recycled/recycled-routing.module').then(
            (m) => m.RecycledRoutingModule
          ),
      },
      {
        path: 'award',
        loadChildren: () =>
          import('./pages/award/award-routing.module').then(
            (m) => m.AwardRoutingModule
          ),
      },
      {
        path: 'stadistic',
        loadChildren: () =>
          import('./pages/stadistic/stadistic-routing.module').then(
            (m) => m.StadisticRoutingModule
          ),
      },
      {
        path: 'greenpoints',
        loadChildren: () =>
          import('./pages/greenpoints/greenpoints-routing.module').then(
            (m) => m.GreenpointsRoutingModule
          ),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
