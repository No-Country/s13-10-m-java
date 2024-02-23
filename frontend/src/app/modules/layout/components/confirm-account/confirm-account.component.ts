import { Component } from '@angular/core';
import JSConfetti from 'js-confetti';

@Component({
  selector: 'app-confirm-account',
  templateUrl: './confirm-account.component.html',
  styleUrls: ['./confirm-account.component.scss']
})
export class ConfirmAccountComponent {
   confetti !: JSConfetti;
   ngAfterViewInit(){
    this.confetti = new JSConfetti();
    this.confetti.addConfetti({
      emojis:["🍂","🍃","🎉","🎊","♻️"],
    })
   }
}
