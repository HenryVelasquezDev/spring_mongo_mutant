package com.example.mutant.evaluation;

import java.util.regex.Pattern;

import com.example.mutant.exceptions.EstructuraNoValidaException;

public abstract class MutantEvaluation {
	
	/**
	 * Funcion principal que permite evaluar el arreglo de cadena de caracteres
	 * para determinar si la secuencia pertenece a un mutante o no
	 * @param dna
	 * @return Boolean true o false
	 */
	public static Boolean isMutant( String[] dna) {
		Boolean esMutante = false;
		int coincidencias = 0;
		
		Boolean esEstrucValida = validarEstructuraDNA( dna );		
		if ( !esEstrucValida ) {
			throw  new EstructuraNoValidaException("Secuencia de ADN no valida");
		}
		
		Boolean esCadenaValida = validarCadenaDNA( dna );		
		if ( !esCadenaValida ) {
			throw  new EstructuraNoValidaException("Secuencia de ADN no valida");
		}
		
		//Se revisan las secuencias que sean minimo de 4x4, el resto de secuencias se asumen no mutantes
		if (dna.length > 3) {
			Character[][] matrizADN = new Character [ dna.length ][ dna[0].length() ];
			
			//LLenamos una matriz con la informacion de secuencias ADN
			for ( int fila = 0; fila < dna.length ; fila++) {
				for ( int col = 0; col < dna.length ; col++) {
					matrizADN[fila][col] = dna[fila].toCharArray()[col];
				}
			}
			
			// Evaluamos las filas de la matriz
			String filaValor = "";
			for ( int filaIndice = 0; filaIndice < matrizADN.length ; filaIndice++) {
				filaValor = "";
				for ( int colIndice = 0; colIndice < matrizADN.length ; colIndice++) {
					filaValor = filaValor + matrizADN[filaIndice][colIndice];
				}
				if ( validarContenido(filaValor) ) {
					coincidencias++;
				}
			}
			
			// Evaluamos las columnas de la matriz
			String valorColumna="";
			for ( int colIndice = 0; colIndice < matrizADN.length; colIndice++) {
				valorColumna = "";
				for ( int filaIndice = 0; filaIndice < matrizADN.length; filaIndice++) {
					valorColumna = valorColumna + matrizADN[filaIndice][colIndice];
				}	
				if ( validarContenido(valorColumna) ) {
					coincidencias++;
				}
			}
			
			// Evaluamos las diagonales de la matriz
	        int altura = dna.length, anchura = dna.length;
	        String valorDiagonal = "";
	        int diagonalPrevia = 0;
	        for ( int diagonal = 1 - anchura; 
	            diagonal <= altura - 1; 
	            diagonal += 1 ) 
	        {
	        	
	            for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
	                vertical < altura && horizontal < anchura; 
	                vertical += 1, horizontal += 1 ) 
	            {
	            	
	            	if( diagonalPrevia != diagonal) {
	            		diagonalPrevia = diagonal;
	            		valorDiagonal = "";
	            	}
	            	
	            	valorDiagonal = valorDiagonal + matrizADN[vertical][horizontal];
	                
	            }
	            
	            if ( valorDiagonal.length() > 3 ) {
	            	if ( validarContenido(valorDiagonal) ) {
						coincidencias++;
					}
	            }
	        }
	        
	        esMutante = (coincidencias >= 2) ? true : false;
		
		}
		
		return esMutante;
	}
	
	/**
	 * 
	 * @param valor
	 * @return Boolean true o false
	 */
	private static Boolean validarContenido( String valor ) {
		Boolean esValido = false;
		
		if ( valor.contains("AAAA") || 
				valor.contains("TTTT") ||
				valor.contains("CCCC") ||
				valor.contains("GGGG") ) {
			
			esValido = true;
		}
		
		return esValido;
	}
	
	/**
	 * Funcion encargada de validar la estructura NxN del arreglo que ingresa por parametros
	 * @param dna
	 * @return Boolean true o false
	 */
	private static Boolean validarEstructuraDNA( String[] dna ) {
		Boolean esValido = true;
		int cont = 0;
		String Fila = "";
		int numFilas = dna.length;
		
		while (cont < numFilas) {
			Fila = dna[cont];
			if (Fila.length() != numFilas ) {
				esValido = false;
			}
			cont++;
		}
		return esValido;
	}
	
	/**
	 * Funcion que valida que cada fila contenga solo caracteres A,T,C,G
	 * @param dna
	 * @return Boolean true o false
	 */
	private static Boolean validarCadenaDNA( String[] dna ) {
		Boolean esValido = true;
		int cont = 0;
		String Fila = "";
		int numFilas = dna.length;
		
		while (cont < numFilas) {
			Fila = dna[cont];
			esValido = Pattern.matches("[A|T|C|G]*", Fila);
			if (!esValido) {
				return esValido;
			}
			cont++;
		}
		return esValido;
	}

}
