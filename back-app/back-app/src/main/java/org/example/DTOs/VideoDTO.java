package org.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class VideoDTO {

    private String slug;

    private String title;

    private String synopsis;

    private String description;

    private Long category;

    private Long plan;

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
