package com.hortin.HORTIN.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hortin.HORTIN.entity.CompraProduto;
import com.hortin.HORTIN.entity.Produto;
import com.hortin.HORTIN.entity.Usuario;
import com.hortin.HORTIN.entity.Vendedor;
import com.hortin.HORTIN.repository.produtoRepository;
import com.hortin.HORTIN.repository.usuarioRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private produtoRepository repo;
	@Autowired
	private usuarioRepository usuarioRepo;
	
	
	@GetMapping()
	public ResponseEntity<List<Produto>> pegaTodosProdutos(
			  @RequestParam(defaultValue = "0") int pageNum,
		      @RequestParam(defaultValue = "999") int size,
		      @RequestParam(defaultValue = "id") String sort){
		Pageable page = PageRequest.of(pageNum, size, Sort.by(sort));
		Page<Produto> listaProdutos = repo.findAll(page);
		
		for (Produto produto : listaProdutos) {
			System.out.println(produto.getVendedor().getNome());
		}
		return ResponseEntity.ok(listaProdutos.getContent());
	}
	
	@PostMapping("/vendedor/{usuario_id}")
	public String insereProduto(@RequestBody Produto produto,@PathVariable long usuario_id, UriComponentsBuilder uriBuilder){
		System.out.println(produto);
		Optional<Usuario> usuario = usuarioRepo.findById(usuario_id);
		if(usuario.isEmpty()) {
			return "{\"status\": \"error\"}";
		}
		produto.setVendedor(usuario.get());
		repo.save(produto);
		
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId_produto()).toUri();
		return "{\"status\": \"success\"}";
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Optional<Produto>> deletaProduto(@PathVariable Long id){
		Optional<Produto> prodAchado = repo.findById(id);
		if(prodAchado.isEmpty()) {
			ResponseEntity.notFound().build();
		}
		repo.deleteById(id);
		
		return ResponseEntity.ok(prodAchado);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Produto> atualizaProduto(@PathVariable Long id, @RequestBody Produto produto){
		Optional<Produto> produtoAchado = repo.findById(id);
		
		if(produtoAchado.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		produtoAchado.get().setDescricaoProduto(produto.getDescricaoProduto());
		produtoAchado.get().setNomeProduto(produto.getNomeProduto());
		produtoAchado.get().setValorProduto(produto.getValorProduto());
                produtoAchado.get().setQuantidadeProduto(produto.getQuantidadeProduto());
		
		return ResponseEntity.ok(produtoAchado.get());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> pegaProdutoPorId(@PathVariable Long id){
		System.out.println("pegaProdutoPorIDVendedor");
		Optional<Produto> produtoAchado = repo.findById(id);
		
		if(produtoAchado.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(produtoAchado.get());
	}
	
	@GetMapping("/vendedor/{id}")
	public ResponseEntity<List<Produto>> pegaProdutosPorVendedor(@PathVariable Long id){
		List<Produto> listaProduto = repo.findByVendedorId(id);
		
		if(listaProduto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(listaProduto);
		
	}
	
	@PutMapping("/Compra")
	@Transactional
	public ResponseEntity<List<CompraProduto>> compraProdutos(@RequestBody List<CompraProduto> listaCompra){
		System.out.println("compra");
		for(CompraProduto compra : listaCompra) {
			Optional<Produto> produtoAchado = repo.findById(compra.getId());
			if(produtoAchado.isEmpty()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return ResponseEntity.notFound().build();
			}
			if(produtoAchado.get().getQuantidadeProduto() - compra.getQuantidade() >= 0) {
				produtoAchado.get().setQuantidadeProduto(produtoAchado.get().getQuantidadeProduto() - compra.getQuantidade());
			}
			else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return ResponseEntity.badRequest().body(null);
			}
		}
		return ResponseEntity.ok(listaCompra);
	}
}
