export interface greenpoint {
  nombre: string;
  direccion: string;
  lat: number;
  lng: number;
  type: string;
}
export interface greenpointForm {
  address: string;
  closeTime: string;
  dni: string;
  lat: string;
  lng: string;
  name: string;
  openTime: string;
  phone: string;
  papelcarton: boolean;
  plastico: boolean;
  vidrio: boolean;
  metal: boolean;
  lunes:boolean;
  martes:boolean;
  miercoles:boolean;
  jueves:boolean;
  viernes:boolean;
  sabado:boolean;
  domingo:boolean;
}
export interface greenpointDTO {
  userId: string;
  nombre: string;
  dni: string;
  telefono: string;
  horarioAtencion: string;
  diasAtencion: string;
  materialesAceptados: string[];
  direccion: string;
  latitud: string;
  longitud: string;
}
export interface greenPointResponse extends
  Omit<greenpointDTO, 'userId' | 'dni'>{
  puntoVerdeId:string
}
