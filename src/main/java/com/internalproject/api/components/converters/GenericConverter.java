package com.internalproject.api.components.converters;


import com.internalproject.api.model.entity.BaseEntity;
import com.internalproject.api.domain.dto.BaseDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter <D extends BaseDto, E extends BaseEntity> {

    D toDto(E entity);

    default List<D> toDto(final Collection<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
