package br.org.serratec.backend.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.dto.ClienteFotoDTO;
import br.org.serratec.backend.dto.ProdutoFotoDTO;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Foto;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ClienteRepository;
import br.org.serratec.backend.repository.FotoRepository;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fr;
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private ProdutoRepository pr;

	public Foto inserir(ClienteFotoDTO cliente, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		Cliente client = cr.getById(cliente.getId());
		foto.setCliente(client);

		return fr.save(foto);
	}
	
	public Foto inserirProduto(ProdutoFotoDTO produto, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		Produto prod = pr.getById(produto.getId());
		foto.setProduto(prod);

		return fr.save(foto);
	}

	public Foto obterPorId(Integer id) {
		Optional<Foto> foto = fr.findById(id);

		if (foto.isPresent()) {
			return foto.get();
		}
		return null;
	}
	
//	public Foto obterPorIdCliente(Integer id) {
//		Optional<List<Foto>> foto = fr.findByClientId(id);
//
//		if (foto.isPresent()) {
//			var x = foto.get();
//			if (x.size() > 0)
//			return x.get(0);
//			else return null;
//		}
//		return null;
//	}

	public void deletarPorId(Integer id) {
		if (fr.existsById(id)) {
			fr.deleteById(id);
		}
	}
}
