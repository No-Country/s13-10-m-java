import { Component, EventEmitter, Output } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  
  constructor(private readonly router: Router){}
  @Output() emitShowSidebar = new  EventEmitter<boolean>();
  showSidebar(){
    this.emitShowSidebar.emit(true);
  }
  goToProfile() {
    this.router.navigateByUrl('/dashboard/profile');
  }
  showOptions(){
   var boton= document.getElementById("dropdownDivider");
   boton?.classList.toggle("hidden");
  }
  
}
