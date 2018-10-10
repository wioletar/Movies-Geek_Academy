import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    List<Movie> movies = new ArrayList<Movie>();


    public static void main(String[] args) throws IOException {

        System.out.println("1-wczytaj plik XML");
        System.out.println("2-wczytaj plik JSON");
        System.out.println("3-wyszukanie filmu");
        System.out.println("4-wyjście");

        System.out.println("Podaj numer z menu ");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    Scanner input = new Scanner(System.in);
                    //String xmlPath = scanner.next();
                    BufferedReader fileReader = null;
                    String line;
                    System.out.println("Podaj ścieżkę do pliku XML");
                    try {
                        fileReader = new BufferedReader(new FileReader(input.next()));
                        line = fileReader.readLine();
                        System.out.println(line);
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    finally {
                        if (fileReader != null) {
                            fileReader.close();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Podaj ścieżkę do pliku JSON");
                    String jsonPath = scanner.next();
                    break;
                case 3:
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Podano zły numer");
                    break;
            }
        }

    }

}
