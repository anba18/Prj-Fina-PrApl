/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author ANEL
 */
public class Habitacion {
    private int Numero;

    public Habitacion(int Numero) {
        this.Numero = Numero;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    // metodo para comprobar si una habitacion existe
    static boolean habitacionExiste(int Numero, Hotel hotel) {
        boolean existe = false;
        for (Habitacion hab : hotel.getHabitaciones()) {
            if (hab.getNumero() == Numero) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    // metodo para borrar una habitacion
    static void borrarHabitacion(Hotel hotel, int Numero) {
        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion.getNumero() == Numero) {
                hotel.deleteRoom(habitacion);
                break;
            }
        }
    }
}