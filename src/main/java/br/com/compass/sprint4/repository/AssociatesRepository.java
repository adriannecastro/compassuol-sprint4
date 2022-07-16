package br.com.compass.sprint4.repository;

import br.com.compass.sprint4.entities.AssociatesEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociatesRepository extends JpaRepository<AssociatesEntity, Long> {
    @Query("SELECT associates FROM AssociatesEntity associates WHERE (:politicPosition IS NULL OR :politicPosition = associates.politicPosition)")
    List<AssociatesEntity> findWithFilters(@Param("politicPosition") String politicPosition, Sort sortBy);
}
