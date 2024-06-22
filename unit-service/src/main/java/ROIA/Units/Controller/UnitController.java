package ROIA.Units.Controller;

import ROIA.Units.DTO.UnitRequest;
import ROIA.Units.DTO.UnitResponse;
import ROIA.Units.Service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService unitsService;

    @GetMapping("/get-units")
    public List<UnitResponse> getAllUnits(){
        return unitsService.getAllUnits();
    }

    @GetMapping("/get-unit/{unitId}")
    public UnitResponse getUnitById(@PathVariable long unitId) {
        return unitsService.getUnitById(unitId);
    }

    @GetMapping("/get-developer-units/{developer}")
    public List<UnitResponse> getUnitsByDeveloper(@PathVariable String developer) {
        return unitsService.getUnitsByDeveloper(developer);
    }

    @GetMapping("/search-units/{key}")
    public List<UnitResponse> searchUnitsByTitle(@PathVariable String key) {
        return unitsService.searchUnitsByTitle(key);
    }

    @GetMapping("/filter-units")
    public List<UnitResponse> filterUnits(@RequestBody UnitRequest unitRequest) {
        return unitsService.filterUnits(unitRequest);
    }


}
