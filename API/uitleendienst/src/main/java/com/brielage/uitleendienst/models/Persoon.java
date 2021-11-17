package com.brielage.uitleendienst.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable (tableName = "persoon")
public class Persoon {
    private String id;
    private String voornaam;
    private String familienaam;
    private String adres;
    private String telefoon;
    private String email;
    private String opmerking;

    public Persoon () {}

    public Persoon (
            String voornaam,
            String familienaam,
            String adres,
            String telefoon,
            String email,
            String opmerking) {
        this.voornaam    = voornaam;
        this.familienaam = familienaam;
        this.adres       = adres;
        this.telefoon    = telefoon;
        this.email       = email;
        this.opmerking   = opmerking;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId () {
        return id;
    }

    @DynamoDBAttribute
    public void setId (String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getVoornaam () {return voornaam;}

    @DynamoDBAttribute
    public void setVoornaam (final String voornaam) {this.voornaam = voornaam;}

    @DynamoDBAttribute
    public String getFamilienaam () {return familienaam;}

    @DynamoDBAttribute
    public void setFamilienaam (final String familienaam) {this.familienaam = familienaam;}

    @DynamoDBAttribute
    public String getAdres () {
        return adres;
    }

    @DynamoDBAttribute
    public void setAdres (String adres) {
        this.adres = adres;
    }

    @DynamoDBAttribute
    public String getTelefoon () {
        return telefoon;
    }

    @DynamoDBAttribute
    public void setTelefoon (String telefoon) {
        this.telefoon = telefoon;
    }

    @DynamoDBAttribute
    public String getEmail () {
        return email;
    }

    @DynamoDBAttribute
    public void setEmail (String email) {
        this.email = email;
    }

    @DynamoDBAttribute
    public String getOpmerking () {
        return opmerking;
    }

    @DynamoDBAttribute
    public void setOpmerking (String opmerking) {
        this.opmerking = opmerking;
    }

    @Override
    public String toString () {
        return "Persoon{" +
                "id='" + id + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", familienaam='" + familienaam + '\'' +
                ", adres='" + adres + '\'' +
                ", telefoon='" + telefoon + '\'' +
                ", email='" + email + '\'' +
                ", opmerking='" + opmerking + '\'' +
                '}';
    }
}
