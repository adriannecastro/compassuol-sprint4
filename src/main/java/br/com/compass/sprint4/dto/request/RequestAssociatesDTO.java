package br.com.compass.sprint4.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RequestAssociatesDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String politicPosition;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotBlank
    private String sex;

    private Integer partyId;

}
