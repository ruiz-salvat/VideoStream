package org.example.Entities;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    private String synopsis;

    private String description;

    private String videoFilePath;

    private String imageFilePath;

    public Video(String slug, String title, String description, String videoFilePath, String imageFilePath) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.videoFilePath = videoFilePath;
        this.imageFilePath = imageFilePath;
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
