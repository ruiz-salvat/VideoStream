package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOs.PlanDTO;
import org.example.Entities.Plan;
import org.example.Mappers.IMapper;
import org.example.Repositories.IPlanRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanService implements IPlanService {

    private IPlanRepository planRepository;

    private IMapper<Plan, PlanDTO> planMapper;

    @Override
    public PlanDTO getPlan(Long id) {
        Optional<Plan> planOptional = planRepository.findById(id);
        if (planOptional.isPresent()) {
            Plan plan = planOptional.get();
            return planMapper.modelToDto(plan);
        } else {
            throw new RuntimeException("Error retrieving plan");
        }
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        return planMapper.modelsToDtos(planRepository.findAll());
    }
}
