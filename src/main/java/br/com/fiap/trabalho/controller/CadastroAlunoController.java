package br.com.fiap.trabalho.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.dto.EnderecoDTO;
import br.com.fiap.trabalho.dto.StatusDTO;
import br.com.fiap.trabalho.service.AlunoService;
import br.com.fiap.trabalho.service.EnderecoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("cadastro")
public class CadastroAlunoController {
	
	@Autowired
	private EnderecoService service;
	
	@Autowired
	private AlunoService alunoService;

	@GetMapping("/endereco/{id}")
	public EnderecoDTO getEnderecoById(@PathVariable Integer id) {
		return service.getEnderecoByID(id);
	}
	
	@PostMapping("/alunos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity save(@RequestBody CreditoDTO creditoDTO) {
		try {
			AlunoDTO res = alunoService.save(creditoDTO);
			
			if(res == null) {
				return new ResponseEntity("CEP Invalido", HttpStatus.NOT_ACCEPTABLE);
			}
			return new ResponseEntity(alunoService.save(creditoDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/alunos")
	public List<AlunoDTO> getAll() {
		return alunoService.getAll();
	}

	@GetMapping("/alunos/{id}")
	public Optional<AlunoDTO> getById(@PathVariable Integer id) {
		return alunoService.getById(id);
	}

	@DeleteMapping("/alunos/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		alunoService.delete(id);
		return new ResponseEntity("Deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/alunos/nome/{nome}")
	public List<AlunoDTO> getByName(@PathVariable String nome) {
		return alunoService.getByName(nome);
	}
	
	@GetMapping("alunos/status/{id}")
	public StatusDTO getStatusById(@PathVariable Integer id) {
		return alunoService.getAlunoStatus(id);
	}
	
}
