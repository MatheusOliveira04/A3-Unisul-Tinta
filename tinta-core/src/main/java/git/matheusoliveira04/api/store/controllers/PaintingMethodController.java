package git.matheusoliveira04.api.store.controllers;


import git.matheusoliveira04.api.store.models.PaintingMethod;
import git.matheusoliveira04.api.store.services.PaintingMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/painting-method")
public class PaintingMethodController {

    @Autowired
    private PaintingMethodService paintingMethodService;

    @PostMapping
    public ResponseEntity<PaintingMethod> insert(
            @RequestBody @Valid PaintingMethod paintingMethod,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        PaintingMethod entity = paintingMethodService.insert(paintingMethod);

        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/painting-method/{id}")
                                .buildAndExpand(entity.getId())
                                .toUri()
                )
                .body(entity);
    }

    @GetMapping
    public ResponseEntity<List<PaintingMethod>> findALl() {
        return ResponseEntity.ok(paintingMethodService.findALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingMethod> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(paintingMethodService.findById(id));
    }
}
