import { Component, HostListener, OnInit } from '@angular/core';
import { tokenData } from '@models/token.model';
import { AuthService } from '@services/auth.service';
import { TokenService } from '@services/token.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  isShowSidebar = false;
  isShowModal = false;

  constructor(private readonly authService: AuthService) {}

  ngOnInit(): void {
    this.authService.getUserLogged().subscribe();
  }

  receiveSidebarStatus(status: boolean) {
    this.isShowSidebar = status;
  }
  receiveModalStatus(status: boolean) {
    this.isShowModal = status;
  }
  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    if (window.innerWidth > 768) {
      this.isShowSidebar = false;
    }
  }
}
