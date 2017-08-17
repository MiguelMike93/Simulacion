package main;

import java.util.ArrayList;

import logic.Conejo;

public class Simulacion {
	
	private int tiempoEstudio;
	private ArrayList<Conejo> machos, hembras;
	
	public Simulacion(int tiempoEstudio, int machos, int hembras, int edadMachos, int edadHembras) {
		this.tiempoEstudio = tiempoEstudio;
		this.machos = new ArrayList<>();
		this.hembras = new ArrayList<>();
		crearMachos(machos, edadMachos);
		crearHembras(hembras, edadHembras);
	}
	
	private void crearMachos(int cantidad, int edad) {
		for (int i = 0; i < cantidad; i++) {
			Conejo conejo = new Conejo(edad);
			//--> Generar aleatoriamente el tiempo en que va a madurar el conejo (4 - 8 meses).
			machos.add(conejo);
		}
	}
	
	private void crearHembras(int cantidad, int edad) {
		for (int i = 0; i < cantidad; i++) {
			Conejo conejo = new Conejo(edad);
			//--> Generar aleatoriamente el tiempo en que va a madurar el conejo (4 - 6 meses).
			hembras.add(conejo);
		}
	}
	
	
	public void simular() {
		//Bucle de los dias de estudio
		for (int i = 0; i < tiempoEstudio; i++) {
			//Bucle de conejos macho
			for (int j = 0; j < machos.size(); j++) {
				//--> Verificar la madurez del conejo: cambiar su estado a true en madurez.
				machos.get(j).setEdad(machos.get(j).getEdad()+1);//Aumenta la edad del conejo.
				//-->Verificar edad del conejo para matarlo. Edad de muerte (15 a�os).
			}
			//Bucle de conejos hembra
			for (int j = 0; j < hembras.size(); j++) {
				//--> Verificar la madurez del conejo: cambiar su estado a true en madurez.
				//--> Calcular celo: depende de si ya madur�, si los d�as de celo son iguales a 0 
				//						y si no est� embarazada.
				//--> Verificar celo: Si est� en celo: 
				//		--> Mediante random decidir si queda embarazada o no.
				//		--> Si queda embarazada: 
				//				-Quitar celo,
				//				-Generar el numero de gazapos: de 4 - 8 si la coneja tiene de 2 a 5 a�os.
				//				-Generar el tiempo de gestaci�n de los conejos: 		
				//							98% 31 d�as --- 28 a 35 d�as dependiendo del numero de gazapos.
				//		-->Sino queda: -Quitar celo
				//					   -Calcular d�as para el celo.
				//-->Si est� embarazada:
				//		-->Verificar parto: Si los dias del parto son iguales a 0:
				//					-->Calcular mortalidad de gazapos: 
				//							en promedio 2, pero varia respecto a la
				//							edad de la coneja, entre m�s joven m�s probabilidad de morir,
				//							y la cantidad de gazapos en el parto, a mayor cantidad,
				//							mayor probabilidad.
				//					-->Generar cantidad de machos y hembras respecto a los sobrevivientes.
				//					-->Agregar machos y hembras a la lista correspondiente.
				//					-->Quitar estado de embarazo, poner estado de lactancia.
				//					-->Calcular celo.
				//					-->D�as de lactancia: 4 semanas.
				//
				//-->Si est� lactando:
				//		-->Verificar lactancia y embarazo: Si la coneja tiene en 1 los d�as de lactancia
				//										y est� embarazada agregar 1 semana de lactancia.
				//
				//-->Reducir d�as: Metodo que reduzca los d�as de todas los posibles estados 
				//					siempre y cuando el numero de d�as sea mayor a 0.
				//-->Aumentar edad coneja;
			}
		}
	}
	
	
}
