import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GreenpointsRoutingModule } from './greenpoints-routing.module';
import { GreenpointsComponent } from './greenpoints.component';
import { SharedModule } from '@modules/shared/shared.module';
import { RecyclerUserModalComponent } from './components/recycler-user-modal/recycler-user-modal.component';
import { RecyclerUserFormComponent } from './components/recycler-user-form/recycler-user-form.component';

@NgModule({
  declarations: [
    GreenpointsComponent,
    RecyclerUserModalComponent,
    RecyclerUserFormComponent,
  ],
  imports: [CommonModule, GreenpointsRoutingModule, SharedModule],
})
export class GreenpointsModule {}
