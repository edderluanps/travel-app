import { Cidade } from "./cidade";
import { Empresa } from "./empresa";

export interface Voos {

  id: number;
  preco: string;
  dataEmbarque: string;
  dataDesembarque: string;
  empresa: Empresa[];
  cidade: Cidade[];
  ativo: boolean;
}
