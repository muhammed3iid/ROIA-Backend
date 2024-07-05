package com.ROIA.shared_unit.Controller;

import com.ROIA.shared_unit.DTO.UserSharesDTO;
import com.ROIA.shared_unit.Model.Share;
import com.ROIA.shared_unit.Model.SharedUnit;
import com.ROIA.shared_unit.Service.SharedUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shared-units")
public class SharedUnitController {
    private final SharedUnitService sharedUnitService;

    @GetMapping("/get-shared-units")
    public List<SharedUnit> getAllSharedUnits() {
        return sharedUnitService.getAllSharedUnits();
    }

    @GetMapping("/get-shared-unit/{id}")
    public SharedUnit getSharedUnitById(@PathVariable Long id) {
        return sharedUnitService.getSharedUnitById(id);
    }

    @PostMapping("/{unitId}/buy-shares/{userId}/{numberOfShares}")
    public String buyShares(@PathVariable Long userId, @PathVariable Long unitId, @PathVariable int numberOfShares) {
        return sharedUnitService.buyShares(userId, unitId, numberOfShares);
    }

    @PostMapping("/{unitId}/sell-shares/{userId}/{numberOfShares}")
    public String sellShares(@PathVariable Long userId, @PathVariable Long unitId, @PathVariable int numberOfShares) {
        return sharedUnitService.sellShares(userId, unitId, numberOfShares);
    }

    @GetMapping("/user/{userId}/shares")
    public List<UserSharesDTO> getSharesByUserId(@PathVariable Long userId) {
        return sharedUnitService.getSharesByUserId(userId);
    }

    @PostMapping("/create-shared-unit")
    public SharedUnit createSharedUnit(@RequestBody SharedUnit sharedUnit) {
        return sharedUnitService.saveSharedUnit(sharedUnit);
    }

    @DeleteMapping("delete-shared-unit/{id}")
    public void deleteSharedUnit(@PathVariable Long id) {
        sharedUnitService.deleteSharedUnit(id);
    }
}
