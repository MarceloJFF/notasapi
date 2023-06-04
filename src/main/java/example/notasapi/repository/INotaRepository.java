package example.notasapi.repository;

import example.notasapi.model.Categoria;
import example.notasapi.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Integer> {

}
