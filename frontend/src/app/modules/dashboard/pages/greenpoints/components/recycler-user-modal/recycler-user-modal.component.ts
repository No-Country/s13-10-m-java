import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-recycler-user-modal',
  templateUrl: './recycler-user-modal.component.html',
  styleUrls: ['./recycler-user-modal.component.scss'],
})
export class RecyclerUserModalComponent {
  @ViewChild('modal') modal!: ElementRef<HTMLDialogElement>;
  @Input() idPuntoVerde: string | undefined = '';
  @Output() prueba = new EventEmitter<boolean>();

  open() {
    this.modal.nativeElement.showModal();
  }

  close() {
    this.modal.nativeElement.close();
  }
  sendPrueba(){
    this.prueba.emit(true);
  }
  receivePrueba(valor:boolean){
    this.sendPrueba()
  }
}
