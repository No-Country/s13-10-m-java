import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  isShowSidebar = false;

  receiveSidebarStatus(status:boolean){
    this.isShowSidebar = status;
  }
  @HostListener("window:resize", ["$event"])
  onResize(event : Event){
    if(window.innerWidth>768){
      this.isShowSidebar = false;
    }
  }
}
