export interface IRecyclingReq {
  emailUsuario: string;
  materialesRecibidos: string[];
  descripcion: string;
  idPuntoVerde: string | undefined;
}

export interface IRecyclingRes {
  reciclajeId: string;
  materialesRecibidos: string[];
  descripcion: string;
  email: string;
  dia: string;
  userId: string;
  puntoVerdeNombre: string;
}
