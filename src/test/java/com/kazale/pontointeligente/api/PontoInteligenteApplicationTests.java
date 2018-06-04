package com.kazale.pontointeligente.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

//Teste que faz o carregamento do contexto
@RunWith(SpringRunner.class)
@SpringBootTest
//ativa o profile de teste para não utilizar o msql nos testes, ou seja, carrega os dados de configuração do H2 no arquivo  application-test.properties
@ActiveProfiles("test")
public class PontoInteligenteApplicationTests {

	@Test
	public void contextLoads() {
	}

}
