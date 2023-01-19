import { EstadoDTO } from "./estado.dto";

export class CidadeDTO{
  id: number;
  nome: string;
  estado? : EstadoDTO;
}
