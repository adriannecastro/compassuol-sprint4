package br.com.compass.sprint4.service;

import br.com.compass.sprint4.dto.request.RequestPartiesDTO;
import br.com.compass.sprint4.dto.response.ResponseAssociatesDTO;
import br.com.compass.sprint4.dto.response.ResponsePartiesDTO;
import br.com.compass.sprint4.entities.AssociatesEntity;
import br.com.compass.sprint4.entities.PartiesEntity;
import br.com.compass.sprint4.exceptions.PartiesNotFoundException;
import br.com.compass.sprint4.repository.PartiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartiesService {

    @Autowired
    private PartiesRepository partiesRepository;

    @Autowired
    private ValidationServiceParties validationServiceParties;

    @Autowired
    private ModelMapper modelMapper;

    public List<ResponsePartiesDTO> get(String ideology ) {
        List<PartiesEntity> allParties = partiesRepository.findWithFilters(ideology);
        List<ResponsePartiesDTO> dtos = allParties.stream().map(partiesEntity -> modelMapper.map(partiesEntity, ResponsePartiesDTO.class)).collect(Collectors.toList());
        return dtos;
    }

    public ResponsePartiesDTO get(Long id) {
        PartiesEntity party = partiesRepository.findById(id).orElseThrow(PartiesNotFoundException::new);
        return modelMapper.map(party, ResponsePartiesDTO.class);
    }

    public ResponsePartiesDTO save(RequestPartiesDTO request) {
        validationServiceParties.validate(request);
        PartiesEntity entity = modelMapper.map(request, PartiesEntity.class);
        PartiesEntity savedEntity = partiesRepository.save(entity);
        return modelMapper.map(savedEntity, ResponsePartiesDTO.class);
    }

    public void delete(Long id) {
        partiesRepository.findById(id).orElseThrow(PartiesNotFoundException::new);
        partiesRepository.deleteById(id);
    }

    public void update(RequestPartiesDTO request, Long id) {
        validationServiceParties.validate(request);
        PartiesEntity entity = partiesRepository.findById(id).orElseThrow(PartiesNotFoundException::new);
        modelMapper.map(request, entity);
        partiesRepository.save(entity);
    }

    public List<ResponseAssociatesDTO> getAssociates(Integer partyId) {
        List<AssociatesEntity> party = partiesRepository.getPartiesAssociates(partyId);
        List<ResponseAssociatesDTO> dtos = party.stream().map(associatesEntity -> modelMapper.map(associatesEntity, ResponseAssociatesDTO.class)).collect(Collectors.toList());
        return dtos;
    }
}
