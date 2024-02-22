import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GreenpointsRoutingModule } from './greenpoints-routing.module';
import { GreenpointsComponent } from './greenpoints.component';


@NgModule({
  declarations: [
    GreenpointsComponent
  ],
  imports: [
    CommonModule,
    GreenpointsRoutingModule
  ]
})
export class GreenpointsModule { }
