import { Component, HostListener } from '@angular/core';
import { tokenData } from '@models/token.model';
import { TokenService } from '@services/token.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  isShowSidebar = false;
  isShowModal = false;
  receiveSidebarStatus(status:boolean){
    this.isShowSidebar = status;
  }
  receiveModalStatus(status:boolean){
    this.isShowModal = status;
  }
  @HostListener("window:resize", ["$event"])
  onResize(event : Event){
    if(window.innerWidth>768){
      this.isShowSidebar = false;
    }
  }
}
