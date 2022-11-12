package org.example.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @Lob
    private byte[] data;

    public Video(String title, String description, byte[] data) {
        this.title = title;
        this.description = description;
        this.data = data;
    }

}
