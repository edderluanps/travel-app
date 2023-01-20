import { ItemPedidoDTO } from "./item-pedido.dto";
import { PagamentoDTO } from "./pagamento.dto";
import { RefDTO } from "./ref.dto";

export class PedidoDTO{
  cliente: RefDTO;
  pagamento: PagamentoDTO;
  itens : ItemPedidoDTO[];
}
