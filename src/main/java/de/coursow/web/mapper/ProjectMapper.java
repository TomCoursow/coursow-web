package de.coursow.web.mapper;

import de.coursow.web.entity.ProjectEntity;
import de.coursow.web.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TechnologyMapper.class)
public interface ProjectMapper {

    Project toVO(ProjectEntity entity);

    ProjectEntity toEntity(Project vo);
}
