package example.notasapi.mapper;

import example.notasapi.model.Nota;
import example.notasapi.model.NotaDTO;

import java.util.ArrayList;
import java.util.List;

public class NotaMapper {
    public List<NotaDTO> map(List<Nota> entities){
        List<NotaDTO> dtos = new ArrayList<>();
        for ( Nota e : entities) {
            NotaDTO dto = new NotaDTO();
            dto.setTitulo(e.getTitulo());
            dto.setConteudo(e.getConteudo());
            dto.setDataCriacao(e.getDataCriacao());
            dto.setAutor(e.getAutor());
            dto.setCategoria(e.getCategoria());
            dtos.add(dto);
        }
        return dtos;
    }

    public NotaDTO map(Nota entity){
        NotaDTO dto = new NotaDTO();
        dto.setTitulo(entity.getTitulo());
        dto.setConteudo(entity.getConteudo());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setAutor(entity.getAutor());
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public Nota map(NotaDTO dto){
        Nota entity = new Nota();
        entity.setTitulo(dto.getTitulo());
        entity.setConteudo(dto.getConteudo());
        entity.setDataCriacao(dto.getDataCriacao());
        entity.setAutor(dto.getAutor());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }
}
