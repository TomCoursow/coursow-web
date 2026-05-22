package de.coursow.web.mapper;

import de.coursow.web.entity.TechnologyEntity;
import de.coursow.web.model.Technology;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnologyMapper {

    Technology toVO(TechnologyEntity entity);

    TechnologyEntity toEntity(Technology vo);
}
