/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author ANEL
 */


public class Hotel {
    private String nombre;
    private String domicilio;
    private String ciudad;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reservas> reservas;
    private ArrayList<Habitacion> habitaciones;

    public Hotel(String nombre, String domicilio, String ciudad) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
        clientes = new ArrayList<>();
        reservas = new ArrayList<>();
        habitaciones = new ArrayList<>();
    }

    public String getNombre() {return nombre;}

    public String getDomicilio() {return domicilio;}

    public String getCiudad() {return ciudad;}

    public ArrayList<Cliente> getClientes() {return clientes;}

    public ArrayList<Reservas> getReservas() {return reservas;}

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCliente(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setReservas(ArrayList<Reservas> reservas) {
        this.reservas = reservas;
    }

    public void setHabitacion(ArrayList<Habitacion> habitacion) {
        this.habitaciones = habitacion;
    }

    // Class methods
    // Get a customer from the arraylist with ID
    public Cliente getCliente(String DNI) {
        Cliente cliente = null;
        boolean found = false;
        Iterator<Cliente> it = clientes.iterator();
        while (!found && it.hasNext()){
            Cliente c = it.next();
            if (c.getDNI().equals(DNI)) {
                cliente = c;
                found = true;
            }
        }
        return cliente;
    }

    public Habitacion getHabitacion(int numero) {
        Habitacion habitacion = null;
        boolean found = false;
        Iterator<Habitacion> it = habitaciones.iterator();
        while (!found && it.hasNext()) {
            Habitacion r = it.next();
            if (r.getNumero() == numero) {
                habitacion = r;
                found = true;
            }
        }
        return habitacion;
    }

    // Methods to add to the ArrayList
    public void addReserva(Reservas reserva) {reservas.add(reserva);}

    public void addCliente(Cliente cliente) {clientes.add(cliente);}

    public void addHabitacion(Habitacion habitacion) { habitaciones.add(habitacion);}

    // Methods to delete from the ArrayList
    public void deleteReservation(Reservas reserva) {reservas.remove(reserva);}

    public void deleteCliente(Cliente cliente) {clientes.remove(cliente);}

    public void deleteRoom(Habitacion habitacion) {habitaciones.remove(habitacion);}

    // Clear information in the ArrayList
    public void borrarDatos() {
        reservas.clear();
        habitaciones.clear();
        clientes.clear();
    }

    // Preload information on the application
    public static void cargarDatos(Hotel hotel) {
        Simple simple;
        Doble doble;
        Suit suit;
        Habitacion habitacion;
        Cliente cliente;
        Reservas reserva;

        hotel.borrarDatos();

        // Habitaciones
        simple = new Simple(1, 24.5, 80.4);
        hotel.addHabitacion(simple);
        simple = new Simple(4, 12.5, 78.3);
        hotel.addHabitacion(simple);
        simple = new Simple(5, 20.4, 79.4);
        hotel.addHabitacion(simple);
        doble = new Doble(2, 30.1, 100.76, "matrimonio");
        hotel.addHabitacion(doble);
        doble = new Doble(6, 30.1, 100.76, "matrimonio");
        hotel.addHabitacion(doble);
        doble = new Doble(7, 30.1, 100.76, "matrimonio");
        hotel.addHabitacion(doble);
        suit = new Suit(3, 30.8, 24.5, 145.89);
        hotel.addHabitacion(suit);
        suit = new Suit(8, 30.8, 24.5, 145.89);
        hotel.addHabitacion(suit);
        suit = new Suit(9, 30.8, 24.5, 145.89);
        hotel.addHabitacion(suit);

        // Customers
        cliente = new Cliente("anel", "barrios pinillo", "2345673A");
        hotel.addCliente(cliente);
        cliente = new Cliente("Manfredo", "Di Sicillia", "33529651R");
        hotel.addCliente(cliente);
        cliente = new Cliente("Farinata", "Degli Uberti", "1234567Q");
        hotel.addCliente(cliente);
        //reservas
        cliente = hotel.getCliente("2345673A");
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 22);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 30);
        reserva = new Reservas(1, fechaEntrada, fechaSalida);
        habitacion = hotel.getHabitacion(1);
        reserva.addHabitacion(habitacion);
        reserva.addCliente(cliente);
        hotel.addReserva(reserva);

        cliente = hotel.getCliente("2345673A");
        fechaEntrada = LocalDate.of(2021, 12, 02);
        fechaSalida = LocalDate.of(2021, 12, 14);
        reserva = new Reservas(5, fechaEntrada, fechaSalida);
        habitacion = hotel.getHabitacion(8);
        reserva.addHabitacion(habitacion);
        reserva.addCliente(cliente);
        hotel.addReserva(reserva);

        cliente = hotel.getCliente("33529651R");
        fechaEntrada = LocalDate.of(2021, 12, 24);
        fechaSalida = LocalDate.of(2021, 12, 28);
        reserva = new Reservas(2, fechaEntrada, fechaSalida);
        habitacion = hotel.getHabitacion(4);
        reserva.addHabitacion(habitacion);
        reserva.addCliente(cliente);
        hotel.addReserva(reserva);

        cliente = hotel.getCliente("1234567Q");
        fechaEntrada = LocalDate.of(2021, 12, 17);
        fechaSalida = LocalDate.of(2021, 12, 22);
        reserva = new Reservas(3, fechaEntrada, fechaSalida);
        habitacion = hotel.getHabitacion(9);
        reserva.addHabitacion(habitacion);
        reserva.addCliente(cliente);
        hotel.addReserva(reserva);

        cliente = hotel.getCliente("2345673A");
        fechaEntrada = LocalDate.of(2021, 12, 16);
        fechaSalida = LocalDate.of(2021, 12, 20);
        reserva = new Reservas(4, fechaEntrada, fechaSalida);
        habitacion = hotel.getHabitacion(5);
        reserva.addHabitacion(habitacion);
        reserva.addCliente(cliente);
        hotel.addReserva(reserva);


        System.out.println("Datos cargados con exito.");
    }

    @Override
    public String toString() {
        return "Hotel " + this.nombre + "se encuentra en " + this.domicilio + " la ciudad de " + this.ciudad;
    }
}