package br.com.compass.sprint4.controller;

import br.com.compass.sprint4.dto.request.RequestAssociatesDTO;
import br.com.compass.sprint4.dto.response.ResponseAssociatesDTO;

import br.com.compass.sprint4.service.AssociatesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/associates")
public class AssociatesController {

    @Autowired
    private AssociatesService associatesService;

    @PostMapping
    public ResponseEntity<ResponseAssociatesDTO> add(@RequestBody @Valid RequestAssociatesDTO request, UriComponentsBuilder uriComponentsBuilder) {
        ResponseAssociatesDTO save = associatesService.save(request, uriComponentsBuilder);
        URI uri = uriComponentsBuilder.path("associates/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @GetMapping
    public ResponseEntity<List<ResponseAssociatesDTO>> listAllAssociates(@RequestParam(required = false) String politicPosition,
                                                                         @RequestParam(required = false, defaultValue = "id") String sortBy) {
        List<ResponseAssociatesDTO> responseAssociatesDTOS = associatesService.get(politicPosition, sortBy);
        return ResponseEntity.ok(responseAssociatesDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAssociatesDTO> get(@PathVariable Long id) {
        ResponseAssociatesDTO responseAssociatesDTO = associatesService.getById(id);
        return ResponseEntity.ok(responseAssociatesDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid RequestAssociatesDTO request, @PathVariable Long id) {
        associatesService.update(request, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        associatesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
