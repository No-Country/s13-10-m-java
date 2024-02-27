import { Component, EventEmitter, Output } from '@angular/core';
import { tokenData } from '@models/token.model';
import { TokenService } from '@services/token.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  @Output() emitShowSidebar = new  EventEmitter<boolean>();
  @Output() emitShowModal = new  EventEmitter<boolean>();
  tokenData !: tokenData | undefined;

  constructor(
    private tokenService:TokenService
  ){}

  ngOnInit(){
    this.tokenData = this.tokenService.getTokenDecoded()
  }
  showSidebar(){
    this.emitShowSidebar.emit(true);
  }

  showModal(){
    this.emitShowModal.emit(true);
  }
  showOptions(){
   var boton= document.getElementById("dropdownDivider");
   boton?.classList.toggle("hidden");
  }

}
