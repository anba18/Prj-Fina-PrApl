/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author ANEL
 */



public class Reservas {
    private int NumeroReserva;
    private LocalDate FechaEntrada;
    private LocalDate FechaSalida;
    private ArrayList<Cliente> cliente;
    private ArrayList<Habitacion> habitacion;

    public Reservas(int NumeroReservas, LocalDate FechaEntrada, LocalDate FechaSalida) {
        this.NumeroReserva = NumeroReserva;
        this.FechaEntrada = FechaEntrada;
        this.FechaSalida = FechaSalida;
        this.cliente = new ArrayList<>();
        this.habitacion = new ArrayList<>();
    }

    public int getNumeroReserva() {
        return NumeroReserva;
    }

    public LocalDate getFechaEntrada() {
        return FechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return FechaSalida;
    }

    public ArrayList<Cliente> getCliente() {
        return cliente;
    }

    public ArrayList<Habitacion> getHabitacion() {
        return habitacion;
    }

    public void setNumeroReserva(int NumeroReserva) {
        this.NumeroReserva = NumeroReserva;
    }

    public void setFechaEntrada(LocalDate FechaEntrada) {
        this.FechaEntrada = FechaEntrada;
    }

    public void setFechaSalida(LocalDate FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public void setCliente(ArrayList<Cliente> cliente) {
        this.cliente = cliente;
    }

    public void setHabitacion(ArrayList<Habitacion> habitacion) {
        this.habitacion = habitacion;
    }

    public void addCliente(Cliente clientes) {
        cliente.add(clientes);
    }

    public void addHabitacion(Habitacion habitaciones) {
        habitacion.add(habitaciones);
    }

    // inicio de metodos para interactuar con el hotel
    public static int ultimaReserva(Hotel hotel) {
        int numeroUltimaReserva = 0;
        ArrayList<Reservas> reserva = hotel.getReservas();
        // go to the last position, get last number and update to new number
        numeroUltimaReserva = (reserva.get(reserva.size() - 1).getNumeroReserva()) + 1;
        return numeroUltimaReserva;
    }
    // metodos para hacer un parsing de string a localDate
    public static LocalDate parseFecha(String string) {
        LocalDate fechaParsing = null;
        do try {
            fechaParsing = LocalDate.parse(string);
        } catch (DateTimeParseException e) {
            System.out.println("Introduce un formado de fecha valido (YYYY-mm-dd)" + e);
        }   while (fechaParsing == null);

        return fechaParsing;
    }

    // metodo habitacion para comprobar si coincide en un periodo de tiempo
    public static boolean noCoincideTiempo (LocalDate entradaReservaNueva, LocalDate salidaReservaExistente) {
        return entradaReservaNueva.isAfter(salidaReservaExistente) &&
                entradaReservaNueva.isEqual(salidaReservaExistente);
    }

    // metodo para comprobar si una fecha esta dentro de un periodo especifico
    public static boolean fechaEnPeriodo(LocalDate inicioPeriodo, LocalDate finPeriodo, LocalDate fechaEntrada,
                                       LocalDate fechaSalida) {
        return fechaSalida.isAfter(inicioPeriodo) && fechaEntrada.isBefore(finPeriodo);
    }

    // metodo para mostrar habitaciones libres para reservar
    public static void habitacionPeriodo(Hotel hotel, LocalDate inicioPeriodo, LocalDate finPeriodo) {
        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion instanceof Simple) {
                habitacionesPeriodoComprobacion(hotel, inicioPeriodo, finPeriodo, habitacion);
            }
        }

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion instanceof Doble) {
                habitacionesPeriodoComprobacion(hotel, inicioPeriodo, finPeriodo, habitacion);
            }
        }

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            habitacionesPeriodoComprobacion(hotel, inicioPeriodo, finPeriodo, habitacion);
        }
    }

    public static void habitacionesPeriodoComprobacion(Hotel hotel, LocalDate inicioPeriodo, LocalDate finPeriodo, Habitacion habitacion) {
        boolean ocupada = false;
        for (Reservas reserva : hotel.getReservas()) {
            LocalDate entrada = reserva.getFechaEntrada();

            LocalDate salida = reserva.getFechaSalida();

            // si la reserva coincide con el periodo y es la misma habitacion 
            if ((fechaEnPeriodo(inicioPeriodo, finPeriodo, entrada, salida)) && (reserva.getHabitacion().contains(habitacion))) {
                ocupada = true;
                break;
            }
        }
        if (!ocupada) {
            System.out.println(habitacion);
        }
    }

    public static void borrarReserva(Hotel hotel, int numero) {
        for (Reservas reserva : hotel.getReservas()) {
            if (reserva.getNumeroReserva()== numero) {
                hotel.deleteReservation(reserva);
                break;
            }
        }
    }
    //fin del metodo para interactuar con el hotel
    @Override
    public String toString() {
        return "Numero de reserva: " + this.NumeroReserva + ", el cliente: " + this.cliente +
                " en la habitacion " + this.habitacion + ", fecha de entrada: " + this.FechaEntrada + ", fecha de salida: " + this.FechaSalida;
    }
}