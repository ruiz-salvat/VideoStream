package org.example.Mappers;

import lombok.NoArgsConstructor;
import org.example.DTOs.PlanDTO;
import org.example.Entities.Plan;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class PlanMapper implements IMapper<Plan, PlanDTO>{
    @Override
    public PlanDTO modelToDto(Plan model) {
        return new PlanDTO(model.getId());
    }

    @Override
    public Plan dtoToModel(PlanDTO dto) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    @Override
    public List<PlanDTO> modelsToDtos(Iterable<Plan> models) {
        List<PlanDTO> planDtos = new ArrayList<>();
        for (Plan plan : models) {
            planDtos.add(modelToDto(plan));
        }
        return planDtos;
    }

    @Override
    public List<Plan> dtosToModels(List<PlanDTO> dtos) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }
}
