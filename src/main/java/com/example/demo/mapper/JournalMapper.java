package com.example.demo.mapper;

import com.example.demo.dao.model.Journal;
import com.example.demo.dto.JournalDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JournalMapper {

    JournalMapper INSTANCE = Mappers.getMapper(JournalMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "entityName", target = "entityName")
    @Mapping(source = "entityId", target = "entityId")
    @Mapping(source = "operationType", target = "operationType")
    @Mapping(target = "timestamp", expression = "java(System.currentTimeMillis())")
    Journal journalDtoToJournal(JournalDto journalDto);

    @Mapping(source = "entityName", target = "entityName")
    @Mapping(source = "entityId", target = "entityId")
    @Mapping(source = "operationType", target = "operationType")
    @Mapping(target = "timestamp", expression = "java(System.currentTimeMillis())")
    JournalDto journalToJournalDto(Journal journal);

    List<JournalDto> journalsToJournalDtos(List<Journal> journals);

    List<Journal> journalDtosToJournals(List<JournalDto> journalDtos);

}
