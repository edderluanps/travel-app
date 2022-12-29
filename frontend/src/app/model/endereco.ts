import { Cidade } from "./cidade";
import { Cliente } from "./cliente";

export class endereco {
  id: number;
  logradouro: string;
  numero: string;
  complemento: string;
  bairro: string;
  cep: string;
  cliente: Cliente[];
  cidade: Cidade[];
  ativo: boolean;
}
