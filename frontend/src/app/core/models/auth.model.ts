export interface IAuthRegister {
  nombre: string;
  apellido: string;
  email: string;
  password: string;
}

export interface IAuthLoginReq {
  email: string;
  password: string;
}

export interface IAuthLoginRes {
  token: string;
}
