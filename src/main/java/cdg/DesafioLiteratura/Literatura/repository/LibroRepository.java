package cdg.DesafioLiteratura.Literatura.repository;

import cdg.DesafioLiteratura.Literatura.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    //se usan los nombres de las clses no de la bd
    @Query("SELECT l FROM Libro l")
    List<Libro> listadoDeLibrosConsultados();
    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE i = :idioma")
    List<Libro> listadoDeLibrosPorIdioma(@Param("idioma") String idioma);



}
