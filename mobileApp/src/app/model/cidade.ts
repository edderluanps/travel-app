import { Estado } from "./estado";

export interface Cidade {

  id: number;
  nome: string;
  estado: Estado[];
  img: string;
}
