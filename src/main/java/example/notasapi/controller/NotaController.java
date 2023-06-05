package example.notasapi.controller;

import example.notasapi.model.NotaDTO;
import example.notasapi.service.NotaService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaController {

    @Autowired
    final private NotaService notaService;

    // create
    @PostMapping
    public ResponseEntity<NotaDTO> createNota(@RequestBody NotaDTO novaNotaRequest) {
        return new ResponseEntity<>(notaService.create(novaNotaRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllNotas() {
        List<NotaDTO> notasDTO = notaService.findAll();
        if (notasDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma nota encontrada");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(notasDTO);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findNota(@PathVariable Integer id) {

        var notaDto = notaService.findById(id);
        if (notaDto != null) {
            return new ResponseEntity<>(notaDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota não encontradaa");

    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Integer id, @RequestBody NotaDTO updateNotaRequest) {
        return new ResponseEntity<>(notaService.update(id, updateNotaRequest), HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<NotaDTO> deleteNota(@PathVariable Integer id) {
        return new ResponseEntity<>(notaService.delete(id));
    }

}
