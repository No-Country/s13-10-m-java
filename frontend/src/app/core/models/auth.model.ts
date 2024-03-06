export interface IAuthRegisterReq {
  nombre: string;
  apellido: string;
  email: string;
  password: string;
}

export interface IAuthRegisterRes {
  userId: string;
  nombre: string;
  apellido: string;
  email: string;
  imgUrl: string;
  jwtToken: string;
}

export interface IAuthLoginReq {
  email: string;
  password: string;
}

export interface IAuthLoginRes {
  token: string;
}
