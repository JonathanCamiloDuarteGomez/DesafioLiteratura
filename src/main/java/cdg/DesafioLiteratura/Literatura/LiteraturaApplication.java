package cdg.DesafioLiteratura.Literatura;

import cdg.DesafioLiteratura.Literatura.principal.Principal;
import cdg.DesafioLiteratura.Literatura.repository.AutorRepository;
import cdg.DesafioLiteratura.Literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	//Las inyecciones de dependencias solo se puede llamar en una clase creada por el propio Spring
	@Autowired//Inyeccion de dependencias
	private LibroRepository repository;
	@Autowired//cada clase necesita su manipulacion del repositorio
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal controlador = new Principal(repository,autorRepository);
		controlador.ejecutar();
	}
}
