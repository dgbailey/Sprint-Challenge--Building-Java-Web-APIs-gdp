package com.lambdaschool.sprint.gdp.gdp.Models;

import java.util.concurrent.atomic.AtomicLong;

public class GDP
{
    private static AtomicLong counter = new AtomicLong() ;
    private Long id;
    String name;
    String gdp;

    public GDP(String name, String gdp)
    {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.gdp = gdp;
    }

    public Integer getGdpInt()
    {
        return Integer.valueOf(gdp);
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGdp() {
        return gdp;
    }

    public void setGdp(String gdp) {
        this.gdp = gdp;
    }

    @Override
    public String toString() {
        return "GDP{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gdp=" + gdp +
                '}';
    }


}
