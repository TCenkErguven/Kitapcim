package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.SaveYayinEviRequestDto;
import com.bilgeadam.repository.entity.YayinEvi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IYayinEviMapper {
    IYayinEviMapper INSTANCE = Mappers.getMapper(IYayinEviMapper.class);
    YayinEvi toYayinEvi(final SaveYayinEviRequestDto dto); // final unutma!!
}
