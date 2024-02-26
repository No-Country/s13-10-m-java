import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './material/material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NotFoundComponent } from './modules/not-found/not-found.component';
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ApiInterceptor } from './interceptors/api.interceptor';
import { ReactiveFormsModule } from '@angular/forms';
import { ShowsidebarDirective } from './modules/dashboard/components/showsidebar.directive';

@NgModule({
  declarations: [AppComponent, NotFoundComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    BrowserAnimationsModule,
    NgxUiLoaderModule
  ],
	providers: [{ provide: HTTP_INTERCEPTORS, useClass: ApiInterceptor, multi: true }],
  bootstrap: [AppComponent],
})
export class AppModule {}
