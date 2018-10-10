import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {

    private String title;
    private Director director;
    private Date dateOfProduction;
    private String genre;
    private List<Actor> listOfActors= new ArrayList<Actor>();

    public Movie(String title, Director director, Date dateOfProduction, String genre, List<Actor> listOfActors) {
        this.title = title;
        this.director = director;
        this.dateOfProduction = dateOfProduction;
        this.genre = genre;
        this.listOfActors = listOfActors;
    }



}
