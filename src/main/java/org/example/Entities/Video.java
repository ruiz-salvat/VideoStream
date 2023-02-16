package org.example.Entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    private String slug;

    private String title;

    @Lob
    @Type(type = "text")
    private String synopsis;

    @Lob
    @Type(type = "text")
    private String description;

    private String videoFilePath;

    private String imageFilePath;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Video(String slug, String title, String synopsis, String description, String videoFilePath, String imageFilePath, Category category) {
        this.slug = slug;
        this.title = title;
        this.synopsis = synopsis;
        this.description = description;
        this.videoFilePath = videoFilePath;
        this.imageFilePath = imageFilePath;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Video video = (Video) o;
        return id != null && Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
