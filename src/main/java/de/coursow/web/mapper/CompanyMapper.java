package de.coursow.web.mapper;

import de.coursow.web.entity.CompanyEntity;
import de.coursow.web.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toVO(CompanyEntity entity);

    CompanyEntity toEntity(Company vo);
}
