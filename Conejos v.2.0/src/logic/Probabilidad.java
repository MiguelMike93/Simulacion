package logic;

public class Probabilidad {
	
	private int distribucion;
	
	
	public Probabilidad(int distribucion) {
		this.distribucion = distribucion;
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
		// TODO Auto-generated method stub
		return 0;
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
	
	
}
