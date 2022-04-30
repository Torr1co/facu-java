import tp02.ejercicio2.ColaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.AnalizadorArbol;
import tp04.ejercicio1.ArbolGeneral;
import tp04.ejercicio1.RecorridosAG;
import tp04.ejercicio1.RedDeAguaPotable;

public class App {

    public static void crear(ArbolGeneral<Integer> abb, int n){
        ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<ArbolGeneral<Integer>>();
        cola.encolar(abb);
        cola.encolar(null);

        while (!cola.esVacia()){
            ArbolGeneral<Integer> abbAct = cola.desencolar();

            if (abbAct != null){
                ListaGenerica<ArbolGeneral<Integer>> hijos = abbAct.getHijos();
                if (hijos.tamanio() < 3){
                    ArbolGeneral<Integer> abbNuevo = new ArbolGeneral<Integer>(n);
                    abbAct.agregarHijo(abbNuevo);
                    while (!cola.esVacia()){
                        cola.desencolar();
                    }
                }
                else {
                    hijos.comenzar();
                    while (!hijos.fin()){
                        ArbolGeneral<Integer> abbHijo = hijos.proximo();
                        cola.encolar(abbHijo);
                    }
                }
            } else if (!cola.esVacia()){
                cola.encolar(null);
            }
        }
    }


    public static void agregarEnArbol(ArbolGeneral<Integer> abb){
        crear(abb, 1);   
        crear(abb, 2);   
        crear(abb, 3);   
        crear(abb, 11); 
        crear(abb, 12);   
        crear(abb, 13);   
        crear(abb, 21);
        crear(abb, 22);   
        crear(abb, 23);   
        crear(abb, 31);
        crear(abb, 32);   
        crear(abb, 33);   
        crear(abb, 111);   
        crear(abb, 112);   
    }

    public static void recorrerLista(ListaGenerica<Integer> lista){
        if (lista.esVacia()){
            System.out.println("lista vacia");
        } else {
            lista.comenzar();
            System.out.print("[");
            while (!lista.fin()){
                System.out.print(lista.proximo()+", ");
            }
            System.out.println("]");
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArbolGeneral<Integer> abb = new ArbolGeneral<Integer>(0); 
        agregarEnArbol(abb); 

        RecorridosAG r = new RecorridosAG();

        // RECORRIDOS
        ListaGenerica<Integer> porNiveles = r.numerosImparesMayoresQuePorNiveles(abb, 50);
        recorrerLista(porNiveles);

        ListaGenerica<Integer> preOrden = r.numerosImparesMayoresQuePreOrden(abb, 50);
        recorrerLista(preOrden);

        // ListaGenerica<Integer> inOrden = r.numerosImparesMayoresQueInOrden(abb, 50);
        // recorrerLista(inOrden);

        ListaGenerica<Integer> postOrden = r.numerosImparesMayoresQuePostOrden(abb, 50);
        recorrerLista(postOrden);

        // EJERCICIO 4
        int altura = abb.altura();
        System.out.println("altura: "+altura);
        
        int nivel = abb.nivel(111);
        System.out.println("nivel: "+nivel);

        int maxNodos = abb.ancho();
        System.out.println("maxNodos: "+maxNodos);

        // EJERCICIO 5
        AnalizadorArbol analizador = new AnalizadorArbol();
        int maxProm = analizador.devolverMaximoPromedio(abb);
        System.out.println("maxProm: "+maxProm);

        // EJERCICIO 6
        System.out.println(abb.esAncestro(11, 100));
        // EJERCICIO 7
        RedDeAguaPotable red = new RedDeAguaPotable(abb);
        double minCaudal = red.minimoCaudal(1000);
        System.out.println(minCaudal);
    }
}
