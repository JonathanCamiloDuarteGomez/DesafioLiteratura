package cdg.DesafioLiteratura.Literatura.repository;

import cdg.DesafioLiteratura.Literatura.Modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    //se usan los nombres de las clases no de la bd
    Optional<Autor> findByNombre(String nombre);
    @Query("SELECT a FROM Autor a")
    List<Autor> listadoDeAutoresConsultados();
    @Query("select au from Autor au where :dato BETWEEN au.fechaDeNacimiento and au.fechaDeFallecimiento")
    List<Autor> vivos(Double dato);
}
