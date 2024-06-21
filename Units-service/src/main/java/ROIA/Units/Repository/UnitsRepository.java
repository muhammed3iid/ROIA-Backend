package ROIA.Units.Repository;

import ROIA.Units.Model.Unit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitsRepository extends JpaRepository<Unit, Long> {
    Unit getUnitById(Long id);
    List<Unit> getUnitsByDeveloper(String developer);
    List<Unit> getUnitsByTitleContaining(String key);

    List<Unit> findAll(Specification<Unit> spec);
}
