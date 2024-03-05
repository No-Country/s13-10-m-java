import { Component, ElementRef, Input, ViewChild } from '@angular/core';

@Component({
  selector: 'app-recycler-user-modal',
  templateUrl: './recycler-user-modal.component.html',
  styleUrls: ['./recycler-user-modal.component.scss'],
})
export class RecyclerUserModalComponent {
  @ViewChild('modal') modal!: ElementRef<HTMLDialogElement>;
  @Input() idPuntoVerde: string | undefined = '';

  open() {
    this.modal.nativeElement.showModal();
  }

  close() {
    this.modal.nativeElement.close();
  }
}
