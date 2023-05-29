package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.PlanDTO;
import org.example.Services.IPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("private-plan")
@CrossOrigin
@AllArgsConstructor
public class PrivatePlanController {

    private IPlanService planService;

    @GetMapping("all")
    public ResponseEntity<List<PlanDTO>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }

}
