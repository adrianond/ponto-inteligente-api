package com.kazale.pontointeligente.api.services;

import java.util.Optional;

import com.kazale.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 * Optional - uma forma muito mais interessante de representar atributos que podem ou não estar presentes em nosso código.
	 * Você pode pensar em um Optional como uma classe que pode ou não conter um valor não nulo. 
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

	Optional<Empresa> buscarPorId(Long id);
	
}
