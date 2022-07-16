package br.com.compass.sprint4.service;

import br.com.compass.sprint4.dto.request.RequestAssociatesDTO;
import br.com.compass.sprint4.dto.request.RequestLinkedAssociateDTO;
import br.com.compass.sprint4.dto.response.ResponseAssociatesDTO;
import br.com.compass.sprint4.entities.AssociatesEntity;
import br.com.compass.sprint4.entities.PartiesEntity;
import br.com.compass.sprint4.exceptions.AssociatesNotFoudException;
import br.com.compass.sprint4.exceptions.PartiesNotFoundException;
import br.com.compass.sprint4.repository.AssociatesRepository;
import br.com.compass.sprint4.repository.PartiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociatesService {

    @Autowired
    private AssociatesRepository associatesRepository;

    @Autowired
    private ValidationServiceAssociates validationServiceAssociates;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseAssociatesDTO save(RequestAssociatesDTO request, UriComponentsBuilder uriComponentsBuilder) {
        validationServiceAssociates.validatePoliticPosition(request); ;
        validationServiceAssociates.validateSex(request);
        AssociatesEntity entity = modelMapper.map(request, AssociatesEntity.class);
        AssociatesEntity savedEntity = associatesRepository.save(entity);
        return modelMapper.map(savedEntity, ResponseAssociatesDTO.class);
    }

    //public void linksAssociatedWithTheParty(RequestLinkedAssociateDTO request) {
        //Long PartiesId = request.getPartiesId();
        //Long AssociatesId = request.getAssociatesId();
        //AssociatesEntity associatesEntity = associatesRepository.findById(AssociatesId).orElseThrow(AssociatesNotFoudException::new);

    //}

    public List<ResponseAssociatesDTO> get(String politicPosition, String sortBy) {
        List<AssociatesEntity> associates = associatesRepository.findWithFilters(politicPosition, Sort.by(Sort.Direction.ASC, sortBy));
        return associates.stream().map(associateEntity -> modelMapper.map(
                associateEntity, ResponseAssociatesDTO.class)).collect(Collectors.toList());
    }

    public ResponseAssociatesDTO getById(Long id) {
        AssociatesEntity associateEntity = associatesRepository.findById(id).orElseThrow(AssociatesNotFoudException::new);
        return modelMapper.map(associateEntity, ResponseAssociatesDTO.class);
    }

    public void delete(Long id) {
        associatesRepository.findById(id).orElseThrow(AssociatesNotFoudException::new);
        associatesRepository.deleteById(id);
    }

    public void update(RequestAssociatesDTO request, Long id) {
        validationServiceAssociates.validatePoliticPosition(request);
        validationServiceAssociates.validateSex(request);
        AssociatesEntity entity = associatesRepository.findById(id).orElseThrow(AssociatesNotFoudException::new);
        modelMapper.map(request, entity);
        associatesRepository.save(entity);
    }

    //public ResponseAssociatesDTO delete(Long associatesId, Long partiesId) {
        //partiesRepository.findById(partiesId).orElseThrow(PartiesNotFoundException::new);
        //AssociatesEntity associatesEntity = associatesRepository.findById(associatesId).orElseThrow(AssociatesNotFoudException::new);
        //associatesEntity.setPartyId(null);
        //associatesRepository.save(associatesEntity);
        //return modelMapper.map(associatesEntity, ResponseAssociatesDTO.class);
    //}
}
