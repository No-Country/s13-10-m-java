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
          import('./pages/profile/profile.module').then(
            (m) => m.ProfileModule
          ),
      },
      {
        path: 'recycled',
        loadChildren: () =>
          import('./pages/recycled/recycled.module').then(
            (m) => m.RecycledModule
          ),
      },
      {
        path: 'award',
        loadChildren: () =>
          import('./pages/award/award.module').then(
            (m) => m.AwardModule
          ),
      },
      {
        path: 'stadistic',
        loadChildren: () =>
          import('./pages/stadistic/stadistic.module').then(
            (m) => m.StadisticModule
          ),
      },
      {
        path: 'greenpoints',
        loadChildren: () =>
          import('./pages/greenpoints/greenpoints.module').then(
            (m) => m.GreenpointsModule
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
