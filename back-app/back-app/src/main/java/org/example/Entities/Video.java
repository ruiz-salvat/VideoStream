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

    private boolean isInfoVideo;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Video(String slug, String title, String synopsis, String description, String videoFilePath, String imageFilePath, boolean isInfoVideo, Category category, Plan plan) {
        this.slug = slug;
        this.title = title;
        this.synopsis = synopsis;
        this.description = description;
        this.videoFilePath = videoFilePath;
        this.imageFilePath = imageFilePath;
        this.isInfoVideo = isInfoVideo;
        this.category = category;
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return isInfoVideo() == video.isInfoVideo() && Objects.equals(getId(), video.getId()) && Objects.equals(getSlug(), video.getSlug()) && Objects.equals(getTitle(), video.getTitle()) && Objects.equals(getSynopsis(), video.getSynopsis()) && Objects.equals(getDescription(), video.getDescription()) && Objects.equals(getVideoFilePath(), video.getVideoFilePath()) && Objects.equals(getImageFilePath(), video.getImageFilePath()) && Objects.equals(getCategory(), video.getCategory()) && Objects.equals(getPlan(), video.getPlan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSlug(), getTitle(), getSynopsis(), getDescription(), getVideoFilePath(), getImageFilePath(), isInfoVideo(), getCategory(), getPlan());
    }
}
