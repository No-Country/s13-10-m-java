import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecycledRoutingModule } from './recycled-routing.module';
import { RecycledComponent } from './recycled.component';
import { TypeFormatArrayPipe } from 'app/core/pipes/type-format-array.pipe';


@NgModule({
  declarations: [
    RecycledComponent
  ],
  imports: [
    CommonModule,
    RecycledRoutingModule,
    TypeFormatArrayPipe
  ]
})
export class RecycledModule { }
