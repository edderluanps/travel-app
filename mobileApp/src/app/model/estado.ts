import { Cidade } from "./cidade";

export interface Estado{
  id: number;
  nome: string;
  cidades: Cidade[];
}
