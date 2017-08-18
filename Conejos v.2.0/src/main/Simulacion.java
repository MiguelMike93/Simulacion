package main;

import java.util.ArrayList;
import java.util.Random;

import logic.Conejo;
import logic.Probabilidad;

public class Simulacion {

	private int tiempoEstudio;
	private ArrayList<Conejo> machos, hembras;
	private Probabilidad probabilidad;

	public Simulacion(int tiempoEstudio, int machos, int hembras, int edadMachos, int edadHembras, int distribucion) {
		this.tiempoEstudio = tiempoEstudio;
		this.machos = new ArrayList<>();
		this.hembras = new ArrayList<>();
		probabilidad = new Probabilidad(distribucion);
		crearMachos(machos, edadMachos);
		crearHembras(hembras, edadHembras);
	}

	private void crearMachos(int cantidad, int edad) {
		for (int i = 0; i < cantidad; i++) {
			Conejo conejo = new Conejo(edad);
			probabilidad.generarAleatorio(120, 240);
			machos.add(conejo);
		}
	}

	private void crearHembras(int cantidad, int edad) {
		for (int i = 0; i < cantidad; i++) {
			Conejo conejo = new Conejo(edad);
			probabilidad.generarAleatorio(120, 180);
			hembras.add(conejo);
		}
	}

	private void verificarMadurez(Conejo conejo) {
		if (conejo.getEdad() >= conejo.getEdadMadurez()) {
			conejo.setMadurez(true);
		}
	}

	private void verificarLactancia(Conejo conejo) {
		if (conejo.getDiasLactancia() == 1 && conejo.isEmbarazo()) {
			conejo.setDiasLactancia(8);
		}
	}

	private boolean verificarSiHayMachosDisponibles() {
		boolean siHayMachos = false;
		for (int i = 0; i < machos.size(); i++) {
			if (machos.get(i).isMadurez()) {
				siHayMachos = true;
				break;
			}
		}
		return siHayMachos;
	}

	private boolean embarazo() {
		boolean embarazar = false;
		Random random = new Random();
		double r = random.nextDouble();
		if (r >= 0.5) {
			embarazar = true;
		}
		return embarazar;

	}

	private void calcularCelo(Conejo conejo) {
		conejo.setDiasCelo(probabilidad.generarAleatorio(14, 16));
	}

