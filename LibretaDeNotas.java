package Interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibretaDeNotas {


    public static int cantidadAlumnos = -1;
    public static int cantidadNotas = -1;

    //crear un Hashmap que almacene un string como nombre de estudiante
    //y un ArrayList de Double
    public static HashMap<String, ArrayList<Double>> registroDeNotas = new HashMap<String, ArrayList<Double>>();

    //Variables globales para imprimir texto por consola en colores usando secuencias de escape ANSI (N -> Negrita I -> Intenso)
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_blanco_I = "\033[0;97m";
    public static final String ANSI_rojo_NI = "\033[1;91m";
    public static final String ANSI_verde_NI = "\033[1;92m";
    public static final String ANSI_amarillo_NI = "\033[1;93m";
    public static final String ANSI_cian_NI = "\033[1;96m";



    public static void main(String[] args)
    {

        IngresoDeNotas();

        Menu();

    }




    //Funcion retorna la nota maxima del estudiante, se tiene que vereficar antes que el estudiante existe
    public static Double CalcularNotaMaxima(Map<String, ArrayList<Double>> regNotas, String nombreEstudiante)
    {
        double notaMaxima = Integer.MIN_VALUE;


        ArrayList<Double> notas = regNotas.get(nombreEstudiante);

        for (double nota : notas)
        {
            if (nota > notaMaxima)
            {
                notaMaxima = nota;
            }
        }

        return notaMaxima;
    }

    //Funcion retorna la nota minima del estudiante, se tiene que vereficar antes que el estudiante existe
    public static Double CalcularNotaMinima(Map<String, ArrayList<Double>> regNotas, String nombreEstudiante)
    {
        double notaMinima = Integer.MAX_VALUE;


        ArrayList<Double> notas = regNotas.get(nombreEstudiante);

        for (double nota : notas)
        {
            if (nota > notaMinima)
            {
                notaMinima = nota;
            }
        }

        return notaMinima;
    }

    //Calcula el promedio en el registro de notas del estudiante entregado como argumento, Hay que verificar que el estudiante exista en el registro antes
    public static Double CalcularPromedioEstudiante(Map<String, ArrayList<Double>> regNotas, String nombreEstudiante)
    {
        double suma = 0;
        double promedioEstudiante = 0;

        ArrayList<Double> notas = regNotas.get(nombreEstudiante);

        for (double nota : notas)
        {
            suma += nota;
        }
        promedioEstudiante = suma / cantidadNotas;
        promedioEstudiante = Math.round(promedioEstudiante * 10) / 10.0;
        return promedioEstudiante;
    }

    //Calcula el promedio de notas de todos los estudiantes enlos registros
    public static Double CalcularPromedioCurso(Map<String, ArrayList<Double>> regNotas)
    {
        double sumatotal = 0;
        double promedioTotal = 0;
        for (Map.Entry<String, ArrayList<Double>> registro : regNotas.entrySet())
        {

            ArrayList<Double> notas = registro.getValue();

            for (double nota : notas)
            {
                sumatotal += nota;


            }


        }
        promedioTotal = sumatotal / (cantidadAlumnos * cantidadNotas);
        promedioTotal = Math.round(promedioTotal * 10) / 10.0;
        return promedioTotal;
    }

    //Retorna el indice de la nota indicado de un estudiante en el registro de notas
    public static Double RetornarNotaEstudiante(Map<String, ArrayList<Double>> regNotas, String nombreEstudiante, int indiceNota)
    {
        ArrayList<Double> notas = regNotas.get(nombreEstudiante);
        return notas.get(indiceNota);
    }

    //Imprime todos los estudiantes del Registro de notas
    public static void ImprimirEstudiantes(Map<String, ArrayList<Double>> regEstudiantes)
    {
        for (Map.Entry<String, ArrayList<Double>> registro : regEstudiantes.entrySet()){
            System.out.print(registro.getKey()+ " | ");
        }
        System.out.println("\n");
    }

    public static void Menu()
    {
        Scanner entrada = new Scanner(System.in);
        int option = -1;
        boolean menuActivado = true;

        while (menuActivado)
        {

            ImprimirMenu();

            if (entrada.hasNextInt()){
                option = entrada.nextInt();
                switch (option){
                    case 1:
                        Menu_opcion_1();
                        break;
                    case 2:
                        Menu_opcion_2();
                        break;
                    case 3:
                        Menu_opcion_3();
                        break;
                    case 4:
                        menuActivado = false;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }


            } else {
                System.out.println("¡Ingrese un Numero!\n");
                entrada.next();
            }

        }


        entrada.close();
    }

    private static void Menu_opcion_1()
    {
        boolean MenuActivado = true;
        while (MenuActivado)
        {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Ingrese Nombre del estudiante");
            ImprimirEstudiantes(registroDeNotas);
            String nombre = entrada.nextLine();
            if (registroDeNotas.containsKey(nombre))
            {
                System.out.println("Promedio de estudiante " + nombre + ":\n " + CalcularPromedioEstudiante(registroDeNotas, nombre));
                MenuActivado = false;
            } else {
                System.out.println("Estudiante no se encuentra en los registros");
            }

        }
    }

    private static void Menu_opcion_2()
    {
        boolean MenuActivado = true;
        while (MenuActivado) {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Ingrese Nombre del estudiante");
            ImprimirEstudiantes(registroDeNotas);
            String nombre = entrada.nextLine();
            if (registroDeNotas.containsKey(nombre))
            {
                ArrayList<Double> notas = registroDeNotas.get(nombre);
                System.out.println(ANSI_blanco_I + "\nNotas Aprobadas y Reprobadas del estudiante");
                for(int i = 1; i <= cantidadNotas; i++){
                    System.out.print("N°"+ i + " | ");
                }
                System.out.print("\n");
                for (double nota : notas)
                {
                    if (nota >= 4)
                    {
                        System.out.print(ANSI_verde_NI + nota + " | ");
                    }
                    else
                    {
                        System.out.print(ANSI_rojo_NI + nota + " | ");
                    }
                }

                System.out.println("\n" + ANSI_RESET);

                MenuActivado = false;
            } else {
                System.out.println("Estudiante no se encuentra en los registros");
            }


        }
    }
    private static void Menu_opcion_3()
    {
        boolean MenuActivado = true;
        while (MenuActivado) {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Ingrese Nombre del estudiante\n");
            ImprimirEstudiantes(registroDeNotas);
            String nombre = entrada.nextLine();
            if (registroDeNotas.containsKey(nombre))
            {
                double promedioCurso = CalcularPromedioCurso(registroDeNotas);

                String cadenaSimbolos = "";

                ArrayList<Double> notas = registroDeNotas.get(nombre);
                System.out.println(ANSI_blanco_I + "\nNotas sobre el Promedio del curso");
                for(int i = 1; i <= cantidadNotas; i++){
                    System.out.print("N°"+ i + " | ");
                }
                System.out.println("NP");
                for (double nota : notas)
                {
                    if (nota > promedioCurso)
                    {
                        System.out.print(ANSI_cian_NI + nota + " | ");
                        cadenaSimbolos += "  >  |";
                    }
                    else if (nota < promedioCurso)
                    {
                        System.out.print(ANSI_amarillo_NI + nota + " | ");
                        cadenaSimbolos += "  <  |";
                    } else {
                        System.out.print(ANSI_verde_NI + nota + " | ");
                        cadenaSimbolos += "  =  |";
                    }
                }
                System.out.print(ANSI_blanco_I + CalcularPromedioCurso(registroDeNotas));
                System.out.print("\n"+cadenaSimbolos);
                System.out.println("\n" + ANSI_RESET);

                MenuActivado = false;
            } else {
                System.out.println("Estudiante no se encuentra en los registros");
            }


        }


    }




    //Imprime por consola el formato del menu
    public static void ImprimirMenu()
    {
        System.out.println("Menu Sistema Libreta de Notas");
        System.out.println("1. Mostrar Premedio de Notas");
        System.out.println("2. Mostrar notas Aprobadas/Reprobadas y Maximo/minimo");
        System.out.println("3. Comparar Notas con el promedio del curso");
        System.out.println("4. Salir");
    }

    //El ingreso de notas
    public static void IngresoDeNotas(){
        Scanner sc = new Scanner(System.in);

        //pedirle al usuario que ingrese cantidad de estudiantes y cantidad de notas por alumno
        //Indica si no se ingresa un numero e indica si se ingreso una cantidad negativa

        while (!(cantidadAlumnos > 0)){
            System.out.println("Ingrese cantidad de estudiantes: ");
            if (sc.hasNextInt()){
                cantidadAlumnos = sc.nextInt();
                if (cantidadAlumnos <= 0){
                    System.out.println("Ingrese una cantidad mayor que 0");
                }
            } else {
                System.out.println("Dato no valido");
                sc.next();
            }

        }

        while (!(cantidadNotas > 0)){
            System.out.println("Ingrese cantidad de notas: ");
            if (sc.hasNextInt()){
                cantidadNotas = sc.nextInt();
                if (cantidadNotas <= 0){
                    System.out.println("Ingrese una cantidad mayor que 0");
                }
            } else {
                System.out.println("Dato no valido");
                sc.next();
            }

        }

        sc.nextLine(); //despues de un nextInt hay que poner nextLine si quiero usar un nextLine

        //Captura todas las notas por cantidad de estudiante ( pregunta cantidadAlumnos veces el nombre, y por cada uno
        //pide cantidadNotas notas

        String nombre = "";

        for (int i = 1; i <= cantidadAlumnos; i++)
        {

            //Capturar/obtener el Nombre del estudiante
            System.out.println("Ingrese nombre del Alumno " + i + ": ");
            nombre = sc.nextLine();

            //Crea arrayList (de manera local) para almacenar las notas de cada estudiante por separado
            ArrayList<Double> notas = new ArrayList<>();

            for (int j = 1; j <= cantidadNotas; j++)
            {
                Double nota = (double) -1;
                while (nota < 1 || nota > 7)
                {


                    System.out.println("Ingrese nota numero " + j + " de "+ nombre + " :");

                    //Capturar/Obtener la Notas del estudiante con verificacion de datos
                    //Informa si se ingresa algo que no es un numero, informa si se ingresa una nota no valida en el sistema
                    // 1,0 a 7,0
                    if (sc.hasNextDouble())
                    {
                        nota = sc.nextDouble();

                        if (nota < 1 || nota > 7)
                        {
                            System.out.println("Ingrese nota Valida entre 1,0 a 7,0:");
                        }

                    } else {
                        System.out.println("Ingrese un valor valido\n");
                        sc.next();
                    }


                }
                //Añadir la nota al ArrayList
                nota = Math.round(nota * 10) / 10.0;
                notas.add(nota);
            }
            //Despues de Recolectar el nombre del estudiante como <String> y sus notas como un ArrayList<Double>
            //Los agregamos al registro de notas
            registroDeNotas.put(nombre, notas);
            sc.nextLine();
        }


    }



}
