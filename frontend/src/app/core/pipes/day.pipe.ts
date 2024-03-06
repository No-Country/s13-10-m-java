import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'day',
  standalone:true
})
export class DayPipe implements PipeTransform {

  transform(word: string): string {
    if(word ==="miercoles") return "miércoles";
    if(word === "sabado") return "sábado"
    return word;
  }

}
