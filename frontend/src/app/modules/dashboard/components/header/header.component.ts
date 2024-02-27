import { Component, EventEmitter, Output } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  @Output() emitShowSidebar = new  EventEmitter<boolean>();
  showSidebar(){
    this.emitShowSidebar.emit(true);
  }
}
