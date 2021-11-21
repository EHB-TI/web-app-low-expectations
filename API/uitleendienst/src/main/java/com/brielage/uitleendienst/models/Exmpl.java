package com.brielage.uitleendienst.models;

import java.util.Objects;

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

    @Override
    public boolean equals (final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Exmpl exmpl = (Exmpl) o;
        return Objects.equals(id, exmpl.id)
                && Objects.equals(name,
                                  exmpl.name)
                && Objects.equals(test,
                                  exmpl.test);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id, name, test);
    }
}
