package Clases;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.LinkedList;

public abstract class Generador {

    private int precision;

    public Generador(int numero) {
        precision = numero;
    }

    public Generador() {
        precision = 0;
    }
    
    /*trunca los decimales de la variable double en la presicion especificada*/
    public double truncar(double nro) {
        if (precision != 0) {
            nro = Math.floor(nro * Math.pow(10, precision)) / Math.pow(10, precision);
            return (double) nro;
        } else {
            return nro;
        }

    }

    abstract double generarNumeroAleatorio();//genera un numero aleatorio

    abstract LinkedList generarNumeroAleatorio(int n);
    // genera una cantidad n de numeros aleatorios y los devuelve en una lista

}
