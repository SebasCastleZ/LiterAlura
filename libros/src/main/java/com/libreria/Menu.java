package com.libreria;

import com.libreria.convertirDatos.ConvierteDatos;
import com.libreria.conexiones.ConsumoApi;
import com.libreria.libros.Libros;
import com.libreria.libros.categoria.Categoria;
import com.libreria.odts.Datos;
import com.libreria.odts.Datoslibros;
import com.libreria.repositorio.LibrosRepositorio;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;

import java.util.*;

public class Menu {
    //variables
    Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    
    List <Libros> libros = new ArrayList();
    
    //variable del repositorio
    private LibrosRepositorio repositorio;
    //consturctor para inicializar el repositorio 
    
    public Menu(LibrosRepositorio repository) {
        this.repositorio = repository;
    }
    
    int opcion = 0;
    
    
    public void menu () {
        do {
            System.out.println("""
                    1.Buscar libro por titulo
                    2.Listar libros registrados
                    3.listar autores registrados
                    4.listar autores vivos en un determinado año
                    5.listar libros por idioma
                    6.Salir
                    """);
            opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    BuscarLibrosPorTitulo();
                    break;
                case 2:
                    ListarLibrosRegistrados();
                    break;
                    case 3:
                        ListarAutoresRegistrados();
                    break;
                case 4:
                    ListarAutoresEnAnos();
                    break;
                case 5:
                    ListarPorIdiomas();
                    break;

                case 6:
                    opcion = 7;
                    break;

                default:
                    System.out.println("opcion no valido");
                    break;
            }
        } while (opcion != 7);
    }

    private void BuscarLibrosPorTitulo() {
        Scanner teclado = new Scanner(System.in);
        //introducir a el nombre del libro
        System.out.println("Escribe el nombre del libro a buscar ");
        var nombreLibro = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE +"?search="+ nombreLibro.replace(" ","%20"));

        // convertir los datos del record Datos a un Json
        Datos datos =  conversor.obtenerDatos(json, Datos.class);
        //veirifcar que exista el libro buscado
        Optional<Datoslibros> libroBuscado = datos.libros().stream()
                .filter(llamado -> llamado.titulo().toUpperCase().contains(llamado.titulo().toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            // convertir los datos del record Datos a un Json
            var ObtenerDatos = libroBuscado.get();
            System.out.println("Lbro encontrado");
            System.out.println(libroBuscado);
                Libros datosLibros = new Libros(ObtenerDatos);
                repositorio.save(datosLibros);
            // Datoslibros datos = new ();

        }
        else {
            System.out.println("No encontrado");
        }
    }
    //2 funcion del menu
    private void ListarAutoresRegistrados() {
        System.out.println("Escribe el nombre del autor ");
        var nombreAutor = teclado.nextLine();
        Optional<Libros> autores = repositorio.findByTituloContainsIgnoreCase(nombreAutor);
        if (autores.isPresent()) {
            System.out.println(autores.get());
        }else {
            System.out.println("No se encontro el nombre del autor ");
        }
    }
//3 funcion del menu
    private void ListarAutoresEnAnos() {
        System.out.println("Escribe desde que año quieres buscar el autor ");
        double anoAutor = teclado.nextDouble();
        List<Libros> autores = repositorio.librosDeAutoresPorRangoDeAno(anoAutor);
        System.out.println("Titulo del autor del "+ anoAutor + " año para delante: " );
        autores.stream().map(libro -> libro.getTitulo()).forEach(libro -> System.out.println(libro));

    }
    
    
//4 funcion del menu
    private void ListarLibrosRegistrados() {
        libros = repositorio.findAll();
        libros.stream().map(libro -> libro.getTitulo()).forEach(libro -> System.out.println(libro));
    }

    //5 funcion del menu 
    private void ListarPorIdiomas() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escriba en que idioma desea buscar los libros por la siguente nomenclatura");
        System.out.println("EN - ingles \n ES - español \n TL - náhuatl \n");
        var idioma = teclado.nextLine().toUpperCase();
        var categoria = Categoria.fromString(idioma);
        List<Libros> librosPorIdiomas = repositorio.findByIdioma(categoria);
        System.out.println("Series por idioma en:  " + idioma);
        librosPorIdiomas.forEach(System.out::println);
    }

}
