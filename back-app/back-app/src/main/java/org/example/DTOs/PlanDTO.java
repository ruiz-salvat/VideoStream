package org.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class PlanDTO {

    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanDTO planDTO = (PlanDTO) o;
        return Objects.equals(getId(), planDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
