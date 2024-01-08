package com.baeldung.mapping.mapper;

import com.baeldung.mapping.entity.SimpleDestination;
import com.baeldung.mapping.entity.SimpleSource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleSourceDestinationMapper {
    SimpleDestination sourceToDestination(SimpleSource source);

    SimpleSource destinationToSource(SimpleDestination destination);
}
