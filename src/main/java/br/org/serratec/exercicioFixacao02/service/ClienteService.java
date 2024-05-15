package br.org.serratec.exercicioFixacao02.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.exercicioFixacao02.dto.ClienteDto;
import br.org.serratec.exercicioFixacao02.model.Cliente;
import br.org.serratec.exercicioFixacao02.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorio;
	
	public List<ClienteDto> obterTodosOsClientes() {
		return repositorio.findAll().stream()
				.map(c -> new ClienteDto(c.getId(), c.getNome(),
						c.getCpf(), c.getEmail(), c.getDataNascimento()))
				.toList();
	}
	
	public Optional<ClienteDto> obterClientePorId(Long id) {
		Optional<Cliente> cliente = repositorio.findById(id);
		if (cliente.isPresent()) {
			return Optional.of(cliente.get().toDto());
		}
		return Optional.empty();
	}
	
	public ClienteDto cadastrarCliente(ClienteDto cliente) {
		Cliente clienteEntity = repositorio.save(cliente.toEntity());
		return clienteEntity.toDto();
	}
	
	public Optional<ClienteDto> alterarDadosCliente(Long id, ClienteDto clienteAlterado) {
		Cliente clienteEntity = clienteAlterado.toEntity();
		
		if (repositorio.existsById(id)) {
			clienteEntity.setId(id);
			repositorio.save(clienteEntity);
			return Optional.of(clienteEntity.toDto());
		}
		return Optional.empty();
	}
	
	public boolean excluirPorId(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
		 repositorio.deleteById(id);
		 return true;
	}

	public List<ClienteDto> obterPorNascimento(@RequestBody String nascimento) {
		return repositorio.findByDataNascimentoAfter(LocalDate.parse(nascimento)).stream()
				.map(c -> new ClienteDto(c.getId(), c.getNome(),
						c.getCpf(), c.getEmail(), c.getDataNascimento()))
				.toList();
	}
	
	public List<ClienteDto>obterClientePorNome(String nome) {
		return repositorio.findByNomeContainingIgnoreCase(nome).stream()
				.map(c -> new ClienteDto(c.getId(),c.getNome(),c.getCpf(),c.getEmail(),c.getDataNascimento()))
				.toList();
}
	
	
	
	
	
}
