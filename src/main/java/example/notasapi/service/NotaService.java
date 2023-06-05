package example.notasapi.service;

import example.notasapi.mapper.NotaMapper;
import example.notasapi.model.Categoria;
import example.notasapi.model.Nota;
import example.notasapi.model.NotaDTO;
import example.notasapi.repository.INotaRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    INotaRepository repo;

   

    public List<NotaDTO> findAll() {
        NotaMapper mapper = new NotaMapper();
        List<Nota> notas = repo.findAll();
        List<NotaDTO> notasDTO = new ArrayList<>();
        if(notas.size()>0){    
           notasDTO = mapper.map(notas);
        }
        
        return notasDTO;
    }

    public NotaDTO findById(Integer id) {
        NotaMapper mapper = new NotaMapper();
        Optional<Nota> result = repo.findById(id);
        if (result.isPresent()) {
            var notaModel = result.get();
            return mapper.map(notaModel);
        } else {
            NotaDTO notaModel = null;
            return notaModel; // Instância vazia de NotaDTO
        }
    }

    @Transactional
    public NotaDTO create(NotaDTO createNotaRequest) {
        NotaMapper mapper = new NotaMapper();
        Nota novaNota = repo.save(mapper.map(createNotaRequest));
        return mapper.map(novaNota);
    }

    public NotaDTO update(Integer id, NotaDTO updateNotaRequest) {
        NotaMapper mapper = new NotaMapper();

        Optional<Nota> result = repo.findById(id);
        if (result.isEmpty())
            return null;
        Nota notaDesatualizada = result.get();
        Nota notaAtualizada = updateNota(notaDesatualizada, updateNotaRequest);
        notaAtualizada = repo.save(notaAtualizada);
        return mapper.map(notaAtualizada);
    }
    public Nota updateNota(Nota notaDesatualizada,NotaDTO updateNotaRequest){
        
        String titulo = updateNotaRequest.getTitulo();
        String conteudo = updateNotaRequest.getConteudo();
        LocalDate dataCriacao = updateNotaRequest.getDataCriacao();
        String autor = updateNotaRequest.getAutor();
        Categoria categoria = updateNotaRequest.getCategoria();

        notaDesatualizada.setTitulo(titulo);
        notaDesatualizada.setConteudo(conteudo);
        notaDesatualizada.setDataCriacao(dataCriacao);
        notaDesatualizada.setAutor(autor);
        notaDesatualizada.setCategoria(categoria);

        return notaDesatualizada;
    }

    @Transactional
    public HttpStatus delete(Integer id) {
        try {
            repo.delete(repo.findById(id).get());
            return HttpStatus.OK;
        } catch (NoSuchElementException e) {
            System.err.println("erro: " + e);
            return HttpStatus.OK;
        } catch (Exception e) {
            System.err.println("erro: " + e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
