package org.example.Mappers;

import lombok.NoArgsConstructor;
import org.example.DTOs.VideoDTO;
import org.example.Entities.Video;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@NoArgsConstructor
public class VideoMapper implements IMapper<Video, VideoDTO> {

    @Override
    public VideoDTO modelToDto(Video model) {
        return new VideoDTO(model.getSlug(), model.getTitle(), model.getSynopsis(), model.getDescription());
    }

    @Override
    public Video dtoToModel(VideoDTO dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<VideoDTO> modelsToDtos(Iterable<Video> models) {
        Iterator<Video> it = models.iterator();
        List<VideoDTO> videoDtos = new ArrayList<>();
        while (it.hasNext()) {
            videoDtos.add(modelToDto(it.next()));
        }
        return videoDtos;
    }

    @Override
    public List<Video> dtosToModels(List<VideoDTO> dtos) {
        throw new RuntimeException("Not implemented yet");
    }
}
