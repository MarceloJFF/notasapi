package example.notasapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Nota {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String conteudo;
    private LocalDate dataCriacao;
    private String autor;
    private Categoria categoria;
}
