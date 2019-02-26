package com.kazale.pontointeligente.api.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kazale.pontointeligente.api.dtos.EmpresaDto;
import com.kazale.pontointeligente.api.entities.Empresa;
import com.kazale.pontointeligente.api.response.Response;
import com.kazale.pontointeligente.api.services.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	@Autowired
	private EmpresaService empresaService;

	public EmpresaController() {
	}

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		log.info("Buscando empresa por CNPJ: {}", cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);

		if (!empresa.isPresent()) {
			log.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		try {
			response.setData(this.converterEmpresaDto(empresa.get()));
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<EmpresaDto>> alterarDadosEmpresa(@PathVariable("id") Long id,
			@Valid @RequestBody EmpresaDto empresaDto, BindingResult result) {
		log.info("Atualizando empresa: {}", empresaDto.toString());
		
		Empresa empresa;
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		
		try {
			empresaDto.setId(id);
			validarEmpresa(empresaDto, result);
			
			if (result.hasErrors()) {
				log.error("Erro validando empresa: {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			empresa = this.empresaService.persistir(converterEmpresa(empresaDto, result));
			response.setData(this.converterEmpresaDto(empresa));
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * Popula um DTO com os dados de uma empresa.
	 * 
	 * @param empresa
	 * @return EmpresaDto
	 * @throws Exception 
	 */
	private EmpresaDto converterEmpresaDto(Empresa empresa) throws Exception {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());
		try {
			empresaDto.setDataAtualizacao(convertData(empresa.getDataAtualizacao()));
			empresaDto.setDataCriacao(convertData(empresa.getDataCriacao()));
		} catch (ParseException e) {
			throw new Exception(e.getMessage());
		}
		return empresaDto;
	}
	
	private void validarEmpresa(EmpresaDto empresaDto, BindingResult result) throws Exception {
		if (empresaDto.getId() == null) {
			result.addError(new ObjectError("empresa", "Empresa não informada."));
			return;
		}

		log.info("Validando empresa ID {}: ", empresaDto.getId());
		Optional<Empresa> empresa = empresaService.buscarPorId(empresaDto.getId());
		
		if (!empresa.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa não encontrada. Id inexistente."));
		}
		try {
			empresaDto.setDataCriacao(convertData(empresa.get().getDataCriacao()));
		} catch (ParseException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private Empresa converterEmpresa(EmpresaDto empresaDto, BindingResult result) throws Exception  {
		Empresa empresa = new Empresa();
		empresa.setId(empresaDto.getId());
		empresa.setCnpj(empresaDto.getCnpj());
		empresa.setRazaoSocial(empresaDto.getRazaoSocial());
		try {
			empresa.setDataCriacao(convertDataCriacao(empresaDto.getDataCriacao()));
		} catch (ParseException e) {
			throw new Exception(e.getMessage());
		}
		return empresa;
	}

	private Date convertDataCriacao(String data) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date parsedDate = dateFormat.parse(data);
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    Date date = new Date(timestamp.getTime());
	    return date;
	}
	
	private String convertData(Date data) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(data); 
	    return date;
	}
}
