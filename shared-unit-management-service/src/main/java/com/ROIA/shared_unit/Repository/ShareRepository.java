package com.ROIA.shared_unit.Repository;

import com.ROIA.shared_unit.Model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShareRepository extends JpaRepository<Share, Long> {

    List<Share> findByUserId(Long userId);

    @Query("SELECT s FROM Share s WHERE s.userId = :userId AND s.sharedUnit.id = :unitId")
    List<Share> findByUserIdAndSharedUnitId(@Param("userId") Long userId, @Param("unitId") Long unitId);
}
