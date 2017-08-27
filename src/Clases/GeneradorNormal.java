/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.LinkedList;

public class GeneradorNormal extends Generador {

    private float media;
    private float desviacion;

    public GeneradorNormal(int precision, float medi, float desvi) {
        super(precision);
        media = medi;
        desviacion = desvi;
    }

    public GeneradorNormal(float medi, float desvi) {
        super();
        media = medi;
        desviacion = desvi;
    }
    /*Genera un numero aleatorio con distribucion normal segun la tecnica basada en el teorema central de limites*/
    public double generarNumeroAleatorio() {
        double rnd = 0;
        for (int i = 0; i < 12; i++) {
            rnd += Math.random();
        }
        return this.truncar(rnd - 6);
    }
    
    /*Genera un numero aleatorio con el algoritmo de Box y Muller*/
    public double generarNumeroAleatorioSin(double rnd1, double rnd2) {
        double aleatorio = 0;
        aleatorio = ((-2) * Math.log(rnd1) * Math.sin(2 * Math.PI * rnd2));
        return this.truncar(aleatorio);
    }
    public double generarNumeroAleatorioCos(double rnd1, double rnd2) {
        double aleatorio = 0;
        aleatorio = (-2 * Math.log(rnd1) * Math.cos(2 * Math.PI * rnd2));
        return this.truncar(aleatorio);
    }

    public double normalizar(double rnd) {
        return ((rnd * desviacion) + media);

    }
    
    
    public LinkedList generarNumeroAleatorio(int cantidad) {
        LinkedList lista = new LinkedList();
        for (int i = 0; i < cantidad; i++) {
            lista.add(i, normalizar(generarNumeroAleatorio()));
        }
        return lista;
    }
    /*  
    public LinkedList generarNumeroAleatorio(int cantidad) {
        LinkedList lista = new LinkedList();
        for (int i = 0; i < cantidad; i=i+2) {
            double rnd1 = Math.random();
            double rnd2 = Math.random();
            lista.add(i, normalizar(generarNumeroAleatorioSin(rnd1,rnd2)));
            lista.add(i+1, normalizar(generarNumeroAleatorioCos(rnd1,rnd2)));
        }
        return lista;
    }
     */
}