	private void generarHijos(Conejo conejo) {
		int machos = probabilidad.generarAleatorio(0, conejo.getGazapos());
		int hembras = conejo.getGazapos() - machos;
		crearMachos(machos, 0);
		crearHembras(hembras, 0);
	}
	//Alimenta los conejos hasta llegar a un peso de 8000gr=8kilos
	public void alimentarConejos(){
		int alimentar=0;
		for (int i = 0; i < machos.size(); i++) {
			if (machos.get(i).getPeso()<8000) {
				alimentar=probabilidad.generarAleatorio(10, 30)*3;
				machos.get(i).setPeso(machos.get(i).getPeso()+alimentar);
			}
		}
		for (int j = 0; j < hembras.size(); j++) {
			if (hembras.get(j).getPeso()<8000) {
				alimentar=probabilidad.generarAleatorio(10, 30)*3;
				hembras.get(j).setPeso(hembras.get(j).getPeso()+alimentar);
			}
		}
	}
	public void simular() {
		// Bucle de los dias de estudio
		for (int i = 0; i < tiempoEstudio; i++) {
			// Bucle de conejos macho
			for (int j = 0; j < machos.size(); j++) {
				verificarMadurez(machos.get(j));
				machos.get(j).setEdad(machos.get(j).getEdad() + 1);// Aumenta la edad del conejo.
			}
			// Bucle de conejos hembra
			for (int j = 0; j < hembras.size(); j++) {
				verificarMadurez(hembras.get(j));
				if (hembras.get(j).isMadurez() && hembras.get(j).getDiasCelo() == -1 && !hembras.get(j).isEmbarazo()) {
					calcularCelo(hembras.get(j));// --> se envian los dias del celo respecto al metodo
					// de calcular aleatoriamente
				}
				// --> Verificar celo: Si está en celo:
				// --> Mediante random decidir si queda embarazada o no.
				// --> Si queda embarazada:
				// -Quitar celo,
				// -Generar el numero de gazapos: de 4 - 8 si la coneja tiene de 2 a 5 años.
				// -Generar el tiempo de gestación de los conejos:
				// 98% 31 días --- 28 a 35 días dependiendo del numero de gazapos.
				// -->Sino queda: -Quitar celo
				// -Calcular días para el celo.
				if (hembras.get(j).getDiasCelo() == 0) {

					hembras.get(j).setEmbarazo(embarazo());
					if (hembras.get(j).isEmbarazo()) {
						hembras.get(j).setCelo(false);
						if (hembras.get(j).getEdad() >= (2 * 365) && hembras.get(j).getEdad() <= (5 * 365)) {
							hembras.get(j).setGazapos(probabilidad.generarAleatorio(4, 8));// aqui enviamos el numero de
																							// gazapos segun randome 4 -
																							// 8 si la
						}
						if (hembras.get(j).getEdad() < (2 * 365)) {
							hembras.get(j).setGazapos(probabilidad.generarAleatorio(6, 8));
						}
						if (hembras.get(j).getEdad() > (5 * 365)) {
							hembras.get(j).setGazapos(probabilidad.generarAleatorio(4, 6));
						}
						// hay q hacer un random diferente para el 98% de probabilidad para 31
						hembras.get(j).setTiempoGestacion(probabilidad.generarAleatorio(28, 35));// radom gestacion 98%
																									// 31 días --- 28 a
																									// 35 días
						// dependiendo del numero de gazapos
					}

					if (!hembras.get(j).isEmbarazo()) {
						hembras.get(j).setCelo(false);
						hembras.get(j).setDiasCelo(probabilidad.generarAleatorio(14, 16));// random dias de celo
					}

				}
				if (hembras.get(j).isEmbarazo()) {
					if (hembras.get(j).getTiempoGestacion() == 0) {
						// -->Verificar parto: Si los dias del parto son iguales a 0:
						// -->Calcular mortalidad de gazapos:
						// en promedio 2, pero varia respecto a la
						// edad de la coneja, entre mÃ¡s joven mÃ¡s probabilidad de morir,
						// y la cantidad de gazapos en el parto, a mayor cantidad,
						// mayor probabilidad.
						// -->Generar cantidad de machos y hembras respecto a los sobrevivientes.
						// -->Agregar machos y hembras a la lista correspondiente.
						// -->Quitar estado de embarazo, poner estado de lactancia.
						// -->Calcular celo.
						// -->DÃ­as de lactancia: 4 semanas.
						generarHijos(hembras.get(j));
						hembras.get(j).setEmbarazo(false);
						hembras.get(j).setLactancia(true);
						calcularCelo(hembras.get(j));
						hembras.get(j).setDiasLactancia(28);
					}
				}

				// -->Si está lactando:
				// -->Verificar lactancia y embarazo: Si la coneja tiene en 1 los días de
				// lactancia
				// y está embarazada agregar 1 semana de lactancia.
				if (hembras.get(j).isLactancia()) {
					verificarLactancia(hembras.get(i));
				}

				hembras.get(j).reducirDia();
				hembras.get(j).setEdad(hembras.get(j).getEdad() + 1);
				if (hembras.get(j).getEdad() == 5475)
					hembras.remove(hembras.get(j));// matar(hembras.get(j)) utlizando el metodo
				// -->Reducir días: Metodo que reduzca los días de todas los posibles estados
				// siempre y cuando el numero de días sea mayor a 0.
				// -->Aumentar edad coneja;
			}
		}
	}

	public void matar(Conejo conejo) {
		if (machos.contains(conejo)) {
			machos.remove(conejo);
		} else
			hembras.remove(conejo);
	}

	// se matara el 5% de los conejos mensual por enfermedad
	private void matarPorEnfermedad() {
		// TODO Auto-generated method stub

	}

	// aqui se sacrifican los conejos cuando pesan mas de 8 kilos y tienen mas de 3
	// meses de edad
	private void sacrificar() {

	}

	// se reproducede acuerdo a los machos donde 1 macho puede reproducirce con 5
	// hembras
	private void reproducir() {
		// TODO Auto-generated method stub

	}

	// se alimenta a diario 3 veces con un random de 10 a 30 gramos diarios que es
	// el peso que gana el conejo despues de las dos primeras semanas
	private void alimentar() {
		// TODO Auto-generated method stub

	}

}
