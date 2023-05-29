package org.example.Services;

import org.example.DTOs.PlanDTO;
import java.util.List;

public interface IPlanService {

    PlanDTO getPlan(Long id);

    List<PlanDTO> getAllPlans();

}
