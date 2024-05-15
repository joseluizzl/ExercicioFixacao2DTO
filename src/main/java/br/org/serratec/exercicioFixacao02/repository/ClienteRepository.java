package br.org.serratec.exercicioFixacao02.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.exercicioFixacao02.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByDataNascimentoAfter(LocalDate nascimento);
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
