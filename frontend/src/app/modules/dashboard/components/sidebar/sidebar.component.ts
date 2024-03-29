import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent {
  @Output() emitHiddenSidebar = new EventEmitter<boolean>();

  hiddenSidebar(){
    this.emitHiddenSidebar.emit(false);
  }

}
