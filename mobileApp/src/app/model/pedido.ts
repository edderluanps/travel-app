import { ItemPedidoDTO } from "./item-pedido.dto";

export interface Pedido{

  id : number;
  dataPedido : string;
  itens: ItemPedidoDTO[];
}
