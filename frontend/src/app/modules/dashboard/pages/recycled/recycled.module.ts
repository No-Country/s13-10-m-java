import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecycledRoutingModule } from './recycled-routing.module';
import { RecycledComponent } from './recycled.component';


@NgModule({
  declarations: [
    RecycledComponent
  ],
  imports: [
    CommonModule,
    RecycledRoutingModule
  ]
})
export class RecycledModule { }
