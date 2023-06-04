package example.notasapi.service;

import example.notasapi.mapper.NotaMapper;
import example.notasapi.model.Categoria;
import example.notasapi.model.Nota;
import example.notasapi.model.NotaDTO;
import example.notasapi.repository.INotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    INotaRepository repo;

    public List<NotaDTO> getAll(){
        NotaMapper mapper = new NotaMapper();
        List<Nota> notas = repo.findAll();
        return mapper.map(notas);
    }

    public NotaDTO findById(Integer id) {
        NotaMapper mapper = new NotaMapper();

        Optional<Nota> result = repo.findById(id);
        if ( result.isEmpty() ) return null;

        Nota nota = result.get();

        return mapper.map(nota);
    }

    public NotaDTO create(NotaDTO createNotaRequest){
        NotaMapper mapper = new NotaMapper();
        Nota novaNota = repo.save(mapper.map(createNotaRequest));
        return mapper.map(novaNota);
    }

    public NotaDTO update(Integer id, NotaDTO updateNotaRequest) {
        NotaMapper mapper = new NotaMapper();

        String titulo = updateNotaRequest.getTitulo();
        String conteudo = updateNotaRequest.getConteudo();
        LocalDate dataCriacao = updateNotaRequest.getDataCriacao();
        String autor = updateNotaRequest.getAutor();
        Categoria categoria = updateNotaRequest.getCategoria();

        Optional<Nota> result = repo.findById(id);
        if ( result.isEmpty() ) return null;

        Nota notaDesatualizada = result.get();
        notaDesatualizada.setTitulo(titulo);
        notaDesatualizada.setConteudo(conteudo);
        notaDesatualizada.setDataCriacao(dataCriacao);
        notaDesatualizada.setAutor(autor);
        notaDesatualizada.setCategoria(categoria);

        Nota notaAtualizada = repo.save(notaDesatualizada);
        return mapper.map(notaAtualizada);
    }

    public HttpStatus delete(Integer id){
        try {
            repo.delete(repo.findById(id).get());
            return HttpStatus.OK;
        } catch (NoSuchElementException e) {
            System.err.println("erro: " + e);
            return HttpStatus.OK;
        } catch (Exception e){
            System.err.println("erro: " + e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
