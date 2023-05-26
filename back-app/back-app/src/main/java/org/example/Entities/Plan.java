package org.example.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Plan {

    @Id
    @GeneratedValue
    private Long id;

    private Integer price24;

    private Integer price48;

    private Integer price24premium;

    private Integer price48premium;

    public Plan(Integer price24, Integer price48, Integer price24premium, Integer price48premium) {
        this.price24 = price24;
        this.price48 = price48;
        this.price24premium = price24premium;
        this.price48premium = price48premium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(getId(), plan.getId()) && Objects.equals(getPrice24(), plan.getPrice24()) && Objects.equals(getPrice48(), plan.getPrice48()) && Objects.equals(getPrice24premium(), plan.getPrice24premium()) && Objects.equals(getPrice48premium(), plan.getPrice48premium());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice24(), getPrice48(), getPrice24premium(), getPrice48premium());
    }
}
