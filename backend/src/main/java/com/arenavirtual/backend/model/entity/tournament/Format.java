package com.arenavirtual.backend.model.entity.tournament;

import jakarta.persistence.*;
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

    @Column(name = "name")
    private String name; // mata-mata, fase de grupos

    @Column(name = "teamsDivision")
    private Integer teamsDivision; // ex: mata-mata: 2, pontos corridos: 1

    @Column(name = "teamsForNextStage")
    private Integer teamsForNextStage; // quantos times passam/sao "campeoes" no final de tudo, ex: 2 por grupo, 1 no final de todas as fase do mata-mata
    
//    private String formatDescription; // apenas para descrever como funciona

	public Format(String name, Integer teamsDivision, Integer teamsForNextStage, String formatDescription) {
		this.name = name;
		this.teamsDivision = teamsDivision;
		this.teamsForNextStage = teamsForNextStage;
//		this.formatDescription = formatDescription;
	}

    
    
}
