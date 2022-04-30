package tp04.ejercicio1;

import tp02.ejercicio2.ColaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class AnalizadorArbol {
    
    public int devolverMaximoPromedio (ArbolGeneral<Integer>arbol) {
        int maxPromedio = 0;
        int promedioAct = 0;
        int promedioCant = 0;
        ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<ArbolGeneral<Integer>>();

        cola.encolar(arbol);
        cola.encolar(null);

        while (!cola.esVacia()){
            ArbolGeneral<Integer> arbAct = cola.desencolar();

            if (arbAct != null){
                promedioAct += arbAct.getDato();
                promedioCant += 1;
                System.out.println(promedioAct);
                if (arbAct.tieneHijos()){
                    ListaGenerica<ArbolGeneral<Integer>> hijos = arbAct.getHijos();

                    hijos.comenzar();
                    while (!hijos.fin()){
                        cola.encolar(hijos.proximo());
                    }
                }

            } else {
                promedioAct = promedioAct / promedioCant;
                if (promedioAct > maxPromedio){
                    maxPromedio = promedioAct;
                }
                promedioAct=0;
                promedioCant=0;
                if (!cola.esVacia()) cola.encolar(null);
            }
        }

        return maxPromedio;
    }

    
}
