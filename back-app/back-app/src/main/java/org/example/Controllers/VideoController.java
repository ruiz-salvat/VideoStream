package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.PlanDTO;
import org.example.DTOs.VideoDTO;
import org.example.Entities.Plan;
import org.example.Repositories.IPlanRepository;
import org.example.Repositories.IRoleRepository;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (org.springframework.security.core.GrantedAuthority authority : authentication.getAuthorities()) {

                SimpleGrantedAuthority basicAuthority = new SimpleGrantedAuthority("basic_user");
                SimpleGrantedAuthority grantedAuthority = (SimpleGrantedAuthority) authority;

                Plan freePlan = planRepository.findByName("free");
//                Plan loginPlan = planRepository.findByName("login");

                System.out.println("\n*******\n");
                System.out.println(grantedAuthority);
                System.out.println("\n*******\n");
                System.out.println(authority);
                System.out.println("\n*******\n");

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
        } else {
            throw new RuntimeException("Security Context Not Loaded");
        }

        return videoService.getVideo(slug);
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
