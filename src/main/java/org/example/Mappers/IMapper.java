package org.example.Mappers;

import java.util.List;

public interface IMapper<T, U> {

    U modelToDto(T model);

    T dtoToModel(U dto);

    List<U> modelsToDtos(Iterable<T> models);

    List<T> dtosToModels(List<U> dtos);

}