package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService {
	private final FuncionarioRepository repository;
	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatorioService(FuncionarioRepository repository) {
		this.repository = repository;
	}

	public void inicial(Scanner scanner) {
		while (this.system) {
			System.out.println("O que deseja fazer?");
			System.out.println("0 - Voltar ao menu principal");
			System.out.println("1 - Buscar funcionário por nome");
			System.out.println("2 - Buscar funcionário por nome, salário, data de contratação");
			System.out.println("3 - Buscar funcionário por data de contratação");
			System.out.println("4 - Buscar funcionário e salário");

			int action = scanner.nextInt();

			switch (action) {
			case 1: {
				this.buscarFuncionarioPorNome(scanner);
				break;
			}
			case 2: {
				this.buscarNomeSalarioMaiorData(scanner);
				break;
			}
			case 3: {
				this.buscaFuncionarioDataContratacao(scanner);
				break;
			}
			case 4: {
				this.pesquisaFuncionarioSalario();
				break;
			}
			default:
				this.system = false;
			}
		}

	}

	public void buscarFuncionarioPorNome(Scanner scanner) {
		System.out.println("Informe o nome");
		String nome = scanner.next();
		List<Funcionario> funcionarios = this.repository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}

	public void buscarNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Informe o nome");
		String nome = scanner.next();

		System.out.println("Informe o salario");
		Double salario = scanner.nextDouble();

		System.out.println("Informe a data de contratação");
		String data = scanner.next();
		LocalDate locadate = LocalDate.parse(data, this.formatter);

		List<Funcionario> funcionarios = this.repository.findNomeSalarioMaiorDataContratacao(nome, salario, locadate);
		funcionarios.forEach(System.out::println);
	}

	public void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Informe a data de contratação");
		String data = scanner.next();
		LocalDate locadate = LocalDate.parse(data, this.formatter);

		List<Funcionario> funcionarios = this.repository.findDataContratacaoMaior(locadate);
		funcionarios.forEach(System.out::println);
	}

	public void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> funcionarios = this.repository.findFuncionarioSalario();
		funcionarios.forEach(f -> System.out.println("Id: " + f.getId() + " | Nome: " + f.getNome() + 
				" | Salário: " + f.getSalario()));
	}

}
