package git.matheusoliveira04.api.store.controllers;

import git.matheusoliveira04.api.store.models.SurfaceType;
import git.matheusoliveira04.api.store.services.SurfaceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/surface-type")
public class SurfaceTypeController {

    @Autowired
    private SurfaceTypeService surfaceTypeService;

    @PostMapping
    public ResponseEntity<SurfaceType> insert(
            @RequestBody @Valid SurfaceType surfaceType,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        SurfaceType entity = surfaceTypeService.insert(surfaceType);

        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/surface-type/{id}")
                                .buildAndExpand(entity.getId())
                                .toUri()
                )
                .body(entity);
    }

    @GetMapping
    public ResponseEntity<List<SurfaceType>> findAll() {
        return ResponseEntity.ok(surfaceTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurfaceType> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(surfaceTypeService.findById(id));
    }
}
