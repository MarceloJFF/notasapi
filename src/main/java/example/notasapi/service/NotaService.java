package example.notasapi.service;

import example.notasapi.mapper.NotaMapper;
import example.notasapi.model.Nota;
import example.notasapi.model.NotaDTO;
import example.notasapi.repository.INotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public NotaDTO create(NotaDTO novaNotaRequest){
        NotaMapper mapper = new NotaMapper();
        Nota novaNota = repo.save(mapper.map(novaNotaRequest));
        return mapper.map(novaNota);
    }
}
