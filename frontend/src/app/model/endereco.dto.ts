import { CidadeDTO } from "./cidade.dto";

export class EnderecoDTO {
  id: number;
  logradouro: string;
  numero: string;
  complemento: string;
  bairro: string;
  cep: string;
  cidade: CidadeDTO | any;
}
