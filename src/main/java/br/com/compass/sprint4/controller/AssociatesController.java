package br.com.compass.sprint4.controller;

import br.com.compass.sprint4.dto.request.RequestAssociatesDTO;
import br.com.compass.sprint4.dto.request.RequestLinkedAssociateDTO;
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

    //@PostMapping("/parties")
    //public ResponseEntity<Void> bindAssociatedWithAParty(@RequestBody @Valid RequestLinkedAssociateDTO request) {
        //associatesService.linksAssociatedWithTheParty(request);
       // return ResponseEntity.status(201).build();
    //}

    //POST- /associados/partidos (Vincula um associado a um partido, como body:{“idAssociado”:10, “idPartido”:10})

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

    @DeleteMapping("/{id}/parties/{partiesId}")
    public ResponseEntity<ResponseAssociatesDTO> delete(@PathVariable Long id, @PathVariable Long partiesId) {
        ResponseAssociatesDTO responseAssociatesDTO = associatesService.delete(id, partiesId);
        return ResponseEntity.ok(responseAssociatesDTO);
    }

    //DELETE- /associados/{id}/partidos/{id}(Remove determinado associado daquele partido)

}
