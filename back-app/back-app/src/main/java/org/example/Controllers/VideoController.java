package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.VideoDTO;
import org.example.Entities.Plan;
import org.example.Repositories.IPlanRepository;
import org.example.Services.IVideoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("video")
@CrossOrigin
@AllArgsConstructor
public class VideoController {

    private IVideoService videoService;
    private IPlanRepository planRepository;

    @GetMapping(value = "{slug}", produces = "video/mp4")
    public Mono<byte[]> getVideo(@PathVariable String slug, @RequestHeader("Range") String range) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // TODO: encapsulate permissions
        if (authentication != null && authentication.isAuthenticated()) {
            for (org.springframework.security.core.GrantedAuthority authority : authentication.getAuthorities()) {

                SimpleGrantedAuthority basicAuthority = new SimpleGrantedAuthority("basic_user");
                SimpleGrantedAuthority grantedAuthority = (SimpleGrantedAuthority) authority;

                Plan freePlan = planRepository.findByName("free");
//                Plan loginPlan = planRepository.findByName("login");

                if (basicAuthority.equals(grantedAuthority)) {

//                    VideoDTO videoDto = videoService.getVideoDetails(slug);
//                    Optional<Plan> videoPlanOptional = planRepository.findById(videoDto.getPlan());
//                    if (videoPlanOptional.isPresent()) {
//                        Plan videoPlan = videoPlanOptional.get();
//                        if (videoPlan.equals(loginPlan)) {
                            return videoService.getVideo(slug);
//                        } else {
//                            return videoService.getVideo("info"); // TODO: merge this with the other one
//                        }
//                    }

                } else {

                    VideoDTO videoDto = videoService.getVideoDetails(slug);
                    Optional<Plan> videoPlanOptional = planRepository.findById(videoDto.getPlan());
                    if (videoPlanOptional.isPresent()) {
                        Plan videoPlan = videoPlanOptional.get();
                        if (videoPlan.equals(freePlan)) {
                            return videoService.getVideo(slug);
                        } else {
                            return videoService.getVideo("info"); // TODO: try and catch
                        }
                    }

                }
            }
        }

        throw new RuntimeException("Security Context Not Loaded");
    }

    @GetMapping(value = "{slug}/{percentage}", produces = "video/mp4")
    public Mono<byte[]> getVideoPart(@PathVariable String slug, @PathVariable String percentage, @RequestHeader("Range") String range) {

        double percentageDouble = Double.parseDouble(percentage);
        if (percentageDouble < 0 || percentageDouble > 100)
            throw new RuntimeException("Percentage out of range");

        percentageDouble = percentageDouble / 100; // provided number is from 0 to 100

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // TODO: encapsulate permissions
        if (authentication != null && authentication.isAuthenticated()) {
            for (org.springframework.security.core.GrantedAuthority authority : authentication.getAuthorities()) {

                SimpleGrantedAuthority basicAuthority = new SimpleGrantedAuthority("basic_user");
                SimpleGrantedAuthority grantedAuthority = (SimpleGrantedAuthority) authority;

                Plan freePlan = planRepository.findByName("free");

                if (basicAuthority.equals(grantedAuthority)) {

                    return videoService.getVideoPart(slug, percentageDouble);

                } else {

                    VideoDTO videoDto = videoService.getVideoDetails(slug);
                    Optional<Plan> videoPlanOptional = planRepository.findById(videoDto.getPlan());
                    if (videoPlanOptional.isPresent()) {
                        Plan videoPlan = videoPlanOptional.get();
                        if (videoPlan.equals(freePlan)) {
                            return videoService.getVideoPart(slug, percentageDouble);
                        } else {
                            return videoService.getVideo("info");
                        }
                    }

                }
            }
        }

        throw new RuntimeException("Security Context Not Loaded");
    }

    @GetMapping(value = "details/{slug}")
    public ResponseEntity<VideoDTO> getVideoDetails(@PathVariable String slug) {
        return ResponseEntity.ok(videoService.getVideoDetails(slug));
    }

    @GetMapping(value = "image/{slug}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable String slug) throws IOException {
        return videoService.getVideoImage(slug);
    }

    @GetMapping("all")
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

}
