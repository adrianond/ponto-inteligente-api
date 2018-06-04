package com.kazale.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.kazale.pontointeligente.api.entities.Empresa;
import com.kazale.pontointeligente.api.repositories.EmpresaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {

	/**
	 * Ao invés de utilizar @Autowired e obter uma instância verdadeira, utilizo um objeto fake utilzando @MockBean
	 */
	@MockBean
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaService empresaService;

	private static final String CNPJ = "51463645000100";

	@Before
	public void setUp() throws Exception {
		//qualquer coisa que o método findByCnpj receber devolver um obejto 'fake' empresa
		BDDMockito.given(this.empresaRepository.findByCnpj(Mockito.anyString())).willReturn(new Empresa());
		//passo um obejto do tipo empresa e retorna objeto do tipo empresa
		BDDMockito.given(this.empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());
	}

	@Test
	public void testBuscarEmpresaPorCnpj() {
		//busca objeto empresa 'fake' criada anteriormente
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(CNPJ);

		//verifica se ha um obejto empresa dentro do objeto Optional
		assertTrue(empresa.isPresent());
	}
	
	@Test
	public void testPersistirEmpresa() {
		//inseri obejto empresa 'fake' criado anteriormente
		Empresa empresa = this.empresaService.persistir(new Empresa());

		assertNotNull(empresa);
	}

}
