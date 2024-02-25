import { Component } from '@angular/core';
import { ApiService } from '../../services/api.services';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  constructor(private _apiService: ApiService){this.isLogged = !!localStorage.getItem('token');};
  isMenuOpen: boolean = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  clickRequest(): void {/* 
		this._apiService.getDitto().subscribe(); */
		this._apiService.getCharizard().subscribe();/* 
		this._apiService.getDitto().subscribe(); */
	}
  isLogged = false;

  
}
