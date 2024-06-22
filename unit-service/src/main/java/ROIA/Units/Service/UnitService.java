package ROIA.Units.Service;

import ROIA.Units.DTO.UnitRequest;
import ROIA.Units.DTO.UnitResponse;
import ROIA.Units.Model.Unit;
import ROIA.Units.Repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UnitService {

    private final UnitRepository unitsRepository;

    @Autowired
    public UnitService(UnitRepository unitsRepository) {
        this.unitsRepository = unitsRepository;
    }

    public List<UnitResponse> getAllUnits() {
        List<UnitResponse> unitResponseList = new ArrayList<>();
        for (Unit unit : unitsRepository.findAll()) {
            unitResponseList.add(mapModelToResponse(unit));
        }
        return unitResponseList;
    }

    public UnitResponse getUnitById(long unitId) {
        Unit unit = unitsRepository.getUnitById(unitId);
        return mapModelToResponse(unit);
    }

    public List<UnitResponse> getUnitsByDeveloper(String developer) {
        List<UnitResponse> unitResponseList = new ArrayList<>();
        for (Unit unit : unitsRepository.getUnitsByDeveloper(developer)) {
            unitResponseList.add(mapModelToResponse(unit));
        }
        return unitResponseList;
    }

    public List<UnitResponse> searchUnitsByTitle(String key) {
        List<UnitResponse> unitResponseList = new ArrayList<>();
        for (Unit unit : unitsRepository.getUnitsByTitleContaining(key)) {
            unitResponseList.add(mapModelToResponse(unit));
        }
        return unitResponseList;
    }

    public List<UnitResponse> filterUnits(UnitRequest unitRequest) {
        List<UnitResponse> unitResponseList = new ArrayList<>();
        Specification<Unit> spec = Specification.where(null);
        if (unitRequest.getCategory() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("category"), unitRequest.getCategory()));
        }
        if (unitRequest.getType() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("type"), unitRequest.getType()));
        }
        if (unitRequest.getN_bedrooms() != 0) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("n_bedrooms"), unitRequest.getN_bedrooms()));
        }
        if (unitRequest.getN_bathrooms() != 0) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("n_bathrooms"), unitRequest.getN_bathrooms()));
        }
        List<Unit> units = unitsRepository.findAll(spec);
        for (Unit unit : units) {
            unitResponseList.add(mapModelToResponse(unit));
        }
        return unitResponseList;
    }

    public UnitResponse mapModelToResponse(Unit unit) {
        return UnitResponse.builder()
                .id(unit.getId())
                .title(unit.getTitle())
                .status(unit.isStatus())
                .category(unit.getCategory())
                .type(unit.getType())
                .price(unit.getPrice())
                .address(unit.getAddress())
                .n_bedrooms(unit.getN_bedrooms())
                .n_bathrooms(unit.getN_bathrooms())
                .landSpace(unit.getLandSpace())
                .amenities(unit.getAmenities())
                .developer(unit.getDeveloper())
                .build();
    }


}
