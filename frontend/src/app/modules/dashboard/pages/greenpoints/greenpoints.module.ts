import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GreenpointsRoutingModule } from './greenpoints-routing.module';
import { GreenpointsComponent } from './greenpoints.component';
import { SharedModule } from '@modules/shared/shared.module';
import { RecyclerUserModalComponent } from './components/recycler-user-modal/recycler-user-modal.component';
import { RecyclerUserFormComponent } from './components/recycler-user-form/recycler-user-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DashboardModule } from '@modules/dashboard/dashboard.module';
import { TypeFormatPipe } from 'app/core/pipes/type-format.pipe';
import { TypeFormatArrayPipe } from 'app/core/pipes/type-format-array.pipe';

@NgModule({
  declarations: [
    GreenpointsComponent,
    RecyclerUserModalComponent,
    RecyclerUserFormComponent,
  ],
  imports: [
    CommonModule,
    GreenpointsRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    DashboardModule,
    TypeFormatArrayPipe,
    TypeFormatPipe
  ],
})
export class GreenpointsModule {}
