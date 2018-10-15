package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import model.Movie;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class JSONprovider implements Provider {


//    public T deserializeFromJson(File jsonFile) {
//        T object = null;
//
//        try {
//            object = OBJECT_MAPPER.readValue(jsonFile, objectType);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return object;
//    }



    public void readFile(List<Movie> movies) throws Exception {

        File file = new File("src\\main\\resources\\files\\MoviesJSON.json");

        Movie[] movie1=null;
        ObjectMapper mapper = new ObjectMapper();
        try{

            movie1=mapper.readValue(file, Movie[].class);
            for (Movie movie : movie1) {
                System.out.println(movie);
                movies.add(movie);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        //        ObjectMapper mapper = new ObjectMapper();
//         Movie[] json = mapper.readValue(file, Movie[].class);



   // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
//        String input = readJson("src\\main\\resources\\files\\MoviesJSON.json");
//        Movie[] json = gson.fromJson(input, Movie[].class);

//        for (Movie movie : json) {
//            System.out.println(movie);
//           //movies.add(movie);
//        }
    }
    //  metoda wczytującą plik tekstowy i zwracającą go w postaci łańcucha znaków:
    private static String readJson(String path) throws IOException {
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
