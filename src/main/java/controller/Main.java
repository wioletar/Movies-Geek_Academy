package controller;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

     public static void main(String[] args) throws Exception {
        Provider provider;

        Scanner scanner = new Scanner(System.in);
        List<Movie> movies = new ArrayList<Movie>();
        MovieMenu movieMenu = MovieMenu.Z;
        while (movieMenu != MovieMenu.G) {
            readMenu();
            movieMenu = MovieMenu.valueOf(scanner.next());
            switch (movieMenu) {
                case A:
                    provider=new XMLprovider();
                    provider.readFile(movies);
                    break;
                case B:
                    provider=new JSONprovider();
                    provider.readFile(movies);
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

//    private static void readJSONFile(List<Movie> movies) throws Exception {
//        Gson gson = new Gson();
//        String input = readJson("src\\main\\resources\\files\\MoviesJSON.json");
//        Movie[] jsonMovies = gson.fromJson(input, Movie[].class);
//        for (Movie movie : jsonMovies) {
//            System.out.println(movie);
//            movies.add(movie);
//        }
//        // serializacja na JSONa
//        // System.out.println(gson.toJson(movies));
//    }
//
//
//    // metoda wczytującą plik tekstowy i zwracającą go w postaci łańcucha znaków:
//    private static String readJson(String path) throws Exception {
//        StringBuilder builder = new StringBuilder();
//
//        String text;
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(path));
//            while ((text = reader.readLine()) != null) {
//                builder.append(text);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
//    }

}
