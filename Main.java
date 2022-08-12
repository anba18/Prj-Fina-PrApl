/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author ANEL
 */

public class Main {
    // Creo un nuevo objeto de hotel
    private static final Hotel ho = new Hotel ("Proyecto final", " , hotel","Anel Barrios, Raul Arauz, Ayrton Rios");
    private static boolean exit;

    public static void main (String[] args) throws Exception {
        // precarga de datos
        Hotel.cargarDatos(ho);
        // menu
        runMenu();
    }

    // metodo para ejecutar el menu
    public static void runMenu() throws Exception{
        while(!exit){
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }

    // imprimir el menu en consola
    private static void printMenu() {
        System.out.println("\nSeleccione una opcion: ");
        System.out.println("-------------------");
        System.out.println("1. Gestion de reservas");
        System.out.println("2. Gestion de habitaciones");
        System.out.println("3. Gestion de clientes");
        System.out.println("4. Habitaciones disponibles por tipo de habitacion entre un periodo determinado.");
        System.out.println("5. Reservas realizadas entre un periodo determinado.");
        System.out.println("6. Reservas por clientes entre un periodo detrminado.");
        System.out.println("Para salir precione 0");
    }

    // metodo para recibir el input del usuario en forma de integer
    private static int getInput() throws NumberFormatException{
        Scanner kb = new Scanner(System.in);
        int choice = -1;
        while(choice < 0 || choice > 6){
            try {
                System.out.print("\nIntroduzca la opcion: \n");
                choice = Integer.parseInt(kb.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Introduzca un numero valido\n");
            }
        }
        return choice;
    }

    // metodo para activar las diferentes opciones 
    private static void performAction(int choice) throws Exception {
        switch (choice) {
            case 0 -> exit = true;
            case 1 -> gestionReservas(ho);
            case 2 -> gestionHabitaciones(ho);
            case 3 -> gestionClientes(ho);
            case 4 -> habitacionesPeriodo(ho);
            case 5 -> reservasPeriodo(ho);
            case 6 -> reservasClientePeriodo(ho);
            //definimos el default del menu por si ocurre un error en el programa
            default -> System.out.println("ha ocurrido un error reinicie la aplicacion");
        }
    }

  
    private static void gestionReservas(Hotel hotel) throws Exception {
        while(!exit){
            menuReservas();
            int choice = getInput();
            accionReservas(hotel, choice);
        }
    }

    // 
    private static void menuReservas() {
        System.out.println("\nSelecione una opcion: ");
        System.out.println("---------------------");
        System.out.println("0. volver al menu principal");
        System.out.println("1. Crear una nueva reserva");
        System.out.println("2. Borrar reservacion");
        System.out.println("3. Lista de reservas");
    }

    // submenu action
    private static void accionReservas(Hotel hotel, int choice) throws Exception {
        switch(choice){
            case 0 -> runMenu();
            case 1 -> crearReserva(hotel);
            case 2 -> borrarReserva(hotel);
            case 3 -> listaReservas(hotel);
            default -> System.out.println("ha ocurrido un error reinicie la aplicacion");
        }
    }

    // metodo para crear una reserva
    private static void crearReserva(Hotel hotel) {
        Reservas reserva;
        Cliente cliente = null;
        Habitacion habitacion;
        int numeroHabitacion = 0;
        boolean reservaTerminada = false;
        //se crea el Scanner
        Scanner sc = new Scanner(System.in);

        // se recibe la informacion del cliente
        System.out.print("Introduzca el DNI del cliente: ");
        String c = sc.nextLine();
        // se busca si el cliente existe
        try {
            if (Cliente.clienteExiste(hotel, c)) {
                cliente = hotel.getCliente(c);
                System.out.println(cliente);
            } else {
                // Informa que el cliente no existe y devuelve el menu
                System.out.println("Este cliente no existe");
                runMenu();
            }
        } catch (Exception e) {
            System.err.println("Introduce un cliente valido" + e);
        }

        // resivo el numero de reservas
        int numeroReserva = Reservas.ultimaReserva(ho);
        System.out.println("Numero de la reserva: " + numeroReserva);

        // resivo la fecha de entrada
        LocalDate fechaEntrada;
        do {
            System.out.print("Introduzca la fecha de entrada (formato yyyy-mm-dd): ");
            fechaEntrada = Reservas.parseFecha(sc.nextLine());
            //formateo el metodo de entrada en localdate
        } while ((fechaEntrada == null) || (fechaEntrada.isBefore(LocalDate.now())));

        LocalDate fechaSalida;
        do {
            System.out.print("Introduzca la fecha de salida (formato yyyy-mm-dd): ");
            fechaSalida = Reservas.parseFecha(sc.nextLine());
            // comprobar que la fecha de salida es válida
        } while (fechaSalida == null || fechaSalida.isBefore(fechaEntrada));

        // mostrar habitaciones libres en esa fecha
        System.out.println("las habitaciones libres para esas fechas son: ");
        System.out.println("--------------------------------------------");
      
        /* cargamos las habitaciones disponibles en una matriz, 
        y si la matriz está vacía, ejecute el menú nuevamente, de lo contrario, continúe*/
        Reservas.habitacionPeriodo(hotel, fechaEntrada, fechaSalida);

        // recibir la habitacion
        do {
            try {
                System.out.print("Introduzca el numero de la habitacion: ");
                numeroHabitacion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Introduzca una habitacion valida" + e);
            }

            habitacion = hotel.getHabitacion(numeroHabitacion);

            //comprobar si la habitacion existe en otras reservas del hotel
            for (Reservas res : hotel.getReservas()) {
                if (numeroHabitacion == habitacion.getNumero()) {
                    // declaramos la fecha a comprobar de la reserva existente
                    LocalDate salidaComprobar = res.getFechaSalida();
                    // Comprobar si está disponible
                    if (Reservas.noCoincideTiempo(fechaEntrada, salidaComprobar)) {
                        // si no coincide lo agrego a la reserva
                        System.out.println("Added to the reservation the room number " + numeroHabitacion);
                        // Creo reserva con los datos del usuario
                        reserva = new Reservas(numeroReserva, fechaEntrada, fechaSalida);
                        reserva.addCliente(cliente);
                        reserva.addHabitacion(habitacion);
                        System.out.println(habitacion);
                        hotel.addReserva(reserva);
                        reservaTerminada = true;
                    } else {
                        System.out.println("That room is not available for these dates");
                    }
                }
            }
        } while (!reservaTerminada);
    }

    // metodo para borrar recervas
    private static void borrarReserva(Hotel hotel) {
        boolean borrarReserva = false;
        int numeroReserva = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("    Eliminar reserva      ");
        System.out.println("--------------------------");

        do try {
            System.out.print("Introduzca el numero de la reserva para borrar: ");
            numeroReserva = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Introduzca un numero valido");
        } while (numeroReserva == 0);

        for (Reservas res : hotel.getReservas()) {
            if (res.getNumeroReserva() == numeroReserva) {
                Reservas.borrarReserva(hotel, numeroReserva);
                System.out.print("la reserva se ha borrado");
                borrarReserva = true;
                break;
            }
        }
        if (!borrarReserva) {
            System.out.println("No se encontró la reserva.");
        }

    }

    // listar recervas
    private static void listaReservas(Hotel hotel) {

        System.out.println("--------------------------");
        System.out.println("   Listar reservas        ");
        System.out.println("--------------------------");

        for (Reservas reserva : hotel.getReservas()) {
            System.out.println(reserva);
        }
    }

    // Submenu para la gestion de las habitaciones
    private static void gestionHabitaciones(Hotel hotel) throws Exception {
        while(!exit){
            menuHabitaciones();
            int choice = getInput();
            accionHabitaciones(hotel, choice);
        }
    }

    private static void menuHabitaciones() {
        System.out.println("\nSeleccione la accion: ");
        System.out.println("-------------------");
        System.out.println("0. volver al menu principal");
        System.out.println("1. crear una habitacion nueva");
        System.out.println("2. borrar una habitacion");
        System.out.println("3. Listar habitaciones");
    }

    private static void accionHabitaciones (Hotel hotel, int choice) throws Exception {
        switch(choice){
            case 0 -> runMenu();
            case 1 -> crearHabitacion(hotel);
            case 2 -> borrarHabitacion(hotel);
            case 3 -> listarHabitaciones(hotel);
            default -> System.out.println("Error");
        }
    }
    // fin del submenu



    // metodo para crear habitaciones
    private static void crearHabitacion(Hotel hotel) throws Exception {
        Scanner sc = new Scanner(System.in);
        int numero = 0;

        try {
            System.out.print("Introduzca el Numero de La habitacion: ");
            numero = Integer.parseInt(sc.nextLine());
        } catch (Exception e){
            System.err.println("Introduzca un numero valido\n" + e);
        }

        // Comprobar si la habitacion existe
        if (Habitacion.habitacionExiste(numero, hotel)) {
            System.out.println("Esta habitacion ya existe.");
            menuHabitaciones();
        } else {

            System.out.println("Introduzca el tipo de Habitacion");
            System.out.println("--------------------------");
            System.out.println("0. volver al menu");
            System.out.println("1. Crear habitacion simple");
            System.out.println("2. Crear habitacion doble");
            System.out.println("3. Crear habitacion suite");

            int choice = -1;
            while (choice < 0 || choice > 3) {
                try {
                    System.out.print("\nIntroduzca la Opcion: \n");
                    choice = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Introduzca un numero valido\n");
                }
                switch (choice) {
                    case 0 -> runMenu();
                    case 1 -> crearSimple(hotel, numero);
                    case 2 -> crearDoble(hotel, numero);
                    case 3 -> crearSuit(hotel, numero);
                    default -> System.out.println("error");
                }
            }
        }
    }

    // metodo para crear habitacion simple
    private static void crearSimple(Hotel hotel, int numero) {
        Simple simple;
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca los metros cuadrados: ");
        double metros = sc.nextDouble();
        System.out.print("Introduzca el precio: ");
        double precio = sc.nextDouble();
        //creamos el objeto
        simple = new Simple(numero, metros, precio);
        //agregamos el objeto al array
        hotel.addHabitacion(simple);
        System.out.println("Habitacion creada: " + simple);
    }

    // metodo para crear habitacion doble
    private static void crearDoble(Hotel hotel, int numero) {
        Doble doble;
        Scanner sc = new Scanner(System.in);
        double metros = 0, precio = 0;


        do try {
            System.out.print("Introduzca los metros cuadrados: ");
            metros = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Introduzca un numero valido.\n" + e);
        }  while (metros == 0);
        do try {
            System.out.print("Introduzca el precio: ");
            precio = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Introduzca un numero valido.\n" + e);
        } while (precio == 0);
        System.out.print("Introduzca el tipo de cama: ");
        String tipoCama = sc.nextLine();
        doble = new Doble(numero, metros, precio, tipoCama);
        hotel.addHabitacion(doble);
        System.out.println("Habitacion creada: " + doble);
    }

    // metodo para crear habitacion suite
    private static void crearSuit(Hotel hotel, int numero) {
        Suit suit;
        Scanner sc = new Scanner(System.in);
        double MDormitorio = 0.0, MHabitacion = 0.0, precio = 0.0;

        do try {
            System.out.print("Introduzca los metros cuadrados del dormitorio: ");
            MDormitorio = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Introduzca un numero valido.\n" + e);
        } while (MDormitorio == 0);
        do try {
            System.out.print("Ingrese los metros cuadrados de la habitación adicional: ");
            MHabitacion = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Introduzca un numero valido.\n" + e);
        } while (MHabitacion == 0);
        do try {
            System.out.print("Introduzca el precio: ");
            precio = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Introduza un numero valido.\n" + e);
        } while (precio == 0);
        suit = new Suit(numero, MDormitorio, MHabitacion, precio);
        hotel.addHabitacion(suit);
        System.out.println("Habitacion Creada: " + suit);
    }

    // Metodo para borrar una habitacion
    private static void borrarHabitacion(Hotel hotel) {
        boolean borrarHabitacion = false;
        int numeroHabitacionBorrar = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------");
        System.out.println("   Borrar Habitacion   ");
        System.out.println("-----------------------");
        System.out.print("Introduzca el numero de la habitacion: ");
        do try {
            numeroHabitacionBorrar = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Enter a valid number");
        } while (numeroHabitacionBorrar == 0);

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (numeroHabitacionBorrar == habitacion.getNumero()) {
                Habitacion.borrarHabitacion(hotel, numeroHabitacionBorrar);
                System.out.print("La habitacion se ha borrado.");
                borrarHabitacion = true;
                break;
            }
        }
        if (!borrarHabitacion) {
            System.out.println("No se encontró la habitación.");
        }

    }

    // Listaa de habitaciones filtradas por tipo
    private static void listarHabitaciones(Hotel hotel) {
        Simple simple;
        Doble doble;
        Suit suit;
        System.out.println("--------------------------");
        System.out.println("  Lista de habitaciones   ");
        System.out.println("--------------------------");

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion instanceof Simple) {
                simple = (Simple) habitacion;
                System.out.println(simple);
            } else if (habitacion instanceof Doble) {
                doble = (Doble) habitacion;
                System.out.println(doble);
            } else if (habitacion instanceof Suit) {
                suit = (Suit) habitacion;
                System.out.println(suit);
            }
        }
    }

    // Submenú para la gestion el cliente
    private static void gestionClientes(Hotel hotel) throws Exception {
        while(!exit){
            menuClientes();
            int choice = getInput();
            accionClientes(hotel, choice);
        }
    }

    private static void menuClientes() {
        System.out.println("\nSeleccione una opcion: ");
        System.out.println("---------------------");
        System.out.println("0. Volver al menu");
        System.out.println("1. Crear nuevo cliente");
        System.out.println("2. Borrar cliente");
        System.out.println("3. Listar clientes");
    }

    private static void accionClientes(Hotel hotel, int choice) throws Exception {
        switch(choice){
            case 0 -> runMenu();
            case 1 -> crearCliente(hotel);
            case 2 -> borrarCliente(hotel);
            case 3 -> listarClientes(hotel);
            default -> System.out.println("error");
        }
    }
    // Fin submenu

    // Create customer
    private static void crearCliente(Hotel hotel) {
        Cliente cliente;
        //creo el nuevo Scanner
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca el DNI: ");
        String DNI = sc.nextLine();
        // comprobar que el cliente existe
        if(!Cliente.clienteExiste(hotel, DNI)) {
            System.out.print("introduzca el nombre del cliente: ");
            String nombre = sc.nextLine();
            System.out.print("introduzca los apellidos: ");
            String apellidos = sc.nextLine();
            //creo el objeto
            cliente = new Cliente(nombre, apellidos, DNI);
            // lo agrego al ArrayList del hotel 
            hotel.addCliente(cliente);
            System.out.println("Cliente agregado. " + cliente);
        } else  {
            System.out.println("El cliente ya existe");
        }
    }

    // Metodo para borrar un cliente
    private static void borrarCliente(Hotel hotel) {
        boolean borrarCliente = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("   Borrar cliente    ");
        System.out.println("---------------------");
        System.out.print("introduzca el DNI del cliente: ");
        String dniClienteBorrar = sc.nextLine();

        for (Cliente cliente : hotel.getClientes()) {
            if (cliente.getDNI().equals(dniClienteBorrar)) {
                Cliente.borrarCliente(hotel, dniClienteBorrar);
                borrarCliente = true;
                System.out.println("El cliente se ha Borrado.");
                break;
            }
        }
        if (!borrarCliente) {
            System.out.println("Cliente no encontrado");
        }
    }

    // metodo para listar clientes
    private static void listarClientes(Hotel hotel) {
        System.out.println("-------------------------");
        System.out.println("   Listado de clientes   ");
        System.out.println("-------------------------");

        for (Cliente cliente : hotel.getClientes()) {
            System.out.println(cliente);
        }
    }





    // Metodo para mostrar habitaciones disponibles en un periodo determinadao
    //filtrado por tipo de habitacion
    private static void habitacionesPeriodo(Hotel hotel) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la fecha inicial del periodo a comprobar (YYYY-mm-dd): ");
        LocalDate inicioPeriodo = Reservas.parseFecha(sc.nextLine());
        System.out.println("Introduzca la fecha final del periodo a comprobar (YYYY-mm-dd): ");
        LocalDate finPeriodo = Reservas.parseFecha(sc.nextLine());

        // Mostrar habitaciones disponibles en el periodo
        System.out.println("======================================================");
        System.out.println("Habitacion disponible entre " + inicioPeriodo + " y   " + finPeriodo);
        System.out.println("======================================================");
        System.out.println("        habitaciones simples disponiblies             ");
        System.out.println("======================================================");

        // comprobar que habitaciones existen en la reserva
        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion instanceof Simple) {
                Reservas.habitacionesPeriodoComprobacion(hotel, inicioPeriodo, finPeriodo, habitacion);
            }
        }

        System.out.println("======================================================");
        System.out.println("            Habitaciones dobles disponibles           ");
        System.out.println("======================================================");

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion instanceof Doble) {
                Reservas.habitacionesPeriodoComprobacion(hotel, inicioPeriodo, finPeriodo, habitacion);
            }
        }

        System.out.println("======================================================");
        System.out.println("          Habitaciones suit disponibles               ");
        System.out.println("======================================================");

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            if (habitacion instanceof Suit) {
                Reservas.habitacionesPeriodoComprobacion(hotel, inicioPeriodo, finPeriodo, habitacion);
            }
        }
    }

    // Metodo para comprobar las recervas en un periodo de tiempo
    private static void reservasPeriodo(Hotel hotel) {
        boolean encontrado = false;
        Scanner sc = new Scanner(System.in);
        //introduccion de fecha inicial
        System.out.println("Introduzca la fecha de inicio del período (YYYY-mm-dd): ");
        LocalDate inicioPeriodo = Reservas.parseFecha(sc.nextLine());
        //introduccion de fecha final
        System.out.println("Introduzca la fecha de finalización del período (YYYY-mm-dd): ");
        LocalDate finPeriodo = Reservas.parseFecha(sc.nextLine());
        // Buscamos todas las recervas
        if (!hotel.getReservas().isEmpty()) {
            System.out.println("Reservas existentes para el período");
            System.out.println("====================================");

            for (Reservas reserva : hotel.getReservas()) {
                // Get reservation entry and exit dates
                LocalDate fechaEntrada = reserva.getFechaSalida();
                LocalDate fechaSalida = reserva.getFechaEntrada();
                if (Reservas.fechaEnPeriodo(inicioPeriodo, finPeriodo, fechaEntrada, fechaSalida)) {
                    // Imprimimos las que se evaluen true en el periodo
                    System.out.println(reserva);
                    encontrado = true;
                }
            }
            // Si no se han encontrado reservas
            if (!encontrado) {
                System.out.println("No existen recervas para este periodo");
            }
        } else {
            System.out.println("No hay reservas en el hotel");
        }

    }
    //metodo para filtrar reservas por cliente en un periodo determinado
    private static void reservasClientePeriodo(Hotel hotel) {
        //recervas por cliente entre un periodo determinado
        //recivir las fechas a comprobar
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la fecha inicial del periodo a comprobar (YYYY-mm-dd): ");
        LocalDate inicioPeriodo = Reservas.parseFecha(sc.nextLine());
        System.out.println("Introduzca la fecha final del periodo a comprobar (YYYY-mm-dd): ");
        LocalDate finPeriodo = Reservas.parseFecha(sc.nextLine());

        for (Cliente cliente : hotel.getClientes()) {
            boolean estaEnReserva = false;
            for (Reservas reserva : hotel.getReservas()) {
                LocalDate entrada = reserva.getFechaEntrada();
                LocalDate salida = reserva.getFechaSalida();

                // si la recerva coincide con el periodo y es la misma habitacion
                if (Reservas.fechaEnPeriodo(inicioPeriodo, finPeriodo, entrada, salida) &&
                        (reserva.getCliente().contains(cliente))) {
                    estaEnReserva = true;
                }
                if (estaEnReserva) {
                    System.out.println(reserva);
                }
                // si se ha cambiado el valor el booleano se resetea
                estaEnReserva = false;
            }
        }
    }
}