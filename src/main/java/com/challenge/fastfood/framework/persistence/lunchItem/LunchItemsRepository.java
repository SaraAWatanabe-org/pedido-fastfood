package com.challenge.fastfood.framework.persistence.lunchItem;

import com.challenge.fastfood.entities.LunchItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LunchItemsRepository extends JpaRepository<LunchItemEntity, Long> {

    List<LunchItemEntity> findByTypeAndStatusTrue(LunchItemType lunchItemType);
    List<LunchItemEntity> findByStatusTrue();
    LunchItemEntity findByName(String name);
    LunchItemEntity findByIdAndStatusTrue(Long id);
}
