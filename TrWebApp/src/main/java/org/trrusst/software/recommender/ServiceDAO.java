package org.trrusst.software.recommender;

/**
 * Created by LG-Admin on 11/17/2014.
 */
public class ServiceDAO {

    String id;
    String name;
    String rating;
    String trustRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTrustRating() {
        return trustRating;
    }

    public void setTrustRating(String trustRating) {
        this.trustRating = trustRating;
    }

}
