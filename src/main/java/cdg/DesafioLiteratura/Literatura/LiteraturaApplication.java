package cdg.DesafioLiteratura.Literatura;

import cdg.DesafioLiteratura.Literatura.principal.Principal;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication {

	public static void main(String[] args) {
		Principal controlador = new Principal();
		controlador.ejecutar();

	}

}
