package com.kazale.pontointeligente.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.kazale.pontointeligente.api.entities.Lancamento;

@Transactional(readOnly = true)

/**
 * Crio a minha query utilizando o nome da classe seguido do nome do método (LancamentoRepository.findByFuncionarioId)
 * @author nss_admin
 *
 */
@NamedQueries({
		@NamedQuery(name = "LancamentoRepository.findByFuncionarioId", 
				query = "SELECT lanc FROM Lancamento lanc WHERE lanc.funcionario.id = :funcionarioId") })
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	/**
	 * Crio minha própria query com JPQL (linguagem SQL do JPA)
	 * @param funcionarioId
	 * @return
	 */
	List<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);

	/**
	 * Crio minha própria query com JPQL, neste caso utilizo paginação nos resultados
	 * @param funcionarioId
	 * @param pageable
	 * @return
	 */
	Page<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId, Pageable pageable);
}
