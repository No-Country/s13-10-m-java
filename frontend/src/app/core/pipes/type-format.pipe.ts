import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'typeFormat',
  standalone:true
})
export class TypeFormatPipe implements PipeTransform {

  transform(value: string): string {
    if(value === "PLASTICO") return "Plástico";
    if(value === "PAPELCARTON") return "Papel y carton";
    if(value === "VIDRIO") return "Vidrio"
    if(value === "METAL") return "Metal"
    return value;
 }

}
