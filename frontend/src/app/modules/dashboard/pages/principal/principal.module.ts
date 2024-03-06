import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrincipalRoutingModule } from './principal-routing.module';
import { PrincipalComponent } from './principal.component';
import { CustomMapComponent } from './components/map/map.component';
import { FormsModule } from '@angular/forms';
import { TypeFormatPipe } from 'app/core/pipes/type-format.pipe';


@NgModule({
  declarations: [
    PrincipalComponent,
    CustomMapComponent,
  ],
  imports: [
    CommonModule,
    PrincipalRoutingModule,
    FormsModule,
    TypeFormatPipe
  ]
})
export class PrincipalModule { }
