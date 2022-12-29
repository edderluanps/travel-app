import { endereco } from "./endereco";

export class Hospedagem {
  id: number;
  nome: string;
  registro: string;
  preco: string;
  tipo: string;
  endereco: endereco[];
  ativo: boolean;
}
