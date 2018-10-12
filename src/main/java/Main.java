import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.sound.midi.Soundbank;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Movie> movies = new ArrayList<Movie>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            readMenu();
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    case1(movies);
                    break;
                case 2:
                    Gson gson = new Gson();
                    String input = readJson("lists/MoviesJSON.json");
                    Movie[] jsonMovies = gson.fromJson(input, Movie[].class);
                    for (Movie movie : jsonMovies) {
                        System.out.println(movie);

                        movies.add(movie);
                    }
                    // serializacja na JSONa
                    // System.out.println(gson.toJson(movies));

                    break;
                case 3:
                    System.out.println("Podaj rodzaj filmu:");
                    String genre = scanner.next();
                    for (Movie e : movies) {
                        if (e.getGenre().compareTo(genre) == 0)
                            System.out.println(e.getTitle());
                    }
                    break;
                case 4:
                    System.out.println("Podaj nazwisko aktora");
                    String name = scanner.next();
                    for (Movie e : movies) {
                        for (Actor a : e.getListOfActors()) {
                            if (a.getLastName().compareTo(name) == 0)
                                System.out.println(e.getTitle());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Podaj zakres daty od(DD.MM.YYYY):");
                    String dateFromString = scanner.next();
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    Date dateFrom = format.parse(dateFromString);

                    System.out.println("Podaj zakres daty do(DD.MM.YYYY");
                    String dateToString = scanner.next();
                    Date dateTo = format.parse(dateToString);

                    for (Movie e : movies) {
                        if (e.getDateOfProduction().before(dateTo) && e.getDateOfProduction().after(dateFrom))
                            System.out.println(e.getTitle());
                    }
                    break;
                case 6:
                    for (Movie e : movies) {
                        System.out.println(e);
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Podano zły numer");
                    break;
            }
        }

    }

    public static void readMenu() {
        System.out.println("\n");
        System.out.println("Menu:");
        System.out.println("1-wczytaj plik XML");
        System.out.println("2-wczytaj plik JSON");
        System.out.println("3-wyszukanie filmu po gatunku");
        System.out.println("4-wyszukiwanie filmu po nazwisku aktora");
        System.out.println("5-wyszukiwanie filmu po dacie");
        System.out.println("6-wypisz filmy z pamięci");
        System.out.println("7-wyjście");
        System.out.println("Podaj numer z menu ");
    }

    // metoda odczytu pliku XML oraz utworzenia obiektów JAVA i zapisanie ich w paimięci
    private static void case1(List<Movie> movies) {
        try {
            File fXmlFile = new File("lists/MoviesXML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("movie"); //lista filmów

            for (int i = 0; i < nList.getLength(); i++) {

                //<movie> </movie>
                Node nNodeMovie = nList.item(i); //1 film

                Movie movie = new Movie();
                System.out.println("\nCurrent Element :" + nNodeMovie.getNodeName());

                if (nNodeMovie.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNodeMovie; // teraz <movie> jest elementem

                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    System.out.println("Title : " + title);
                    movie.setTitle(title);

                    String directorFirstName = eElement.getElementsByTagName("firstName").item(0).getTextContent();
                    String directorLastName = eElement.getElementsByTagName("lastName").item(0).getTextContent();

                    Director director = new Director(directorFirstName, directorLastName);
                    System.out.println("Director : " + director.getFirstName() + " " + director.getLastName());
                    movie.setDirector(director);

                    String dateString = eElement.getElementsByTagName("dateOfProduction").item(0).getTextContent();
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    Date date = format.parse(dateString);
                    System.out.println("Date of production : " + date);
                    movie.setDateOfProduction(date);

                    String genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
                    System.out.println("Genre : " + genre);
                    movie.setGenre(genre);

                    NodeList actorList = eElement.getElementsByTagName("actor");
                    List<Actor> actorsList = new ArrayList<Actor>();
                    for (int j = 0; j < actorList.getLength(); j++) {
                        Node actorNode = actorList.item(j);
                        if (actorNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element actorElement = (Element) actorNode;
                            Actor actor = new Actor(actorElement.getElementsByTagName("firstName").item(0).getTextContent(), actorElement.getElementsByTagName("lastName").item(0).getTextContent());
                            System.out.println("Aktor : " + actor);
                            actorsList.add(actor);
                        }
                    }
                    movie.setListOfActors(actorsList);
                }
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // metoda wczytującą plik tekstowy i zwracającą go w postaci łańcucha znaków:
    private static String readJson(String path) throws Exception {
        StringBuilder builder = new StringBuilder();

        String text;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((text = reader.readLine()) != null) {
                builder.append(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
