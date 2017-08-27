package Clases;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.LinkedList;

public class GeneradorExponencialNegativa extends Generador {

    private float lambda;

    public GeneradorExponencialNegativa(int precision, float lam) {
        super(precision);
        lambda = lam;
    }

    public GeneradorExponencialNegativa(float lam) {
        super();
        lambda = lam;
    }

    public double generarNumeroAleatorio() {
        double rnd = Math.random();
        return this.truncar(Math.log(1 - rnd) / (-lambda));
    }

    public LinkedList generarNumeroAleatorio(int cantidad) {
        LinkedList lista = new LinkedList();
        for (int i = 0; i < cantidad; i++) {
            lista.add(i, generarNumeroAleatorio());
        }
        return lista;
    }

}
