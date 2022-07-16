package br.com.compass.sprint4.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;


@Data
public class ResponseAssociatesDTO {

    private Long id;
    private String name;
    private String politicPosition;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String sex;
    private Long partyId;
}
