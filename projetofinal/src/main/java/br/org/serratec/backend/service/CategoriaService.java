package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.dto.CategoriaInserirDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.exception.RecursoNotFoundException;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository cr;

	public List<CategoriaDTO> listar() {
		List<CategoriaDTO> categoriasDto = new ArrayList<CategoriaDTO>();
		List<Categoria> categorias = cr.findAll();

		for (Categoria categoria : categorias) {
			CategoriaDTO categoriaDto = new CategoriaDTO(categoria);
			categoriasDto.add(categoriaDto);
		}
		return categoriasDto;
	}

	public Optional<CategoriaDTO> obterPorId(Integer id) {

		Optional<Categoria> categoria = cr.findById(id);
		Optional<CategoriaDTO> categoriaDto = Optional.ofNullable(new CategoriaDTO(categoria.get()));
		
		if (categoria.isPresent()) {
			return categoriaDto;
		}
		throw new RecursoBadRequestException("A categoria inserida é inválida!");
	}

	public CategoriaDTO inserir(CategoriaInserirDTO categoria) {
		Categoria cat = new Categoria(categoria);
		cat.setNome(categoria.getNome());
		cat.setDescricao(categoria.getDescricao());
		cr.save(cat);

		return new CategoriaDTO(cat);
	}

	public CategoriaDTO atualizarPorId(Integer id, CategoriaInserirDTO categoria) {
		
		if (cr.existsById(id)) {
			Categoria cat = new Categoria(categoria);
			cat.setId(id);
			cat.setNome(categoria.getNome());
			cat.setDescricao(categoria.getDescricao());
			cr.save(cat);

			return new CategoriaDTO(cat);
		}
		throw new RecursoNotFoundException("Categoria não encontrada!");
	}

	public CategoriaDTO atualizarPorNome(String nome, CategoriaInserirDTO categoria) {
		if (cr.existsByNome(nome) != null) {
			Categoria cat = new Categoria(categoria);
			cat.setId(cr.getByNome(nome).getId());
			cat.setNome(categoria.getNome());
			cat.setDescricao(categoria.getDescricao());
			cr.save(cat);

			return new CategoriaDTO(cat);
		}
		throw new RecursoNotFoundException("Categoria não encontrada!");
	}

	public void deletarPorId(Integer id) {
		if (cr.existsById(id)) {
			cr.deleteById(id);
		}
	}
}