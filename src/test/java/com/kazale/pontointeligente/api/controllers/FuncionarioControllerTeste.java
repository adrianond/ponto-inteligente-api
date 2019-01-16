package com.kazale.pontointeligente.api.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kazale.pontointeligente.api.entities.Empresa;
import com.kazale.pontointeligente.api.entities.Funcionario;
import com.kazale.pontointeligente.api.services.FuncionarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
// preciso de um contexto WEB para o teste
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FuncionarioControllerTeste {

	@Mock
	private FuncionarioService funcionarioService;
	Funcionario funcionario;
	@Autowired
	private MockMvc mvc;
	private static final String BUSCAR_FUNCIONARIO_ID_URL = "/api/funcionarios/";
	private static final Long ID = Long.valueOf(1);
	
	//"/api/funcionarios"
	
	@Test
	@WithMockUser
	public void deveTestarBuscaPorFuncionarioInvalido() throws Exception{
		
		when(funcionarioService.buscarPorId((Mockito.anyLong()))).thenReturn(Optional.empty());
		
		/*mvc.perform(MockMvcRequestBuilders.put(BUSCAR_FUNCIONARIO_ID_URL + ID)
		.accept(MediaType.APPLICATION_JSON)
		.content(MediaType.APPLICATION_JSON)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors").value("Funcionário não encontrado " + ID))
		.andExpect(jsonPath("$.data").isEmpty();*/
		
		
		mvc.perform(MockMvcRequestBuilders.put(BUSCAR_FUNCIONARIO_ID_URL + ID)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Funcionário não encontrado. ID inexistente."))
				.andExpect(jsonPath("$.data").isEmpty());
	}

	private String obterJsonRequisicaoPost() {

		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("28996612802");
		funcionario.setDataAtualizacao(new Date());
		funcionario.setDataCriacao(new Date());
		funcionario.setEmail("teste@gmail.com");
		funcionario.setEmpresa(new Empresa());
		funcionario.setId(Long.valueOf(1));
		funcionario.setNome("Pedro Santos");

		return null;
	}

	/*public void deveAtualizarDadosFuncionario() {

		when(funcionarioService.buscarPorId((Mockito.anyLong()))).thenReturn(returnFuncionario());

		// when(funcionarioService.buscarPorId(Mockito.anyLong())).the

		// this.funcionarioService.

	}*/

	private Optional<Funcionario> returnFuncionario() {
		funcionario = new Funcionario();
		funcionario.setCpf("28996612802");
		funcionario.setDataAtualizacao(new Date());
		funcionario.setDataCriacao(new Date());
		funcionario.setEmail("teste@gmail.com");
		funcionario.setEmpresa(returnaEmpresa());
        funcionario.setId(Long.valueOf(1));
        funcionario.setNome("Pedro Santos");
		return Optional.ofNullable(funcionario);
	}

	private Empresa returnaEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Teste.LTDA");
		empresa.setCnpj("");
		return empresa;
	}

}
