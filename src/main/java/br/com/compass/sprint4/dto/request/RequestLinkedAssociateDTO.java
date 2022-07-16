package br.com.compass.sprint4.dto.request;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class RequestLinkedAssociateDTO {

    @Positive
    private Long AssociatesId;
    @Positive
    private Long PartiesId;
}
