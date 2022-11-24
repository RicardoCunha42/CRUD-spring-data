package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
@Service
public class RelatorioFuncionarioDinamico {
	private FuncionarioRepository funcionarioRepository;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		System.out.println("Digite o nome:");
		String nome = scanner.next();

		if (nome.equalsIgnoreCase("null")) {
			nome = null;
		}

		System.out.println("Digite o cpf:");
		String cpf = scanner.next();

		if (cpf.equalsIgnoreCase("null")) {
			cpf = null;
		}

		System.out.println("Digite o salario:");
		Double salario = scanner.nextDouble();

		if (salario == 0) {
			salario = null;
		}

		System.out.println("Digite a data de contratação:");
		String data = scanner.next();

		LocalDate dataContratacao;
		if (data.equalsIgnoreCase("null")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}

		List<Funcionario> funcionarios = this.funcionarioRepository
				.findAll(Specification.where(SpecificationFuncionario.nome(nome)
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))));
		
		funcionarios.forEach(System.out::println);
	}

}
