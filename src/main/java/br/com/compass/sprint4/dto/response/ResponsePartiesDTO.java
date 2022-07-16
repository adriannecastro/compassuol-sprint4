package br.com.compass.sprint4.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponsePartiesDTO {

    private Long id;
    private String partyName;
    private String initials;
    private String ideology;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataOfFundation;
}
