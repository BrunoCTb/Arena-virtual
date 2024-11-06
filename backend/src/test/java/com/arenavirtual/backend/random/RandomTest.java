package com.arenavirtual.backend.random;

import java.util.ArrayList;
import java.util.List;

public class RandomTest {

	public static void main(String[] args) {
		List<List<String>> teams = new ArrayList<>();		
		List<String> group = new ArrayList<>();
		
		for (int i=0; i<16; i++) group.add("Time " + (i+1));
		
		// dividir times
		for (int i=0; i<16; i+=4) teams.add(group.subList(i, i+4));

		System.out.println("times dividos: " + teams);

		System.out.println(group);
			
//		System.out.println(teams);
//		System.out.println(group);
		
		// inverter cada array: simular mudança de posicao dos times
//		for (List<String> gp : teams) {
//			for (String t : gp) {
//				
//			}
//		}
		
		
		// pegar os 2 "times" de cada array gerando outro: simular proxima fase do camp
		
	}
	
}
