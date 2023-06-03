package example.notasapi.repository;

import example.notasapi.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INotaRepository extends JpaRepository<Nota, Integer> { }
