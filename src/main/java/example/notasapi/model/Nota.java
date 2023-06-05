package example.notasapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Nota {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String conteudo;
    private LocalDate dataCriacao;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
