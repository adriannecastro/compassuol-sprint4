package br.com.compass.sprint4.repository;

import br.com.compass.sprint4.entities.AssociatesEntity;
import br.com.compass.sprint4.entities.PartiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartiesRepository extends JpaRepository<PartiesEntity, Long> {
    @Query("SELECT parties FROM PartiesEntity parties WHERE (:ideology IS NULL OR :ideology = parties.ideology)")
    List<PartiesEntity> findWithFilters(@Param("ideology") String ideology);

    @Query("SELECT associates FROM AssociatesEntity associates WHERE (:partyId = associates.partyId)")
    List<AssociatesEntity> getPartiesAssociates(@Param("partyId") Integer partyId);
}

