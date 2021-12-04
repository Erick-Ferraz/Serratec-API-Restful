package br.org.serratec.backend.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.dto.ProdutoInserirDTO;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutoRepository pr;
	
	private ProdutoDTO adicionarUriFoto(Produto prod) {

		ProdutoDTO prodDto = new ProdutoDTO();
		prodDto.setId(prod.getId());
		prodDto.setNome(prod.getNome());
		prodDto.setDescricao(prod.getDescricao());
		prodDto.setCategoria(prod.getCategoria().getNome());
		prodDto.setQtdEstoque(prod.getQtdEstoque());
		prodDto.setVlUnitario(prod.getVlUnitario());
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/projetofinal/produtos/{id}/foto")
				.buildAndExpand(prod.getId()).toUri();
		prodDto.setUri(uri.toString());

		return prodDto;
	}

	public List<ProdutoDTO> listar() { 

		List<ProdutoDTO> produtosDto = new ArrayList<ProdutoDTO>();
		List<Produto> produtos = pr.findAll();

		for (Produto produto : produtos) {
			ProdutoDTO produtoDto = new ProdutoDTO(produto);
			produtosDto.add(produtoDto);
		}
		return produtosDto;
	}
	
	public Optional<ProdutoDTO> obterPorId(Integer id) {

		Optional<Produto> produto = pr.findById(id);
		Optional<ProdutoDTO> produtoDto = Optional.ofNullable(new ProdutoDTO(produto.get()));

		if (produto.isPresent()) {
			return produtoDto;
		}
		return null;
	}


	public ProdutoDTO inserir(ProdutoInserirDTO produto) {

		Produto prod = new Produto();
		prod.setNome(produto.getNome());
		prod.setDescricao(produto.getDescricao());
		prod.setCategoria(produto.getCategoria());
		prod.setQtdEstoque(produto.getQtdEstoque());
		prod.setVlUnitario(produto.getVlUnitario());
		prod.setDtCadastro(LocalDate.now());
		
		adicionarUriFoto(prod);

		return new ProdutoDTO(pr.save(prod));
	}

	public ProdutoDTO atualizarPorId(Integer id, ProdutoInserirDTO produto) {
		if (pr.existsById(id)) {
			Produto prod = new Produto();
			prod.setId(id);
			prod.setNome(produto.getNome());
			prod.setDescricao(produto.getDescricao());
			prod.setCategoria(produto.getCategoria());
			prod.setQtdEstoque(produto.getQtdEstoque());
			prod.setVlUnitario(produto.getVlUnitario());
			prod.setDtCadastro(LocalDate.now());
			adicionarUriFoto(prod);
			pr.save(prod);

			return new ProdutoDTO(prod);
		}
		return null;
	}

	public void deletarPorId(Integer id) {
		if (pr.existsById(id)) {
			pr.deleteById(id);
		}
	}

}
