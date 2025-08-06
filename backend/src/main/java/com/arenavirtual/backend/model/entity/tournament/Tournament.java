package com.arenavirtual.backend.model.entity.tournament;

import com.arenavirtual.backend.model.entity.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tournament")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title; // campeonato abc 3a edicao...
    private String modality; // futebol, jogo especifico...
    @Column(name = "onlineMode")
    private Boolean onlineMode; // outros usuarios poderao entrar
    @Column(name = "minTeams")
    private Integer minTeams;
    @Column(name = "maxTeams")
    private Integer maxTeams;
    @Column(name = "imageRepresentationUrl")
    private String imageRepresentationUrl;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

//    @Column(name = "status")
//    private TournamentProgressStatus status; // nao iniciado, em andamento e finalizado

    private StyleFormat format; // formato suiço, pontos corridos...

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", title=" + title + ", modality=" + modality + ", onlineMode=" + onlineMode
				+ ", minTeams=" + minTeams + ", maxTeams=" + maxTeams + ", imageRepresentationUrl="
				+ imageRepresentationUrl + ", format=" + format + "]";
	}

    
}
