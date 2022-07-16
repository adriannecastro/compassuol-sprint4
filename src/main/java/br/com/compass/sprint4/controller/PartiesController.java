package br.com.compass.sprint4.controller;

import br.com.compass.sprint4.dto.request.RequestPartiesDTO;
import br.com.compass.sprint4.dto.response.ResponseAssociatesDTO;
import br.com.compass.sprint4.dto.response.ResponsePartiesDTO;
import br.com.compass.sprint4.service.PartiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parties")
public class PartiesController {

    @Autowired
    private PartiesService partiesService;

    @GetMapping
    public ResponseEntity<List<ResponsePartiesDTO>> get(@RequestParam(required = false) String ideology) {
        List<ResponsePartiesDTO> responsePartiesDTOS = partiesService.get(ideology);
        return ResponseEntity.ok(responsePartiesDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePartiesDTO> get(@PathVariable Long id){
        ResponsePartiesDTO responseStateDTO = partiesService.get(id);
        return ResponseEntity.ok(responseStateDTO);
    }

    @GetMapping("/{id}/associates")
    public ResponseEntity<List<ResponseAssociatesDTO>> getAssociates(@PathVariable Integer id){
        List<ResponseAssociatesDTO> responseAssociatesDTO = partiesService.getAssociates(id);
        return ResponseEntity.ok(responseAssociatesDTO);
    }

    @PostMapping
    public ResponseEntity<ResponsePartiesDTO> post(@RequestBody @Valid RequestPartiesDTO request) {
        ResponsePartiesDTO response = partiesService.save(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@RequestBody @Valid RequestPartiesDTO request, @PathVariable Long id) {
        partiesService.update(request, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        partiesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
