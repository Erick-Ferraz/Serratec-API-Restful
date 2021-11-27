package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.dto.ProdutoInserirDTO;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository pr;

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
