package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.IndexLayoutDTO;
import org.example.Services.IIndexLayoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("index-layout")
@CrossOrigin
@AllArgsConstructor
public class IndexLayoutController {

    private IIndexLayoutService indexLayoutService;

    @GetMapping()
    public ResponseEntity<IndexLayoutDTO> getIndexLayout() {
        return ResponseEntity.ok(indexLayoutService.getIndexLayout());
    }

}
