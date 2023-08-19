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

}
