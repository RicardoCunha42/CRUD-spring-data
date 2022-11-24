package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	private CargoRepository repository;
	private boolean system = true;

	public CrudCargoService(CargoRepository repository) {
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
		Cargo cargo = new Cargo();
		System.out.println("Descreva o cargo");
		String descricao = scanner.next();
		cargo.setDescricao(descricao);
		this.repository.save(cargo);
		System.out.println("Salvo");
	}
	
	public void atualizar(Scanner scanner) {
		Cargo cargo = new Cargo();
		System.out.println("Id: ");
		int id = scanner.nextInt();
		
		System.out.println("Descreva o cargo");
		String descricao = scanner.next();
		
		cargo.setId(id);
		cargo.setDescricao(descricao);
		this.repository.save(cargo);
		System.out.println("Atualizado");
	}
	
	public void visualizar() {
		Iterable<Cargo> cargos = this.repository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
		System.out.println("Vizualizado");
	}
	
	public void deletar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		this.repository.deleteById(id);
		System.out.println("Deletado");
	}
}
