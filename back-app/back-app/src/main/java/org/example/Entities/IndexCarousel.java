package org.example.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IndexCarousel {

    @Id
    @GeneratedValue
    private Long id;

    private String imageFilePath;

    private String imageAlt;

    private String link;

    @ManyToOne
    @JoinColumn(name = "index_layout_id")
    private IndexLayout indexLayout;

    public IndexCarousel(String imageFilePath, String imageAlt, String link, IndexLayout indexLayout) {
        this.imageFilePath = imageFilePath;
        this.imageAlt = imageAlt;
        this.link = link;
        this.indexLayout = indexLayout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexCarousel that = (IndexCarousel) o;
        return Objects.equals(id, that.id) && Objects.equals(imageFilePath, that.imageFilePath) && Objects.equals(imageAlt, that.imageAlt) && Objects.equals(link, that.link) && Objects.equals(indexLayout, that.indexLayout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageFilePath, imageAlt, link, indexLayout);
    }
}
