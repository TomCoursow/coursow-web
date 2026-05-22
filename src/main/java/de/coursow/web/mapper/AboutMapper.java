package de.coursow.web.mapper;

import de.coursow.web.entity.AboutEntity;
import de.coursow.web.model.About;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboutMapper {

    About toVO(AboutEntity entity);

    AboutEntity toEntity(About vo);
}
