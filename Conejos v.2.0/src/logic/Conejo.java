package logic;

public class Conejo {

	private boolean madurez, celo, lactancia, embarazo;
	private int edad, edadMadurez, diasCelo, diasLactancia, tiempoGestacion, gazapos;
	
	public Conejo(int edad) {
		this.edad = edad;
		this.madurez=false;
		this.celo=false;
		this.lactancia=false;
		this.embarazo=false;
		this.edadMadurez=-1;// es el random FALTA POR HACER 
		this.diasCelo=-1;// es el random FALTA POR HACER
		this.diasLactancia=-1;// es el random FALTA POR HACER
		this.tiempoGestacion=-1;// es el random FALTA POR HACER	
		this.gazapos=-1;//es el random FALTA POR HACER
	}
	
	public void reducirDia() {
		if(diasCelo>-1)diasCelo-=1;
		if(diasLactancia>-1)diasLactancia-=1;
		if(tiempoGestacion>-1)tiempoGestacion-=1;
		if(gazapos>-1)gazapos-=1;
	}
	
	
//----------------------------------------------------GET Y SET ---------------------------------------------

	public boolean isMadurez() {
		return madurez;
	}

	public void setMadurez(boolean madurez) {
		this.madurez = madurez;
	}

	public boolean isCelo() {
		return celo;
	}

	public void setCelo(boolean celo) {
		this.celo = celo;
	}

	public boolean isLactancia() {
		return lactancia;
	}

	public void setLactancia(boolean lactancia) {
		this.lactancia = lactancia;
	}

	public boolean isEmbarazo() {
		return embarazo;
	}

	public void setEmbarazo(boolean embarazo) {
		this.embarazo = embarazo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getEdadMadurez() {
		return edadMadurez;
	}

	public void setEdadMadurez(int edadMadurez) {
		this.edadMadurez = edadMadurez;
	}

	public int getDiasCelo() {
		return diasCelo;
	}

	public void setDiasCelo(int diasCelo) {
		this.diasCelo = diasCelo;
	}

	public int getTiempoGestacion() {
		return tiempoGestacion;
	}

	public void setTiempoGestacion(int tiempoGestacion) {
		this.tiempoGestacion = tiempoGestacion;
	}

	public int getGazapos() {
		return gazapos;
	}

	public void setGazapos(int gazapos) {
		this.gazapos = gazapos;
	}
	
	public int getDiasLactancia() {
		return diasLactancia;
	}
	
	public void setDiasLactancia(int diasLactancia) {
		this.diasLactancia = diasLactancia;
	}
	
	
	
}
