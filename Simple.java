/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author ANEL
 */
public class Simple extends Habitacion{
    private double M2;
    private double precio;

    public Simple(int Numero, double M2, double precio) {
        super(Numero);
        this.M2 = M2;
        this.precio = precio;
    }

    public double getSquareM2() {
        return M2;
    }

    public void M2(double M2) {
        this.M2 = M2;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Datos de la habitacion simple : " + getNumero() + ", " + this.M2 +
                " metros cuadrados, precio: " + this.precio;
    }
}
