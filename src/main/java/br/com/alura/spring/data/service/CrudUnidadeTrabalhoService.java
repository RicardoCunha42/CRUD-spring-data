package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	private UnidadeTrabalhoRepository repository;
	private boolean system = true;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
		this.repository = repository;
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
				this.visualizar();
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
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		
		System.out.println("Informe o nome da unidade de trabalho");
		String descricao = scanner.next();
		unidadeTrabalho.setDescricao(descricao);
		
		System.out.println("Informe o endereço da unidade de trabalho");
		String endereco = scanner.next();
		unidadeTrabalho.setDescricao(endereco);
		
		this.repository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}

	public void atualizar(Scanner scanner) {
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		System.out.println("Id: ");
		int id = scanner.nextInt();

		System.out.println("Informe o nome da unidade de trabalho");
		String descricao = scanner.next();
		
		System.out.println("Informe o endereço da unidade de trabalho");
		String endereco = scanner.next();

		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		this.repository.save(unidadeTrabalho);
		System.out.println("Atualizado");
	}

	public void visualizar() {
		Iterable<UnidadeTrabalho> unidadeTrabalho = this.repository.findAll();
		unidadeTrabalho.forEach(unidade -> System.out.println(unidade));
		System.out.println("Vizualizado");
	}

	public void deletar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		this.repository.deleteById(id);
		System.out.println("Deletado");
	}
}
