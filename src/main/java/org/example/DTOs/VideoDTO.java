package org.example.DTOs;

import lombok.AllArgsConstructor;
import java.util.Objects;

@AllArgsConstructor
public class VideoDTO {

    private String slug;

    private String title;

    private String synopsis;

    private String description;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoDTO videoDTO = (VideoDTO) o;
        return Objects.equals(slug, videoDTO.slug) && Objects.equals(title, videoDTO.title) && Objects.equals(synopsis, videoDTO.synopsis) && Objects.equals(description, videoDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug, title, synopsis, description);
    }
}
