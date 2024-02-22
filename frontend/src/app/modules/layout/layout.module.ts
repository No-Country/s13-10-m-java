import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LayoutRoutingModule } from './layout-routing.module';
import { LayoutComponent } from './layout.component';
import { ConfirmAccountComponent } from './components/confirm-account/confirm-account.component';


@NgModule({
  declarations: [
    LayoutComponent,
    ConfirmAccountComponent
  ],
  imports: [
    CommonModule,
    LayoutRoutingModule
  ]
})
export class LayoutModule { }
