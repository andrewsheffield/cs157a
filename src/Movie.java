/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
class Movie {
    
    String title;
    String imdbID;
    String plot;

    public Movie(String title, String imdbID, String plot) {
        this.title = title;
        this.imdbID = imdbID;
        this.plot =plot;
    }
    
    @Override
    public String toString() {
        return "{imdbID:" + this.imdbID + ", title:" + this.title + ", plot:" + this.plot + "}";
    }
    
}
