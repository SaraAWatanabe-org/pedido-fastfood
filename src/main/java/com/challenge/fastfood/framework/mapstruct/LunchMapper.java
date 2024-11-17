package com.challenge.fastfood.framework.mapstruct;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.LunchStatus;
import com.challenge.fastfood.framework.persistence.lunch.LunchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LunchMapper {

    @Mapping(target = "lunchItems", source = "lunchItems")
    @Mapping(target = "priceTotal", source = "priceTotal")
    @Mapping(source = "status", target = "status")
    Lunch lunchEntityToLunch(LunchEntity lunchEntity);

    @Mapping(target = "lunchItems", source = "lunchItems")
    @Mapping(target = "priceTotal", source = "priceTotal")
    @Mapping(source = "status", target = "status")
    LunchEntity lunchToLunchEntity(Lunch lunch);


    List<Lunch> lunchsEntityToLunchs(List<LunchEntity> lunchEntity);

    default LunchStatus mapStatus(String status) {
        return LunchStatus.fromDescricao(status);
    }

    default String mapStatus(LunchStatus status) {
        return status.getDescricao();
    }
}
