package br.org.serratec.exercicioFixacao02.dto;

import java.time.LocalDate;

import br.org.serratec.exercicioFixacao02.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record ClienteDto( 
		 Long id,
		 @NotBlank
		 String nome,
		 @Pattern(regexp="\\d{11}")
		 @NotBlank
		 String cpf,
		 @Email(message= "E-mail inválido")
		 @NotBlank
		 String email,
		 @Past
		 @NotNull
		 LocalDate dataNascimento) {
	
	public Cliente toEntity() {
		return new Cliente(this.id, this.nome, this.cpf, this.email, 
				this.dataNascimento);
		
		
	}
	
}
