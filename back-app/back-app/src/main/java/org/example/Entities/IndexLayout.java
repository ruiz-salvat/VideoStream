package org.example.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IndexLayout {

    @Id
    @GeneratedValue
    private Long id;

    private int priority;

    private String description;

    @Lob
    @Type(type = "text")
    private String text1;

    @Lob
    @Type(type = "text")
    private String text2;

    @Lob
    @Type(type = "text")
    private String text3;

    @Lob
    @Type(type = "text")
    private String text4;

    public IndexLayout(int priority, String description, String text1, String text2, String text3, String text4) {
        this.priority = priority;
        this.description = description;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexLayout that = (IndexLayout) o;
        return getPriority() == that.getPriority() && Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getText1(), that.getText1()) && Objects.equals(getText2(), that.getText2()) && Objects.equals(getText3(), that.getText3()) && Objects.equals(getText4(), that.getText4());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPriority(), getDescription(), getText1(), getText2(), getText3(), getText4());
    }
}
