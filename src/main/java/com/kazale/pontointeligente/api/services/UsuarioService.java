package com.kazale.pontointeligente.api.services;

import java.util.Optional;
import com.kazale.pontointeligente.api.security.entities.Usuario;

/**
 * Agora será criado um serviço que será responsável por chamar o repositório: UsuarioRepository             
 * @author nss_admin
 *
 */
public interface UsuarioService {
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	Optional<Usuario> buscarPorEmail(String email);

}
