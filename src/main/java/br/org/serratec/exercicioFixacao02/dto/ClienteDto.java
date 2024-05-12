package br.org.serratec.exercicioFixacao02.dto;

import java.time.LocalDate;

import br.org.serratec.exercicioFixacao02.model.Cliente;

public record ClienteDto( 
		 Long id,
		 String nome,
		 String cpf,
		 String email,
		 LocalDate data_nascimento) {
	
	public Cliente toEntity() {
		return new Cliente(this.id, this.nome, this.cpf, this.email, 
				this.data_nascimento);
		
		
	}
	
}
