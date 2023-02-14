package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.ItemPedido;
import com.eluanps.travelapp.entity.Pedido;
import com.eluanps.travelapp.entity.PgBoleto;
import com.eluanps.travelapp.entity.enums.PagamentoStatus;
import com.eluanps.travelapp.repository.ItemPedidoRepository;
import com.eluanps.travelapp.repository.PagamentoRepository;
import com.eluanps.travelapp.repository.PedidoRepository;
import com.eluanps.travelapp.security.UserSS;
import com.eluanps.travelapp.service.exceptions.AuthorizationException;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PacoteService pacoteService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setDataPedido(new Date());
        pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
        pedido.getPagamento().setStatus(PagamentoStatus.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PgBoleto) {
            PgBoleto pgBoleto = (PgBoleto) pedido.getPagamento();
            boletoService.preencherPGBoleto(pgBoleto, pedido.getDataPedido());
        }
        pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ipedido : pedido.getItens()) {
            ipedido.setDesconto(0);
            ipedido.setPacote(pacoteService.findById(ipedido.getPacote().getId()));
            ipedido.setPreco(ipedido.getPacote().getPreco());
            ipedido.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());
        //emailService.sendOrderConfirmationEmail(pedido);
        return pedido;
    }

    public void atualizar(Long id, Pedido pedido) {
        pedidoRepository.findById(id).map(obj -> {
            pedido.setId(obj.getId());
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public void delete(Long id) {
        findById(id);
        try {
            pedidoRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não foi possivel deletar o Pedido: Item Ativo.");
        }
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.findById(user.getId());
        return pedidoRepository.findAll(pageRequest);
    }
    
    public List<Pedido> findByClienteId(Long id) {
        return pedidoRepository.findByClienteId(id);
    }
    
    public void generatePdf(HttpServletResponse response, Long id) throws IOException {
        
        Pedido pedido = findById(id);
        
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        document.add(new Paragraph("Travel.io", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Color.BLACK)));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        
        Paragraph paragraph = new Paragraph("Resumo", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(14);

        Paragraph paragraph2 = new Paragraph("Pedido cód: " + pedido.getId(), fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        
        Paragraph paragraph3 = new Paragraph("Cliente: " + pedido.getCliente().getNome(), fontParagraph);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        
        Paragraph paragraph4 = new Paragraph("Data: " + pedido.getDataPedido(), fontParagraph);
        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph5 = new Paragraph("Pagamento: " + pedido.getPagamento().getStatus(), fontParagraph);
        paragraph5.setAlignment(Paragraph.ALIGN_LEFT);        

        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);

        
        float columnWidth[] = {50f, 10f, 20f, 20f};
        
        PdfPTable table = new PdfPTable(columnWidth);
        table.setHorizontalAlignment(0);
        table.setWidthPercentage(100);
        
        PdfPCell cell0 = new PdfPCell(new Paragraph("Descrição", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.WHITE)));
        cell0.setBackgroundColor(Color.BLACK);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Qtd.", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.WHITE)));
        cell1.setBackgroundColor(Color.BLACK);
        PdfPCell cell2 = new PdfPCell(new Paragraph("Preço", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.WHITE)));
        cell2.setBackgroundColor(Color.BLACK);
        PdfPCell cell3 = new PdfPCell(new Paragraph("Subtotal", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.WHITE)));
        cell3.setBackgroundColor(Color.BLACK);

        table.addCell(cell0);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        
        Set<ItemPedido> lista = pedido.getItens();
            
        for (ItemPedido i : lista) {

            cell0 = new PdfPCell(new Paragraph(i.getPacote().getNome() + "", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLACK)));
            cell1 = new PdfPCell(new Paragraph(i.getQuantidade() + "", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLACK)));
            cell2 = new PdfPCell(new Paragraph("R$ " + i.getPreco() + "", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLACK)));
            cell3 = new PdfPCell(new Paragraph("R$ " + i.getSubtotal() + "", FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLACK)));

            table.addCell(cell0);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
        } 
        
        Paragraph paragraph6 = new Paragraph("Total: R$ " + pedido.getValorTotal(), FontFactory.getFont(FontFactory.HELVETICA, 18, Color.BLACK));
        paragraph6.setAlignment(Paragraph.ALIGN_LEFT);  
        
        document.add(new Paragraph(" "));
        document.add(table);
        document.add(new Paragraph(" "));
        document.add(paragraph6);
        document.close();
    }

}
