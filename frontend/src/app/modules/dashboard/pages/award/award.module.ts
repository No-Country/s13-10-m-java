import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AwardRoutingModule } from './award-routing.module';
import { AwardComponent } from './award.component';


@NgModule({
  declarations: [
    AwardComponent
  ],
  imports: [
    CommonModule,
    AwardRoutingModule
  ]
})
export class AwardModule { }
