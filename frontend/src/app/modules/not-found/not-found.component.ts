import { Component, NgModule } from '@angular/core';

import { Router } from '@angular/router';

import { SharedModule } from '../shared/shared.module';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.scss']
})
export class NotFoundComponent {
  
  constructor(private readonly router: Router){}
  goToHome() {
    this.router.navigateByUrl('/');
  }

}
