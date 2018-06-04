package com.kazale.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.kazale.pontointeligente.api.entities.Empresa;

/**
 * Entidade Empresa
 * Long - id da entidade
 * @author nss_admin
 *
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	/**
	 * @Transactional(readOnly = true) - para não precisamos capturar o entityManager, iniciar uma transação com o método  begin(),
	 *  finalizar a transação com método commit(), 
	 *  tratar os erros dentro do bloco try-catch e chamar o rollback() caso necessário para cancelar a transação, utilizamos a anotation -@Transactional
	 *  (readOnly = true) - porque é um método apenas de leitura - 'select'
	 * 
	 * @param cnpj
	 * @return
	 */
	@Transactional(readOnly = true)
	//realiza o select no BD pelo cnpj informado
	Empresa findByCnpj(String cnpj);

}
