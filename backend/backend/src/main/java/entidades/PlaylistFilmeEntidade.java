package entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "playlist_filme")
public class PlaylistFilmeEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
