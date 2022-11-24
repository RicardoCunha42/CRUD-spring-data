package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.service.RelatorioService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService unidadeService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public SpringDataApplication(CrudCargoService cargoService, 
			CrudUnidadeTrabalhoService unidadeService,
			CrudFuncionarioService funcionarioService, 
			RelatorioService relatorioService,
			RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.unidadeService = unidadeService;
		this.funcionarioService = funcionarioService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		boolean system = true;

		while (system) {
			System.out.println("O que deseja fazer?");
			System.out.println("0 - Encerrar");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade de Trabalho");
			System.out.println("3 - Funcionário");
			System.out.println("4 - Relatório");
			System.out.println("5 - Relatório dinâmico");
			int action = scan.nextInt();

			switch (action) {
			case 1: {
				cargoService.inicial(scan);
				break;
			}
			case 2: {
				unidadeService.inicial(scan);
				break;
			}
			case 3: {
				funcionarioService.inicial(scan);
				break;
			}
			case 4: {
				relatorioService.inicial(scan);
				break;
			}
			case 5: {
				relatorioFuncionarioDinamico.inicial(scan);
				break;
			}
			default:
				system = false;
			}

		}
	}

}
