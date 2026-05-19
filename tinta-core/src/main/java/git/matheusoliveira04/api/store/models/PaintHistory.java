package git.matheusoliveira04.api.store.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "paint_history")
public class PaintHistory {

    @Id
    @Column(name = "ptht_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ptht_result")
    private String result;

    @Column(name = "us_id")
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "sf_id", nullable = false)
    private Surface surface;

    @ManyToOne
    @JoinColumn(name = "ptmt_id", nullable = false)
    private PaintingMethod paintingMethod;


}
