package tp04.ejercicio1;

import tp02.ejercicio2.ListaGenerica;

public class RedDeAguaPotable {
    private ArbolGeneral<Integer> arbol;

    public RedDeAguaPotable(ArbolGeneral<Integer> arbol){
        this.arbol =arbol;
    }
    public double minimoCaudal(double caudal){
        if (arbol.tieneHijos()){
            ListaGenerica<ArbolGeneral<Integer>> hijos = arbol.getHijos();
            double[] caudales = new double[hijos.tamanio()];
            int index = 0;
            double caudalHijo = caudal / hijos.tamanio();

            hijos.comenzar();
            while (!hijos.fin()){
                RedDeAguaPotable redHijo = new RedDeAguaPotable(hijos.proximo());
                caudales[index] = redHijo.minimoCaudal(caudalHijo);
                index++;
            }

            for (double caudalAct:caudales){
                if (caudalAct<caudal){
                    caudal = caudalAct;
                }
            }

            return caudal;
        } else return caudal;
    }
}
