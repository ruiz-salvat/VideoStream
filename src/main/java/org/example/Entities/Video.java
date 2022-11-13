package org.example.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String filePath;

    public Video(String title, String description, String filePath) {
        this.title = title;
        this.description = description;
        this.filePath = filePath;
    }

}
