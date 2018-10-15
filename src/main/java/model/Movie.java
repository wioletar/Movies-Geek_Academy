package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import model.Actor;

import java.util.Date;
import java.util.List;

public class Movie {

    private String title;
    private Director director;
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy");
    private Date dateOfProduction;
    private String genre;
    private List<Actor> listOfActors;
  //  private Object shape;

    public Movie() {
    }

    public Movie(String title, Director director, Date dateOfProduction, String genre, List<Actor> listOfActors) {
        this.title = title;
        this.director = director;
        this.dateOfProduction = dateOfProduction;
        this.genre = genre;
        this.listOfActors = listOfActors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Actor> getListOfActors() {
        return listOfActors;
    }

    public void setListOfActors(List<Actor> listOfActors) {
        this.listOfActors = listOfActors;
    }

    @Override
    public String toString() {
        return title + '\'' + director+ '\''+ dateOfProduction + '\'' + genre + '\'' +listOfActors;
    }
}
