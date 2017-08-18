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

	private void muertePorEnfermedad() {
		// Se saca el 5% de los conejos en ese momento.
		int cinco = (int) ((int) (hembras.size() + machos.size()) * 0.05);
		// Mediante el aleatorio se sacan la cantidad de conejos a morir.
		int machos = probabilidad.generarAleatorio(0, cinco);
		// Las hembras son el sobrante de conejos a morir entre el veinte por ciento
		// Y la cantidad de conejos que ya se eligieron.
		int hembras = cinco - machos;
		// Si la cantidad de machos a morir es mayor a la cantidad de conejos en ese
		// momento
		// Se vuelve a calcular la cantidad de conejos.
		while (machos > this.machos.size() || hembras > this.hembras.size()) {
			machos = probabilidad.generarAleatorio(0, cinco);
			hembras = cinco - machos;
		}
		// Bucle para borrar a las conejas de la lista
		for (int i = 0; i < hembras; i++) {
			int pos = probabilidad.generarAleatorio(0, this.hembras.size());
			this.hembras.remove(pos);
		}
		// Bucle para borrar a los conejos de la lista
		for (int i = 0; i < machos; i++) {
			int pos = probabilidad.generarAleatorio(0, this.machos.size());
			this.machos.remove(pos);
		}
	}

	private void generarHijos(Conejo conejo) {
		int machos = probabilidad.generarAleatorio(0, conejo.getGazapos());
		int hembras = conejo.getGazapos() - machos;
		crearMachos(machos, 0);
		crearHembras(hembras, 0);
	}

	// Alimenta los conejos hasta llegar a un peso de 8000gr=8kilos
	public void alimentarConejos() {
		int alimentar = 0;
		for (int i = 0; i < machos.size(); i++) {
			if (machos.get(i).getPeso() < 8000) {
				alimentar = probabilidad.generarAleatorio(10, 30) * 3;
				machos.get(i).setPeso(machos.get(i).getPeso() + alimentar);
			}
		}
		for (int j = 0; j < hembras.size(); j++) {
			if (hembras.get(j).getPeso() < 8000) {
				alimentar = probabilidad.generarAleatorio(10, 30) * 3;
				hembras.get(j).setPeso(hembras.get(j).getPeso() + alimentar);
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

				// matar por peso
				if (machos.get(j).getPeso() >= 8000 && machos.get(j).getEdad() >= (30 * 3)) {
					machos.remove(j);
				}
			}
			// Bucle de conejos hembra
			for (int j = 0; j < hembras.size(); j++) {
				verificarMadurez(hembras.get(j));
				if (hembras.get(j).isMadurez() && hembras.get(j).getDiasCelo() == -1 && !hembras.get(j).isEmbarazo()) {
					calcularCelo(hembras.get(j));// --> se envian los dias del celo respecto al metodo
					// de calcular aleatoriamente
				}
				// -Generar el tiempo de gestaciÃ³n de los conejos:
				// 98% 31 dÃ­as --- 28 a 35 dÃ­as dependiendo del numero de gazapos.
				if (hembras.get(j).getDiasCelo() == 0) {

					hembras.get(j).setEmbarazo(embarazo());
					if (hembras.get(j).isEmbarazo()) {
						hembras.get(j).setCelo(false);
						if (hembras.get(j).getEdad() >= (2 * 365) && hembras.get(j).getEdad() <= (5 * 365)) {
							hembras.get(j).setGazapos(probabilidad.generarAleatorio(4, 8));// aqui enviamos el numero de
						}
						if (hembras.get(j).getEdad() < (2 * 365) || hembras.get(j).getEdad() > (5 * 365)) {
							hembras.get(j).setGazapos(probabilidad.generarAleatorio(4, 6));
						}
						// hay q hacer un random diferente para el 98% de probabilidad para 31
						hembras.get(j).setTiempoGestacion(probabilidad.diasConcepcion(hembras.get(j).getGazapos()));// radom gestacion 98%
						// 31 dÃ­as --- 28 a
						// 35 dÃ­as
						// dependiendo del numero de gazapos
					}

					if (!hembras.get(j).isEmbarazo()) {
						hembras.get(j).setCelo(false);
						hembras.get(j).setDiasCelo(probabilidad.generarAleatorio(14, 16));// random dias de celo
					}

				}
				if (hembras.get(j).isEmbarazo()) {
					if (hembras.get(j).getTiempoGestacion() == 0) {
						if (hembras.get(j).getEdad() >= (2 * 365) && hembras.get(j).getEdad() <= (5 * 365)) {
							int muertos = probabilidad.generarAleatorio(0, 2);
							hembras.get(j).setGazapos(hembras.get(j).getGazapos() - muertos);
						}
						if (hembras.get(j).getEdad() < (2 * 365)) {
							int muertos = probabilidad.generarAleatorio(0, 4);
							hembras.get(j).setGazapos(hembras.get(j).getGazapos() - muertos);
						}
						generarHijos(hembras.get(j));
						hembras.get(j).setEmbarazo(false);
						hembras.get(j).setLactancia(true);
						calcularCelo(hembras.get(j));
						hembras.get(j).setDiasLactancia(28);
					}
				}

				// -->Verificar lactancia y embarazo: Si la coneja tiene en 1 los dÃ­as de
				// lactancia
				// y estÃ¡ embarazada agregar 1 semana de lactancia.
				if (hembras.get(j).isLactancia()) {
					verificarLactancia(hembras.get(i));
				}

				hembras.get(j).reducirDia();
				hembras.get(j).setEdad(hembras.get(j).getEdad() + 1);
				if (hembras.get(j).getEdad() == 5475) {
					hembras.remove(hembras.get(j));
				} // matar(hembras.get(j)) utlizando el metodo
				hembras.get(j).setEdad(hembras.get(j).getEdad() + 1);

				if (hembras.get(j).getPeso() >= 8000 && hembras.get(j).getEdad() >= (30 * 3)) {
					hembras.remove(j);
				}
			}
		}
	}

	public void matar(Conejo conejo) {
		if (machos.contains(conejo)) {
			machos.remove(conejo);
		} else
			hembras.remove(conejo);
	}

	


	// se reproducede acuerdo a los machos donde 1 macho puede reproducirce con 5
	// hembras
	private void reproducir() {
		// TODO Auto-generated method stub

	}


}
