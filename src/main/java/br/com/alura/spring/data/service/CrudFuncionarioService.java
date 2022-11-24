package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	private FuncionarioRepository funcionarioRepository;
	private CargoRepository cargoRepository;
	private UnidadeTrabalhoRepository unidadeRepository;
	private boolean system = true;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public CrudFuncionarioService(FuncionarioRepository funcionariorepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeRepository) {
		this.funcionarioRepository = funcionariorepository;
		this.cargoRepository = cargoRepository;
		this.unidadeRepository = unidadeRepository;
	}

	public void inicial(Scanner scanner) {
		while (this.system) {
			System.out.println("O que deseja fazer?");
			System.out.println("0 - Voltar ao menu principal");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			int action = scanner.nextInt();

			switch (action) {
			case 1: {
				this.salvar(scanner);
				break;
			}
			case 2: {
				this.atualizar(scanner);
				break;
			}
			case 3: {
				this.visualizar(scanner);
				break;
			}
			case 4: {
				this.deletar(scanner);
				break;
			}
			default:
				this.system = false;
			}
		}

	}

	public void salvar(Scanner scanner) {
		Funcionario funcionario = new Funcionario();

		System.out.println("Informe o nome");
		String nome = scanner.next();
		funcionario.setNome(nome);

		System.out.println("Informe o cpf");
		String cpf = scanner.next();
		funcionario.setCpf(cpf);

		System.out.println("Informe o salário");
		Double salario = scanner.nextDouble();
		funcionario.setSalario(salario);

		System.out.println("Informe a data de contratação");
		String data = scanner.next();
		LocalDate dataContratacao = LocalDate.parse(data, this.formatter);
		funcionario.setDataContratacao(dataContratacao);

		System.out.println("Informe a Id do cargo");
		Integer id = scanner.nextInt();
		Optional<Cargo> cargo = this.cargoRepository.findById(id);
		funcionario.setCargo(cargo.get());

		List<UnidadeTrabalho> unidades = unidades(scanner);
		funcionario.setUnidadeTrabalhos(unidades);

		this.funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}

	private List<UnidadeTrabalho> unidades(Scanner scanner) {
		boolean keepGoing = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();

		while (keepGoing) {
			System.out.println("Digite o Id da Unidade de trabalho ou 0 para sair");
			Integer id = scanner.nextInt();

			if (id != 0) {
				Optional<UnidadeTrabalho> unidade = this.unidadeRepository.findById(id);
				unidades.add(unidade.get());
			} else {
				keepGoing = false;
			}
		}
		return unidades;
	}

	public void atualizar(Scanner scanner) {
		Funcionario funcionario = new Funcionario();
		System.out.println("Id: ");
		int id = scanner.nextInt();
		funcionario.setId(id);

		System.out.println("Informe o nome");
		String nome = scanner.next();
		funcionario.setNome(nome);

		System.out.println("Informe o cpf");
		String cpf = scanner.next();
		funcionario.setCpf(cpf);

		System.out.println("Informe o salário");
		Double salario = scanner.nextDouble();
		funcionario.setSalario(salario);

		System.out.println("Informe a data de contratação");
		String data = scanner.next();
		LocalDate dataContratacao = LocalDate.parse(data, this.formatter);
		funcionario.setDataContratacao(dataContratacao);

		System.out.println("Informe a Id do cargo");
		Integer cargoId = scanner.nextInt();
		Optional<Cargo> cargo = this.cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());

		List<UnidadeTrabalho> unidades = unidades(scanner);
		funcionario.setUnidadeTrabalhos(unidades);

		this.funcionarioRepository.save(funcionario);
		System.out.println("Atualizado");
	}

	public void visualizar(Scanner scanner) {
		System.out.println("Qual página deseja acessar?");
		Integer page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		
		Page<Funcionario> funcionarios = this.funcionarioRepository.findAll(pageable);
		System.out.println(funcionarios);//mostra o n total de páginas
		System.out.println("Página atual: " + funcionarios.getNumber());
		System.out.println("Total de elemetentos: " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	public void deletar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		this.funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
}
