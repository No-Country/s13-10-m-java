import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'typeFormatArray',
  standalone:true,
})
export class TypeFormatArrayPipe implements PipeTransform {

  transform(value: string[]): string[] {

    return value.map((type)=>{
      if(type === "PLASTICO") return "Plástico";
      if(type === "PAPELCARTON") return "Papel y cartón";
      if(type === "VIDRIO") return "Vidrio"
      if(type === "METAL") return "Metal"
      return type;
    })
 }

}
