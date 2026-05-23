package git.matheusoliveira04.api.store.controllers;

import git.matheusoliveira04.api.store.models.PaintHistory;
import git.matheusoliveira04.api.store.models.PaintingMethod;
import git.matheusoliveira04.api.store.models.Surface;
import git.matheusoliveira04.api.store.models.dtos.requests.PaintHistoryRequest;
import git.matheusoliveira04.api.store.models.dtos.responses.PaintHistoryResponse;
import git.matheusoliveira04.api.store.models.dtos.responses.UserResponseEvent;
import git.matheusoliveira04.api.store.producer.UserRequestProducer;
import git.matheusoliveira04.api.store.services.PaintHistoryService;
import git.matheusoliveira04.api.store.services.PaintingMethodService;
import git.matheusoliveira04.api.store.services.SurfaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/paint-history")
public class PaintHistoryController {

    @Autowired
    private PaintHistoryService paintHistoryService;

    @Autowired
    private SurfaceService surfaceService;

    @Autowired
    private PaintingMethodService paintingMethodService;

    @Autowired
    private UserRequestProducer userRequestProducer;

    @PostMapping
    public ResponseEntity<PaintHistoryResponse> insert(

            @RequestBody @Valid PaintHistoryRequest request,

            UriComponentsBuilder uriBuilder
    ) {

        String email = request.email();
        UserResponseEvent user = userRequestProducer.getUserByEmail(email);


        Surface surface = surfaceService.findById(request.surfaceId());
        PaintingMethod paintingMethod = paintingMethodService.findById(request.paintingMethodId());

        PaintHistory entity = new PaintHistory(null, request.result(), user.id(), surface, paintingMethod);

        PaintHistory saved = paintHistoryService.insert(entity);

        PaintHistoryResponse response = new PaintHistoryResponse(
                        saved.getId(),
                        saved.getResult(),
                        user.id(),
                        user.name(),
                        saved.getSurface().getDescription(),
                        saved.getPaintingMethod().getDescription()
                );

        return ResponseEntity.created(
                uriBuilder
                        .path("/paint-history/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PaintHistory>> get() {
        return ResponseEntity.ok(paintHistoryService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaintHistory> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(paintHistoryService.findById(id));
    }
}
