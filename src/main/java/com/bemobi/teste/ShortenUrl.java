package com.bemobi.teste;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vanessa Chan on 14/01/2017.
 */
@Entity
public class ShortenUrl {
    @Id
    private String alias;
    private String url;
    @Transient
    Map Statistics = new HashMap();

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getStatistics() {
        return Statistics;
    }

    public void setStatistics(Map statistics) {
        Statistics = statistics;
    }
}
