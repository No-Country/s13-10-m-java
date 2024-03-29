import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { ShowsidebarDirective } from './components/showsidebar.directive';
import { ModalRegisterGreenpointComponent } from './components/modal-register-greenpoint/modal-register-greenpoint.component';
import { SharedModule } from '@modules/shared/shared.module';
import { RegisterMapComponent } from './components/register-map/register-map.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormGreenpointComponent } from './components/form-greenpoint/form-greenpoint.component';
import { DayPipe } from 'app/core/pipes/day.pipe';
import { TypeFormatPipe } from 'app/core/pipes/type-format.pipe';

@NgModule({
  declarations: [
    DashboardComponent,
    SidebarComponent,
    HeaderComponent,
    ShowsidebarDirective,
    ModalRegisterGreenpointComponent,
    RegisterMapComponent,
    FormGreenpointComponent,
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    DayPipe,
    TypeFormatPipe
  ],
  exports:[
    ModalRegisterGreenpointComponent,
    RegisterMapComponent,
  ]
})
export class DashboardModule {}
