package br.com.compass.sprint4.service;

import br.com.compass.sprint4.dto.request.RequestAssociatesDTO;
import br.com.compass.sprint4.enums.PoliticPositionEnum;
import br.com.compass.sprint4.enums.SexEnum;
import br.com.compass.sprint4.exceptions.PoliticPositionInvalidException;
import br.com.compass.sprint4.exceptions.SexInvalidException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidationServiceAssociates {

    public void validatePoliticPosition(RequestAssociatesDTO requestAssociatesDTO) {

        boolean isValid = Arrays.stream(PoliticPositionEnum.values()).anyMatch(politicPositionEnum ->
                politicPositionEnum.getValue().equals(requestAssociatesDTO.getPoliticPosition()));

        if (!isValid) {
            throw new PoliticPositionInvalidException();
        }
    }

    public void validateSex(RequestAssociatesDTO requestAssociatesDTO) {

        boolean isValid = Arrays.stream(SexEnum.values()).anyMatch(sexEnum ->
                sexEnum.getValue().equalsIgnoreCase(requestAssociatesDTO.getSex()));

        if (!isValid) {
            throw new SexInvalidException();
        }
    }
}
