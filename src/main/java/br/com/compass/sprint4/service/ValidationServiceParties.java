package br.com.compass.sprint4.service;

import br.com.compass.sprint4.dto.request.RequestPartiesDTO;
import br.com.compass.sprint4.enums.IdeologyEnum;
import br.com.compass.sprint4.exceptions.IdeologyInvalidException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidationServiceParties {

    public void validate(RequestPartiesDTO requestPartiesDTO){

        boolean isValid = Arrays.stream(IdeologyEnum.values()).anyMatch(ideologyEnum -> ideologyEnum.getValue().equals(requestPartiesDTO.getIdeology()));

        if(!isValid){
            throw new IdeologyInvalidException();
        }
    }
}
