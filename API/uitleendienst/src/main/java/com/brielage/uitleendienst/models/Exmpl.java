package com.brielage.uitleendienst.models;

public class Exmpl {
    private String id;
    private String name;
    private String test;

    public Exmpl () {}

    public Exmpl (
            final String id,
            final String name,
            final String test) {
        this.id   = id;
        this.name = name;
        this.test = test;
    }

    public Exmpl (
            final String name,
            final String test) {
        this.name = name;
        this.test = test;
    }

    public String getId ()                  {return id;}

    public void setId (final String id)     {this.id = id;}

    public String getName ()                {return name;}

    public void setName (final String name) {this.name = name;}

    public String getTest ()                {return test;}

    public void setTest (final String test) {this.test = test;}
}
