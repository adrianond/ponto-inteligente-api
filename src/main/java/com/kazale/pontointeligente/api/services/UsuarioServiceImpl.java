package com.kazale.pontointeligente.api.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kazale.pontointeligente.api.repositories.UsuarioRepository;
import com.kazale.pontointeligente.api.security.entities.Usuario;

/**
 * Esse serviço será utilizado para carregar os dados do usuário, e será utilizado no processo de​ ​autenticação​ ​e​ ​autorização
 * @author nss_admin
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));

	}
}
