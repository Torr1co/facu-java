package tp02.ejercicio2;
public class ColaGenerica<T> {
    private ListaGenerica<T> datos;
    
    public ColaGenerica() {
        this.datos = new ListaEnlazadaGenerica<T>();
    }

    public void encolar(T dato){
        datos.agregarFinal(dato);
    }

    public T desencolar(){
        T dato = datos.elemento(1);
        datos.eliminarEn(1);

        return dato;
    }

    public T tope(){
        T dato = datos.elemento(1);
        return dato;
    }

    public boolean esVacia(){
        return datos.esVacia();
    }
}
