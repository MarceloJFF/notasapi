package example.notasapi.controller;

import example.notasapi.model.Nota;
import example.notasapi.model.NotaDTO;
import example.notasapi.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaController {

    final private NotaService notaService;

    // create
    @PostMapping
    public ResponseEntity<NotaDTO> createNota(@RequestBody NotaDTO novaNotaRequest){
        return new ResponseEntity<>(notaService.create(novaNotaRequest), HttpStatus.CREATED);
    }

    // read
    @GetMapping
    public ResponseEntity<List<NotaDTO>> getAllNotas(){
        return new ResponseEntity<>(notaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> findNota(@PathVariable Integer id){
        return new ResponseEntity<>(notaService.findById(id), HttpStatus.OK);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Integer id, @RequestBody NotaDTO updateNotaRequest){
        return new ResponseEntity<>(notaService.update(id, updateNotaRequest), HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<NotaDTO> deleteNota(@PathVariable Integer id){
        return new ResponseEntity<>(notaService.delete(id));
    }



}
