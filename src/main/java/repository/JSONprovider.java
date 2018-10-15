package repository;

import com.google.gson.*;
import model.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class JSONprovider implements Provider {


    public void readFile(List<Movie> movies) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        String input = readJson("src\\main\\resources\\files\\MoviesJSON.json");
        Movie[] json = gson.fromJson(input, Movie[].class);

        for (Movie movie : json) {
            System.out.println(movie);
            movies.add(movie);
        }
    }

   //  metoda wczytującą plik tekstowy i zwracającą go w postaci łańcucha znaków:
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
// serializacja na JSONa
// System.out.println(gson.toJson(movies));
//  Gson gson = new Gson();
//        Date myDate = new Date();
//      //  System.out.println("JSON : " + gson.toJson(myDate));
//        myDate = gson.fromJson("\"Apr 12, 2012 11:56:04 AM\"", Date.class);
//        System.out.println("Date : " + myDate);