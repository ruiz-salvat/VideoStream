package org.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class IndexLayoutDTO {

    private long id;

    private String text1;

    private String text2;

    private String text3;

    private String text4;

    private List<IndexCarouselDTO> indexCarousels;

}
