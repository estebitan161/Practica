import java.util.Random;

class EventNode {
    EventNode ptrPasado;
    String evento;
    String cientifico;
    int datos;
    EventNode ptrFuturo;

    EventNode(EventNode ptrPasado, String evento, String cientifico, int datos, EventNode ptrFuturo) {
        this.ptrPasado = ptrPasado;
        this.evento = evento;
        this.cientifico = cientifico;
        this.datos = datos;
        this.ptrFuturo = ptrFuturo;
    }
}

class EventLinkedList {
    private EventNode inicio;
    private EventNode fin;
    private int size;

    EventLinkedList() {
        inicio = null;
        fin = null;
        size = 0;
    }

    void agregarEventoAlternado(String cientifico, int datos) {
        String evento;
        if (size == 0) {
            evento = "A";
        } else {
            EventNode ultimoEvento = fin;
            if (ultimoEvento != null) {
                String ultimoTipo = ultimoEvento.evento;
                evento = (ultimoTipo.equals("A")) ? "B" : (ultimoTipo.equals("B")) ? "C" : "A";
            } else {
                evento = "A";
            }
        }

        EventNode nuevoEvento = new EventNode(null, evento, cientifico, datos, null);
        if (size == 0) {
            inicio = nuevoEvento;
            fin = nuevoEvento;
        } else {
            fin.ptrFuturo = nuevoEvento;
            nuevoEvento.ptrPasado = fin;
            fin = nuevoEvento;
        }
        size++;
    }

    void imprimirListaHorizontal() {
        EventNode actual = inicio;
        while (actual != null) {
            System.out.print("|" + actual.evento + "|" + actual.cientifico + "|" + actual.datos + "| ");
            actual = actual.ptrFuturo;
        }
        System.out.println();
    }

    int contarEventosTipoA() {
        int contador = 0;
        EventNode actual = inicio;
        while (actual != null) {
            if (actual.evento.equals("A")) {
                contador++;
            }
            actual = actual.ptrFuturo;
        }
        return contador;
    }

    int contarEventosTipoB() {
        int contador = 0;
        EventNode actual = inicio;
        while (actual != null) {
            if (actual.evento.equals("B")) {
                contador++;
            }
            actual = actual.ptrFuturo;
        }
        return contador;
    }

    int contarEventosTipoC() {
        int contador = 0;
        EventNode actual = inicio;
        while (actual != null) {
            if (actual.evento.equals("C")) {
                contador++;
            }
            actual = actual.ptrFuturo;
        }
        return contador;
    }

    void mostrarEventosTipoA() {
        EventNode actual = inicio;
        while (actual != null) {
            if (actual.evento.equals("A")) {
                System.out.println("Evento A encontrado");
                System.out.println("Científico: " + actual.cientifico);
                System.out.println("Datos: " + actual.datos + "\n");
            }
            actual = actual.ptrFuturo;
        }
    }

    void mostrarEventosTipoB() {
        EventNode actual = inicio;
        while (actual != null) {
            if (actual.evento.equals("B")) {
                System.out.println("Evento B encontrado");
                System.out.println("Científico: " + actual.cientifico);
                System.out.println("Datos: " + actual.datos + "\n");
            }
            actual = actual.ptrFuturo;
        }
    }

    void mostrarEventosTipoC() {
        EventNode actual = inicio;
        while (actual != null) {
            if (actual.evento.equals("C")) {
                System.out.println("Evento C encontrado");
                System.out.println("Científico: " + actual.cientifico);
                System.out.println("Datos: " + actual.datos + "\n");
            }
            actual = actual.ptrFuturo;
        }
    }

    boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    boolean sonCoprimos(int a, int b) {
        return a != b;
    }

    boolean crearSingularidad(EventNode eventoA, EventNode eventoB, int datosC) {
        if (eventoA.evento.equals("C")) {
            if (esPrimo(eventoA.datos)) {
                if (eventoA.cientifico.equals("Einstein") && eventoB.cientifico.equals("Rosen")) {
                    return false; // Einstein no puede recibir datos de Rosen
                }
                return sonCoprimos(eventoA.datos, datosC) && eventoB != null && eventoB.cientifico.equals("Rosen");
            }
        }
        return false;
    }

    void encontrarSingularidades() {
        EventNode actual = inicio;
        while (actual != null) {
            if (actual.evento.equals("C")) {
                EventNode eventoA = inicio;
                EventNode eventoB = encontrarUltimoEventoB();
                if (crearSingularidad(eventoA, eventoB, actual.datos)) {
                    System.out.println("Singularidad encontrada:");
                    System.out.println("Evento C:");
                    System.out.println("Científico: " + actual.cientifico);
                    System.out.println("Datos: " + actual.datos);
                    System.out.println("Eventos relacionados:");
                    while (eventoA != null) {
                        if (eventoA.evento.equals("A") && eventoA.datos == actual.datos) {
                            System.out.println("Evento A:");
                            System.out.println("Científico: " + eventoA.cientifico);
                            System.out.println("Datos: " + eventoA.datos + "\n");
                            break;
                        }
                        eventoA = eventoA.ptrFuturo;
                    }
                }
            }
            actual = actual.ptrFuturo;
        }
    }

    private EventNode encontrarUltimoEventoB() {
        EventNode actual = fin;
        while (actual != null) {
            if (actual.evento.equals("B")) {
                return actual;
            }
            actual = actual.ptrPasado;
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        EventLinkedList listaEventos = new EventLinkedList();
        Random rand = new Random();

        int cantidadEventos = rand.nextInt(20) + 3; // Genera un número aleatorio entre 3 y 22

        for (int i = 0; i < cantidadEventos; i++) {
            String cientifico;
            int datos = rand.nextInt(100) + 1;

            cientifico = (rand.nextBoolean()) ? "Einstein" : "Rosen";

            listaEventos.agregarEventoAlternado(cientifico, datos);
        }

        System.out.println("Lista de eventos:");
        listaEventos.imprimirListaHorizontal(); // Imprime la línea de tiempo horizontalmente

        int cantidadEventosA = listaEventos.contarEventosTipoA();
        int cantidadEventosB = listaEventos.contarEventosTipoB();
        int cantidadEventosC = listaEventos.contarEventosTipoC();

        System.out.println("\nCantidad de eventos tipo A: " + cantidadEventosA);
        System.out.println("Cantidad de eventos tipo B: " + cantidadEventosB);
        System.out.println("Cantidad de eventos tipo C: " + cantidadEventosC);

        System.out.println("\nEventos tipo A:");
        listaEventos.mostrarEventosTipoA();

        System.out.println("\nEventos tipo B:");
        listaEventos.mostrarEventosTipoB();

        System.out.println("\nEventos tipo C:");
        listaEventos.mostrarEventosTipoC();

        System.out.println("\nSingularidades:");
        listaEventos.encontrarSingularidades();
    }
}