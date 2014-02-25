package uk.ac.lancaster.scc.data.lab4.datasets;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 * Date / Time : 06/02/2014 / 13:34
 */
public class Dataset implements Serializable {

    String platform;
    String split;
    HashSet<Review> reviews;
    HashSet<String> users;
    HashSet<String> items;

    public Dataset(String platform, String split, HashSet<Review> reviews, HashSet<String> users, HashSet<String> items) {
        this.platform = platform;
        this.split = split;
        this.reviews = reviews;
        this.users = users;
        this.items = items;
    }

    public Dataset() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public HashSet<Review> getReviews() {
        return reviews;
    }

    public void setReviews(HashSet<Review> reviews) {
        this.reviews = reviews;
    }

    public HashSet<String> getUsers() {
        return users;
    }

    public void setUsers(HashSet<String> users) {
        this.users = users;
    }

    public HashSet<String> getItems() {
        return items;
    }

    public void setItems(HashSet<String> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "platform='" + platform + '\'' +
                ", split='" + split + '\'' +
                ", reviews=" + reviews.size() +
                ", users=" + users.size() +
                ", items=" + items.size() +
                '}';
    }
}
