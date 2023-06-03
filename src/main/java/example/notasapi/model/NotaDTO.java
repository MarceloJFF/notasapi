package example.notasapi.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotaDTO {
    private String titulo;
    private String conteudo;
    private LocalDate dataCriacao;
    private String autor;
    private Categoria categoria;
}
