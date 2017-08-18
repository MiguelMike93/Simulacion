package logic;

import java.util.Random;

public class Probabilidad {
	
	 /**
	  * 1 -> Normal
	  * 2 -> Binomial
	  * 3 -> Otra
	  */
	private int distribucion;
	private int levels;
	private Random r;
	private int result;
	
	/**
	 * Para hacer uso de la clase se crea un objeto Probabilidad y se ingresa en el contructor el tipo de distribución
	 * Usar metodo "generarAleatorio()" para obtener un nuevo aleatorio
	 * @param distribucion 1 -> Normal; 2 -> Binomial; 3 -> Binomial Inversa 
	 * 
	 */
	
	public Probabilidad(int distribucion) {
		this.distribucion = distribucion;
		r = new Random();
	}
	
	/**
	 * Generador de numeros enteros pseudoaleatorios basado en la Maquina de Galton
	 * @param menor Numero menor del intervalo (Inclusivo)
	 * @param mayor Numero mayor del intervalo (Inclusivo)
	 * @return Entero pseudoaleatorio de acuerdo a la distribución ingresada en el constructor y al intervalo ingresado como parametro
	 */
	public int generarAleatorio(int menor, int mayor) {
		int random = 0;
		switch (distribucion) {
		case 1:
			random = distribucionNormal(menor, mayor);
			break;
		case 2:
			random = distribucionBinomial(menor, mayor);
			break;
		case 3:
			random = distribucionOtra(menor, mayor);
			break;
		default:
			break;
		}return random;
	}
	
	/*
	 * Metodo encargado de generar un numero aleatorio respecto a la distribucion normal.
	 */
	private int distribucionNormal(int menor, int mayor) {
		setLevels((mayor - menor) + 1);
		result = (levels / 2) + menor - 1;
		if(r.nextBoolean()) 
			result ++;
		for (int i = 0; i < levels; i++) {
			if(r.nextBoolean()) {
				if(result + 1 > mayor) 
					result --;
				else
					result ++;
			}else {
				if(result - 1 < menor)
					result ++;
				else
					result --;
			}		
		}return result;
	}
	
	/*
	 * Metodo encargado de generar un numero aleatorio respecto a la distribucion binomial.
	 */
	private int distribucionBinomial(int menor, int mayor) {
		setLevels((mayor - menor) + 1);
		result = (levels / 3) + menor - 1;
		result = (result * 2) + (result / 2);
		if(r.nextBoolean()) 
			result ++;
		for (int i = 0; i < levels; i++) {
			if(r.nextBoolean()) {
				if(result + 1 > mayor) 
					result --;
				else
					result ++;
			}else {
				if(result - 1 < menor)
					result ++;
				else
					result --;
			}		
		}return result;
	}
	
	/*
	 * Metodo encargado de generar un numero aleatorio respecto a la distribucion que falta por definir.
	 */
	private int distribucionOtra(int menor, int mayor) {
		setLevels((mayor - menor) + 1);
		result = (levels / 3) + menor - 1;
		result = result - (result / 2);
		if(r.nextBoolean()) 
			result ++;
		for (int i = 0; i < levels; i++) {
			if(r.nextBoolean()) {
				if(result + 1 > mayor) 
					result --;
				else
					result ++;
			}else {
				if(result - 1 < menor)
					result ++;
				else
					result --;
			}		
		}return result;
	}
	/**
	 * Calculo aleatorio de Dias en concepcion segun el parametro	
	 * @param fetosEnGestacion a partir de este valor se genera el valor aleatorio de los dias en concepcion de acuerdo a la informacion del documento
	 * @return Dias en concepcion -> 98% de probabilidad de que sea 31 y para el 2% puede ser de 28 a 35 dependiendo del numero de fetos en gestacion
	 * entre mas fetos mayo seran los dias
	 */
	public  int diasConcepcion(int fetosEnGestacion) {
		int result = 0;
		if(r.nextInt(100)>98) {
			result =  31;
		}else {
			if(fetosEnGestacion > 6) {
				result = distribucionBinomial(31, 35);
			}else {
				result = distribucionNormal(28, 30);
			}
		}return result;		
	}

	
	private void test(int iteraciones, int menor, int mayor) {
		setLevels((mayor - menor) + 1);
		int[]numeros = new int[levels];
		int[]contador = new int[levels];
		int r;
		//inicializa el vector de numeros para visualizar
		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = menor + i;
		}
		
		//generador de aleatorios segun la distribucion planteada en el constructor
		for (int i = 0; i < iteraciones; i++) {
			r = generarAleatorio(menor, mayor);
			for (int j = 0; j < contador.length; j++) {
				if(r == numeros[j]) {
					contador[j] = contador[j] + 1; 
				}
			}
		}
		
		//para imprimir en pantalla
		for (int i = 0; i < contador.length; i++) {
			System.out.println(numeros[i] + "-->"+ contador[i]);
		}

	}
	
	public int getDistribucion() {
		return distribucion;
	}
	public void setDistribucion(int distribucion) {
		this.distribucion = distribucion;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	public static void main(String[] args) {
		Probabilidad p = new Probabilidad(3);
		p.test(100000,1,100);
	}
}
