package br.org.serratec.exercicioFixacao02.model;

import java.time.LocalDate;

import br.org.serratec.exercicioFixacao02.dto.ClienteDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60)
	private String nome;
	@Column(length = 11)
	private String cpf;
	@Column(length = 50)
	private String email;
	private LocalDate dataNascimento;
	
	public Cliente() {
		
	}
	
	public Cliente(Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ClienteDto toDto() {
		return new ClienteDto(this.id, this.nome, this.cpf, this.email,
				this.dataNascimento);
	}
	
}
