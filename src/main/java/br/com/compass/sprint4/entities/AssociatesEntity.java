package br.com.compass.sprint4.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class AssociatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String politicPosition;
    private LocalDate birthDate;
    private String sex;
    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private PartiesEntity partyId;
}
