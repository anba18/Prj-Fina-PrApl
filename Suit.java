/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author ANEL
 */
public class Suit extends Habitacion {
    private double M2Dormitorio;
    private double M2Sala;
    private double precio;

    public Suit(int numero, double M2Dormitorio, double M2Sala, double precio) {
        super(numero);
        this.M2Dormitorio = M2Dormitorio;
        this.M2Sala = M2Sala;
        this.precio = precio;
    }

    public double getM2Dormitorio() {
        return M2Dormitorio;
    }

    public void setM2Dormitorio(double M2Dormitorio) {
        this.M2Dormitorio = M2Dormitorio;
    }

    public double getM2Sala() {
        return M2Sala;
    }

    public void setM2Sala(double M2Sala) {
        this.M2Sala = M2Sala;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Información sobre la suite: número de habitación " + getNumero() + ", metros cuadrados del dormitorio: " +
                this.M2Dormitorio + ", metros cuadrados de la segunda habitación: " + this.M2Sala +
                ", the price is " + this.precio;
    }
}