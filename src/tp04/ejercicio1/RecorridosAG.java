package tp04.ejercicio1;

import tp02.ejercicio2.ColaGenerica;
import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class RecorridosAG {
    public ListaGenerica<Integer> numerosImparesMayoresQuePorNiveles (ArbolGeneral
<Integer> a, Integer n){
    ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<ArbolGeneral<Integer>>();
    ListaGenerica<Integer> listaFinal = new ListaEnlazadaGenerica<Integer>();

    cola.encolar(a);
    cola.encolar(null);
    while (!cola.esVacia()){
        ArbolGeneral<Integer> abbAct = cola.desencolar();
        if (abbAct != null){
            int dato = abbAct.getDato();
            if (dato % 2 != 0 && dato < n){
                listaFinal.agregarFinal(dato);
            }
            if (abbAct.tieneHijos()){
                ListaGenerica<ArbolGeneral<Integer>> hijos = abbAct.getHijos();
                hijos.comenzar();

                while (!hijos.fin()){
                    cola.encolar(hijos.proximo());
                }
            }
        } else if (!cola.esVacia()){
            cola.encolar(null);
        }
    }

    return listaFinal;
    }

    public  ListaGenerica< Integer > numerosImparesMayoresQuePreOrden (ArbolGeneral
<Integer> a, Integer n){
        ListaGenerica<Integer> listaFinal = new ListaEnlazadaGenerica<Integer>();
        int dato = a.getDato();
        if (dato < n && dato % 2 != 0){
            listaFinal.agregarFinal(dato);
        }

        if (a.tieneHijos()){
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            hijos.comenzar();
            while (!hijos.fin()){
                ArbolGeneral<Integer> hijo = hijos.proximo();
                ListaGenerica<Integer> agregar = numerosImparesMayoresQuePreOrden(hijo, n);
                if (!agregar.esVacia()){
                    agregar.comenzar();
                    while (!agregar.fin()){
                        listaFinal.agregarFinal(agregar.proximo());
                    }
                }
            }
        }

        return listaFinal;
    }

    public ListaGenerica< Integer > numerosImparesMayoresQuePostOrden (ArbolGeneral
<Integer> a, Integer n){
        ListaGenerica<Integer> listaFinal = new ListaEnlazadaGenerica<Integer>();
        
        if (a.tieneHijos()){
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            hijos.comenzar();
            while (!hijos.fin()){
                ArbolGeneral<Integer> hijo = hijos.proximo();
                ListaGenerica<Integer> agregar = numerosImparesMayoresQuePostOrden(hijo, n);
                if (!agregar.esVacia()){
                    agregar.comenzar();
                    while (!agregar.fin()){
                        listaFinal.agregarFinal(agregar.proximo());
                    }
                }
            }
        }

        int dato = a.getDato();
        if (dato < n && dato % 2 != 0){
            listaFinal.agregarFinal(dato);
        }

        return listaFinal;
    }

}
