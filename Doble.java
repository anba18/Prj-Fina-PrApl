/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author ANEL
 */
public class Doble extends Habitacion{
    private double M2;
    private double precio;
    private String TipoCama;

    public Doble(int numero, double M2, double precio, String TipoCama) {
        super(numero);
        this.M2 = M2;
        this.precio = precio;
        this.TipoCama = TipoCama;
    }

    public double getM2() {
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

    public String getTipoCama() {
        return TipoCama;
    }

    public void setTipoCama(String TipoCama) {
        this.TipoCama = TipoCama;
    }

    @Override
    public String toString() {
        return "Datos de la habitacion doble: Numero " + getNumero() + ", " +
                " metros cuadrados, la cama es " + this.TipoCama + ", y el precio de la habitación es " + this.precio;
    }
}
