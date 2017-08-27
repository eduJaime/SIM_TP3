/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.LinkedList;

/**
 *
 * @author Kevin
 */
public class GeneradorUniforme extends Generador {

    private double limiteInferior;

    private double limiteSuperior;

    public GeneradorUniforme(int precision, double limiteInferior, double limiteSuperior) {
        super(precision);
        this.limiteInferior = limiteSuperior;
        this.limiteSuperior = limiteSuperior;
    }

    public GeneradorUniforme(double limiteInferior, double limiteSuperior) {
        super();
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }

    public double generarNumeroAleatorio() {

        return(limiteInferior + Math.random() * (limiteSuperior - limiteInferior));
    }

    public LinkedList generarNumeroAleatorio(int cantidad) {
        LinkedList lista = new LinkedList();
        for (int i = 0; i < cantidad; i++) {
            lista.add(i, generarNumeroAleatorio());
        }
        return lista;
    }

}
