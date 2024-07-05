package com.ROIA.shared_unit.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserSharesDTO {
    private Long unitId;
    private String unitName;
    private String unitDescription;
    private double pricePerShare;
    private int totalShares;
    private int availableShares;
    private List<ShareDTO> shares;

    @Data
    public static class ShareDTO {
        private Long shareId;
        private double shareValue;
        private double percentage;
    }

}
