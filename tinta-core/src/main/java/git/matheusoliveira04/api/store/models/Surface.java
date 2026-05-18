package git.matheusoliveira04.api.store.models;

import git.matheusoliveira04.api.store.models.dtos.requests.SurfaceRequest;
import git.matheusoliveira04.api.store.models.dtos.responses.SurfaceResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "surface")
public class Surface {

    @Id
    @Column(name = "sf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "sf_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "sftp_id", nullable = false)
    private SurfaceType surfaceType;

    public Surface(SurfaceRequest request, SurfaceType surfaceType) {
        this(null, request.description(), surfaceType);
    }

    public SurfaceResponse toDto() {
        return new SurfaceResponse(id, description, surfaceType.getId(), surfaceType.getType());
    }

}
