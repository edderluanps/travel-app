import { endereco } from "./endereco";

export interface Hospedagem {

  id: number;
  nome: string;
  registro: string;
  preco: string;
  tipo: string;
  endereco: endereco[];
  ativo: boolean;
}
