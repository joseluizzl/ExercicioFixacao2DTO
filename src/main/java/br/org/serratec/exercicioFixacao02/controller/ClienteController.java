package br.org.serratec.exercicioFixacao02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.exercicioFixacao02.dto.ClienteDto;
import br.org.serratec.exercicioFixacao02.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> obterTodosOsClientes () {
		return ResponseEntity.ok(servico.obterTodosOsClientes());
	}
	
	@GetMapping("/nascimento")
	public ResponseEntity<List<ClienteDto>> obterPorNascimento(@RequestBody String nascimento) {
		return ResponseEntity.ok(servico.obterPorNascimento(nascimento));
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<ClienteDto>> obterClientePorNome(@RequestBody String nome) {
		return ResponseEntity.ok(servico.obterClientePorNome(nome));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastrarCliente(@Valid @RequestBody ClienteDto cliente) {		
		return new ResponseEntity<ClienteDto>(servico.cadastrarCliente(cliente), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> obterClientePorId (@PathVariable Long id) {
		Optional<ClienteDto> cliente = servico.obterClientePorId(id);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirPorId (@PathVariable Long id) {
		if (! servico.excluirPorId(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> alterarDadosCliente(@PathVariable Long id, @RequestBody ClienteDto clienteAlterado) {
			Optional<ClienteDto> cliente = servico.alterarDadosCliente(id, clienteAlterado);
			if (cliente.isEmpty()) {
				return ResponseEntity.ok(clienteAlterado);
			}
		return ResponseEntity.badRequest().build();
	}

}
