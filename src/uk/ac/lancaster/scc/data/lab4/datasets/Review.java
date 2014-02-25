package uk.ac.lancaster.scc.data.lab4.datasets;

import java.util.Date;

/**
 * Author: Matthew Rowe
 * Email: m.rowe@lancaster.ac.uk
 * Date / Time : 06/02/2014 / 13:35
 */
public class Review {

    String userid;
    String itemdid;
    Date time;
    Double score;

    public Review(String userid, String itemdid, Date time, Double score) {
        this.userid = userid;
        this.itemdid = itemdid;
        this.time = time;
        this.score = score;
    }

    public Review() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getItemdid() {
        return itemdid;
    }

    public void setItemdid(String itemdid) {
        this.itemdid = itemdid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userid='" + userid + '\'' +
                ", itemdid='" + itemdid + '\'' +
                ", time=" + time +
                ", score=" + score +
                '}';
    }
}
