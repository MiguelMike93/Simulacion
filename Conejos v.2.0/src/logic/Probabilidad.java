package logic;

import java.util.Random;

public class Probabilidad {
	 /**
	  * 1 -> Normal
	  * 2 -> Binomial
	  * 3 -> Otra
	  */
	private int distribucion;
	private Random generator;
	
	public Probabilidad(int distribucion) {
		this.distribucion = distribucion;
		generator =  new Random();
	}
	
	/*
	 * Metodo encargado de generar aleatorio respecto a la distribucion parametrizada en el constructor.
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
		int delay = -1;
		double val = -1;
		boolean flag = false;
		while (flag == false) {
			val = (generator.nextGaussian()*(mayor - menor + 1) + menor) + ((mayor + menor)/2);
			delay = (int) Math.round(val);
			System.out.println(delay);
			if((delay >= menor) && (delay <= mayor)) {
				flag = true;
			}	
		}		
		return delay;
		
	}
	
	/*
	 * Metodo encargado de generar un numero aleatorio respecto a la distribucion binomial.
	 */
	private int distribucionBinomial(int menor, int mayor) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * Metodo encargado de generar un numero aleatorio respecto a la distribucion que falta por definir.
	 */
	private int distribucionOtra(int menor, int mayor) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getDistribucion() {
		return distribucion;
	}
	public void setDistribucion(int distribucion) {
		this.distribucion = distribucion;
	}
	
	public static void main(String[] args) {
		Probabilidad p = new Probabilidad(1);
		p.generarAleatorio(31, 35);
	}
}
