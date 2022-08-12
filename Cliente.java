/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author ANEL
 */
public class Cliente {
    private String nombre;
    private String apellidos;
    private String DNI;

    public Cliente(String nombre, String apellidos, String DNI) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
    }
//inicio metodos accesores
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    // Inicio de metodos de la clase
    // Metodo para comprobar si un cliente existe con su DNI
    static boolean clienteExiste (Hotel hotel, String DNI) {
        boolean existe = false;
        for (Cliente cli : hotel.getClientes()) {
            if (cli.getDNI().equals(DNI)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    // borrar cliente
    static void borrarCliente(Hotel hotel, String DNI) {
        for (Cliente cliente : hotel.getClientes()) {
            hotel.deleteCliente(cliente);
            break;
        }
    }

    @Override
    public String toString() {
        return "Datos del cliente: " + this.apellidos + ", " + this.nombre+ ", DNI: " + this.DNI;
    }
}