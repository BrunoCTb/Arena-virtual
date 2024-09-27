package com.arenavirtual.backend.model.entity.tournament;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Format {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name; // mata-mata, fase de grupos
    
    private int minTeamsQuantity; // quantidade minima de times
    private int maxTeamsQuantity; // quantidade maxima (numero par de times para mata-mata)
    private int matchesByRound; // partida unica ou partidas de volta
    private int numberOfDivisions; // ex: 4` -> 12/4 = 3 times em 4` divisões diferentes
    
    private int teamsByDivision; // mesmo que acima, mas sera o 3` que sera quantos times por divisao (preferencia por numero par)
    private int advanceToNext; // times para a proxima fase ou entao se for o ultimo formato sera o campeao
    
    private String formatDescription; // apenas para descrever como funciona

	public Format(String name, int minTeamsQuantity, int maxTeamsQuantity, int matchesByRound, int teamsDivision,
			int teamsByDivision, int advanceToNext, String formatDescription) {
		this.name = name;
		this.minTeamsQuantity = minTeamsQuantity;
		this.maxTeamsQuantity = maxTeamsQuantity;
		this.matchesByRound = matchesByRound;
		this.numberOfDivisions = teamsDivision;
		this.teamsByDivision = teamsByDivision;
		this.advanceToNext = advanceToNext;
		this.formatDescription = formatDescription;
	}

    
    
}
