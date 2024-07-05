package com.ROIA.shared_unit.Service;

import com.ROIA.shared_unit.DTO.UserSharesDTO;
import com.ROIA.shared_unit.Model.Share;
import com.ROIA.shared_unit.Model.SharedUnit;
import com.ROIA.shared_unit.Repository.ShareRepository;
import com.ROIA.shared_unit.Repository.SharedUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SharedUnitService {

    private final SharedUnitRepository sharedUnitRepository;
    private final ShareRepository shareRepository;


    @Autowired
    public SharedUnitService(SharedUnitRepository sharedUnitRepository, ShareRepository shareRepository) {
        this.sharedUnitRepository = sharedUnitRepository;
        this.shareRepository = shareRepository;
    }

    public List<SharedUnit> getAllSharedUnits() {
        return sharedUnitRepository.findAll();
    }

    public SharedUnit getSharedUnitById(Long id) {
        return sharedUnitRepository.findById(id).orElse(null);
    }

    public SharedUnit saveSharedUnit(SharedUnit sharedUnit) {
        return sharedUnitRepository.save(sharedUnit);
    }

    public void deleteSharedUnit(Long id) {
        sharedUnitRepository.deleteById(id);
    }

    public String buyShares(Long userId, Long unitId, int numberOfShares) {
        Optional<SharedUnit> unitOpt = sharedUnitRepository.findById(unitId);

        if (!unitOpt.isPresent()) {
            return "Unit not found";
        }

        SharedUnit unit = unitOpt.get();

        if (unit.getAvailableShares() < numberOfShares) {
            return "Not enough shares available";
        }

        double shareValue = numberOfShares * unit.getPricePerShare();
        Share share = new Share();
        share.setShareValue(shareValue);
        share.setPercentage((double) numberOfShares / unit.getTotalShares() * 100);
        share.setSharedUnit(unit);
        share.setUserId(userId); // Set the userId directly

        unit.setAvailableShares(unit.getAvailableShares() - numberOfShares);

        shareRepository.save(share);
        sharedUnitRepository.save(unit);

        return "Shares purchased successfully";
    }

    public String sellShares(Long userId, Long unitId, int numberOfShares) {
        Optional<SharedUnit> unitOpt = sharedUnitRepository.findById(unitId);

        if (!unitOpt.isPresent()) {
            return "Unit not found";
        }

        SharedUnit unit = unitOpt.get();

        List<Share> userShares = shareRepository.findByUserIdAndSharedUnitId(userId, unitId);
        int totalUserShares = userShares.stream().mapToInt(share -> (int) (share.getPercentage() * unit.getTotalShares() / 100)).sum();

        if (totalUserShares < numberOfShares) {
            return "Not enough shares to sell";
        }

        double shareValue = numberOfShares * unit.getPricePerShare();

        // Remove shares from user portfolio
        int sharesToSell = numberOfShares;
        for (Share share : userShares) {
            int shareQuantity = (int) (share.getPercentage() * unit.getTotalShares() / 100);
            if (shareQuantity <= sharesToSell) {
                sharesToSell -= shareQuantity;
                shareRepository.delete(share);
            } else {
                share.setPercentage((shareQuantity - sharesToSell) * 100.0 / unit.getTotalShares());
                share.setShareValue((shareQuantity - sharesToSell) * unit.getPricePerShare());
                shareRepository.save(share);
                sharesToSell = 0;
            }
            if (sharesToSell == 0) break;
        }

        unit.setAvailableShares(unit.getAvailableShares() + numberOfShares);
        sharedUnitRepository.save(unit);

        return "Shares sold successfully. You received " + shareValue;
    }

    public List<UserSharesDTO> getSharesByUserId(Long userId) {
        List<Share> shares = shareRepository.findByUserId(userId);

        return shares.stream()
                .collect(Collectors.groupingBy(Share::getSharedUnit))
                .entrySet()
                .stream()
                .map(entry -> {
                    SharedUnit unit = entry.getKey();
                    List<Share> unitShares = entry.getValue();

                    UserSharesDTO userSharesDTO = new UserSharesDTO();
                    userSharesDTO.setUnitId(unit.getId());
                    userSharesDTO.setUnitName(unit.getUnitName());
                    userSharesDTO.setUnitDescription(unit.getDescription());
                    userSharesDTO.setPricePerShare(unit.getPricePerShare());
                    userSharesDTO.setTotalShares(unit.getTotalShares());
                    userSharesDTO.setAvailableShares(unit.getAvailableShares());

                    List<UserSharesDTO.ShareDTO> shareDTOs = unitShares.stream()
                            .map(share -> {
                                UserSharesDTO.ShareDTO shareDTO = new UserSharesDTO.ShareDTO();
                                shareDTO.setShareId(share.getId());
                                shareDTO.setShareValue(share.getShareValue());
                                shareDTO.setPercentage(share.getPercentage());
                                return shareDTO;
                            })
                            .collect(Collectors.toList());

                    userSharesDTO.setShares(shareDTOs);

                    return userSharesDTO;
                })
                .collect(Collectors.toList());
    }

}
