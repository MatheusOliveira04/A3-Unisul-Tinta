package git.matheusoliveira04.api.store.controllers;

import git.matheusoliveira04.api.store.models.Surface;
import git.matheusoliveira04.api.store.models.SurfaceType;
import git.matheusoliveira04.api.store.models.dtos.requests.SurfaceRequest;
import git.matheusoliveira04.api.store.models.dtos.responses.SurfaceResponse;
import git.matheusoliveira04.api.store.services.SurfaceService;
import git.matheusoliveira04.api.store.services.SurfaceTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/surface")
public class SurfaceController {

    @Autowired
    private SurfaceService surfaceService;

    @Autowired
    private SurfaceTypeService surfaceTypeService;

    @PostMapping
    public ResponseEntity<SurfaceResponse> insert(
            @RequestBody @Valid SurfaceRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        SurfaceType type = surfaceTypeService.findById(request.surfaceTypeId());
        Surface entity = surfaceService.insert(new Surface(request, type));

        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/surface/{id}")
                                .buildAndExpand(entity.getId())
                                .toUri()
                )
                .body(entity.toDto());
    }

    @GetMapping
    public ResponseEntity<List<SurfaceResponse>> get() {
        return ResponseEntity.ok(surfaceService.findAll().stream().map(Surface::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurfaceResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(surfaceService.findById(id).toDto());
    }
}
