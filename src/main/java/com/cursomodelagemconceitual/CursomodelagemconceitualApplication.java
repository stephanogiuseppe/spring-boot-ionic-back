package com.cursomodelagemconceitual;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomodelagemconceitual.domain.Categoria;
import com.cursomodelagemconceitual.domain.Cidade;
import com.cursomodelagemconceitual.domain.Cliente;
import com.cursomodelagemconceitual.domain.Endereco;
import com.cursomodelagemconceitual.domain.Estado;
import com.cursomodelagemconceitual.domain.Pagamento;
import com.cursomodelagemconceitual.domain.PagamentoBoleto;
import com.cursomodelagemconceitual.domain.PagamentoCartao;
import com.cursomodelagemconceitual.domain.Pedido;
import com.cursomodelagemconceitual.domain.Produto;
import com.cursomodelagemconceitual.domain.enums.EstadoPagamento;
import com.cursomodelagemconceitual.domain.enums.TipoCliente;
import com.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.cursomodelagemconceitual.repositories.CidadeRepository;
import com.cursomodelagemconceitual.repositories.ClienteRepository;
import com.cursomodelagemconceitual.repositories.EnderecoRepository;
import com.cursomodelagemconceitual.repositories.EstadoRepository;
import com.cursomodelagemconceitual.repositories.PagamentoRepository;
import com.cursomodelagemconceitual.repositories.PedidoRepository;
import com.cursomodelagemconceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomodelagemconceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomodelagemconceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente1 = new Cliente(null, "Maria", "maria@email.com", "112223334", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("33223322", "22332233"));
		
		Endereco endereco1 = new Endereco(null, "Rua A", "1", "Apto. 1", "Bairro Genério", "11011001", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Rua B", "200", "Sala 200", "Bairro Qualquer", "22022002", cliente1, cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cliente1, endereco2);

		Pagamento pagamento1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
	}	
}
