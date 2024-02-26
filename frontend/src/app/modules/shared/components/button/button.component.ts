import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss'],
})
export class ButtonComponent {
  @Input() type: 'submit' | 'button' = 'button';
  @Input() color: 'success' = 'success';

  private mapColors = {
    success: {
      'bg-green-600': true,
      'hover:bg-green-700': true,
      'active:bg-green-800': true,
    },
  };

  get colors() {
    const color = this.mapColors[this.color];

    return color ?? {};
  }
}
