package br.com.compass.sprint4.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class RequestPartiesDTO {

    @JsonAlias("nome")
    @NotBlank
    private String partyName;

    @JsonAlias("sigla")
    @NotBlank
    private String initials;

    @JsonAlias("ideologia")
    @NotBlank
    private String ideology;

    @JsonAlias("dataFundacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataOfFundation;

}
