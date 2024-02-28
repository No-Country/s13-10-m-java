import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GreenpointsRoutingModule } from './greenpoints-routing.module';
import { GreenpointsComponent } from './greenpoints.component';
import { SharedModule } from '@modules/shared/shared.module';

@NgModule({
  declarations: [GreenpointsComponent],
  imports: [CommonModule, GreenpointsRoutingModule, SharedModule],
})
export class GreenpointsModule {}
