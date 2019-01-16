package com.kazale.pontointeligente.api.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kazale.pontointeligente.api.security.dto.JwtAuthenticationDto;
import com.kazale.pontointeligente.api.security.utils.JwtTokenUtil;

/**
 * filtro para verificar o acesso a cada requisição, ou seja, se existe no 
 * header do HTTP um token​ ​válido,​ ​autorizando​ ​o​ ​acesso
 * @author nss_admin
 *
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		
	   /**
	    * próximas 3 limhas implementação do token sem usar o Postman
	    */
	    /* request = gerarToken(request);
		 @SuppressWarnings("unchecked")
		 final Map<String, String> custom = (Map<String, String>) request.getAttribute("mapToken");
		 String token = custom.get("Authorization");*/
	   
	   //gerar token atraves do cliente
	   //request = gerarTokenAtravesDoCliente(request);
	   
		//implementação : request.getHeader(AUTH_HEADER) quando passo o token pelo Postman
	    String token = request.getHeader(AUTH_HEADER);
        
	    if (token != null && token.startsWith(BEARER_PREFIX)) {
        	token = token.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            if (jwtTokenUtil.tokenValido(token)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

	/**
	 * Método criado para gerar o token e inserir na chamada da API 
	 */
	private HttpServletRequest gerarToken(HttpServletRequest request) {

		final Map<String, String> customHeaders = new HashMap<String, String>();
		JwtAuthenticationDto authenticationDto = new JwtAuthenticationDto();
		authenticationDto.setEmail("admin@kazale.com");
		authenticationDto.setSenha("123456");

		log.info("Gerando token para o email {}.", authenticationDto.getEmail());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);
		customHeaders.put("Authorization", token);
		request.setAttribute("mapToken", customHeaders);

		return request;
	}
	
	/**
	 * Método criado para gerar o token e inserir na chamada da API 
	 */
	private HttpServletRequest gerarTokenAtravesDoCliente(HttpServletRequest request) {

		final Map<String, String> customHeaders = new HashMap<String, String>();
		JwtAuthenticationDto authenticationDto = new JwtAuthenticationDto();
		authenticationDto.setEmail((String) request.getHeader("user_email"));
		authenticationDto.setSenha((String) request.getHeader("user_password"));
		

		log.info("Gerando token para o email {}.", authenticationDto.getEmail());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);
		customHeaders.put("Authorization", token);
		request.setAttribute("mapToken", customHeaders);

		return request;
	}

}
