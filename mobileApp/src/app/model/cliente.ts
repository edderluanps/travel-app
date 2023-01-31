import { endereco } from "./endereco";
import { Post } from "./post";

export interface Cliente {

  id: number;
  nome: string;
  cpfOuCnpj: string;
  email: string;
  senha: string;
  dataNascimento: string;
  dataCadastro: string;
  tipoCliente: number;
  telefone: string;
  enderecos: endereco[];
  perfis: string;
  pedidos: string;
  posts: Post[];
  ativo: boolean;

}
