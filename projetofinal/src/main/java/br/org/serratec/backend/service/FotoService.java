package br.org.serratec.backend.service;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Foto;
import br.org.serratec.backend.repository.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fr;

	public Foto inserir(Cliente cliente, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		foto.setCliente(cliente);

		return fr.save(foto);
	}

	public Foto obterPorId(Integer id) {
		Optional<Foto> foto = fr.findById(id);

		if (foto.isPresent()) {
			return foto.get();
		}
		return null;
	}

	public void deletarPorId(Integer id) {
		if (fr.existsById(id)) {
			fr.deleteById(id);
		}
	}
}
