import { Cidade } from "./cidade";
import { Cliente } from "./cliente";

export interface endereco {
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
