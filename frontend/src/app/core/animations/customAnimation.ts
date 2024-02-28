import {
  animate,
  style,
  transition,
  trigger,
} from '@angular/animations';

export const fadeAnimation = trigger('fadeEffect', [
  transition(':enter', [
    style({ opacity: 0, transform: 'scale(0.7)' }),
    animate(300, style({ opacity: 1, transform: 'scale(1)' })),
  ]),
  transition(':leave', [animate(300, style({ opacity: 0 }))]),
]);
