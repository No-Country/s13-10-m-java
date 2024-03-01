import { Component, OnInit } from '@angular/core';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  currentYear: number = new Date().getFullYear();

  constructor(private readonly authService: AuthService) {}

  ngOnInit(): void {
    this.authService.getUserLogged().subscribe();
  }
}
