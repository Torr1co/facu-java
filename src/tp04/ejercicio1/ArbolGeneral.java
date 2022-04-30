package tp04.ejercicio1;

import tp02.ejercicio2.ColaGenerica;
import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class ArbolGeneral<T extends Comparable<T>> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}

	public ListaEnlazadaGenerica<T> preOrden() {
		return null;
	}
	
	public Integer altura() {
		if (tieneHijos()){
			ListaGenerica<ArbolGeneral<T>> hijos = getHijos();
			int[] maxArr = new int[hijos.tamanio()];
			int index = 0;
			hijos.comenzar();
			while (!hijos.fin()){;
				ArbolGeneral<T> abgAct = hijos.proximo();
				maxArr[index]+=1+abgAct.altura();
				index++;
			}
			int maxNum = 0;
			for (int candidato:maxArr){
				if (candidato > maxNum){
					maxNum = candidato;
				}
			}

			return maxNum;
		} else{
			return 1;
		}
	}

	public Integer nivel(T dato) {
		int nivel = 0;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(this);
		cola.encolar(null);
		boolean encontrado = false;
		while (!cola.esVacia()){
			ArbolGeneral<T> abAct = cola.desencolar();

			if (abAct != null){
				if (dato.compareTo(abAct.getDato()) == 0){
					encontrado = true;
					while (!cola.esVacia()){
						cola.desencolar();
					}
				} else {
					ListaGenerica<ArbolGeneral<T>> hijos = abAct.getHijos();
					hijos.comenzar();
					while (!hijos.fin()){
						cola.encolar(hijos.proximo());
					}
				}
			} else if (!cola.esVacia()){
				nivel++;
				cola.encolar(null);
			}
		}
		if (encontrado){
			return nivel;
		} else {
			return -1;
		}
	}

	public Integer ancho() {
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		int maxNodos = 0;
		int nodosAct = 0;
		cola.encolar(this);
		cola.encolar(null);

		while (!cola.esVacia()){
			ArbolGeneral<T> ab = cola.desencolar();
			if (ab != null){
				nodosAct++;
				if (ab.tieneHijos()){
					ListaGenerica<ArbolGeneral<T>> hijos = ab.getHijos();

					hijos.comenzar();
					while (!hijos.fin()){
						cola.encolar(hijos.proximo());
					}
				}
			} else if (!cola.esVacia()){
				if (nodosAct > maxNodos){
					maxNodos = nodosAct;
				}
				nodosAct = 0;
				cola.encolar(null);
			}
		}
		return maxNodos;
	}

	public Boolean esAncestro(T a, T b){
		boolean estaA = false;
		boolean estaB = false;

		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(this);
		cola.encolar(null);

		while (!cola.esVacia()){
			ArbolGeneral<T> arbol = cola.desencolar();
			if (arbol != null){
				dato = arbol.getDato();
				if (dato.compareTo(a) == 0){
					estaA=true;
					while (!cola.esVacia()){
						cola.desencolar();
					}
					cola.encolar(null);
				} else if (estaA && dato.compareTo(b) == 0){
					return true;
				}

				if (arbol.tieneHijos()){
					ListaGenerica<ArbolGeneral<T>> hijos = arbol.getHijos();
					hijos.comenzar();
					while (!hijos.fin()){
						cola.encolar(hijos.proximo());
					}
				}
			} else {
				if (!cola.esVacia()) cola.encolar(null); 
			}

		}

		return estaA && estaB;
	}

}