package controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.Actor;
import model.Director;
import model.Movie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import repository.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Main {

     public static void main(String[] args) throws IOException, Exception {
        Provider provider;
        Scanner scanner = new Scanner(System.in);
        List<Movie> movies = new ArrayList<Movie>();
        MovieMenu movieMenu = MovieMenu.Z;
        while (movieMenu != MovieMenu.G) {
            readMenu();
            movieMenu = MovieMenu.valueOf(scanner.next());
            switch (movieMenu) {
                case Z:
                    break;
                case A:
                    provider=new XMLprovider();
                    provider.readFile(movies);
                    break;
                case B:
                    Movie[] movies1;
                    ArrayList<Movie> library;
                    File file = new File("src\\main\\resources\\files\\MoviesJSON.json");
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    library = new ArrayList<Movie>(Arrays.asList(mapper.readValue(file, Movie[].class)));
//
// provider=new JSONprovider();
//                    provider.readFile(movies);
//                    public static void main(String[] args) throws IOException {
//        Movie[] movies;
//        ArrayList<Movie> library;
//        File file = new File("src\\main\\resources\\MoviesJSON.json");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        library = new ArrayList<Movie>(Arrays.asList(mapper.readValue(file, Movie[].class)));
//    }

                    break;
                case C:
                    MenuOptions.genreSearch(movies);
                    break;
                case D:
                    MenuOptions.actorSearch(movies);
                    break;
                case E:
                    MenuOptions.dateSearch(movies);
                    break;
                case F:
                    MenuOptions.moviesPrint(movies);
                    break;
                case G:
                    return;
                default:
                    System.out.println("Podano nieprawidłową wartość z menu");
                    break;
            }
        }

    }


    public static void readMenu() {
        System.out.println("\n");
        System.out.println("Movie Menu:");
        System.out.println("A-wczytaj plik XML");
        System.out.println("B-wczytaj plik JSON");
        System.out.println("C-wyszukanie filmu po gatunku");
        System.out.println("D-wyszukiwanie filmu po nazwisku aktora");
        System.out.println("E-wyszukiwanie filmu po dacie");
        System.out.println("F-wypisz filmy z pamięci");
        System.out.println("G-wyjście");
        System.out.println("Podaj literę z menu ");
    }

}
