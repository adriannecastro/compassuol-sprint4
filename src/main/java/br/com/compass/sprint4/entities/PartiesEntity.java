package br.com.compass.sprint4.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Parties", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "initials"})})
public class PartiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonAlias("nome")
    @Column(name = "name", unique = true)
    private String partyName;

    @JsonAlias("sigla")
    @Column(name = "name", unique = true)
    private String initials;

    @JsonAlias("ideologia")
    private String ideology;

    @JsonAlias("dataFundacao")
    private LocalDate dataOfFundation;
}
