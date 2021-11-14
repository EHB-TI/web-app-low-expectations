package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Magazijn;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonMagazijnResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String naam;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String adres;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String telefoon;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String opmerking;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List   magazijns;

    public JsonMagazijnResponse (
            final boolean success,
            Magazijn magazijn) {
        super(success);
        this.id        = magazijn.getId();
        this.naam      = magazijn.getNaam();
        this.adres     = magazijn.getAdres();
        this.telefoon  = magazijn.getTelefoon();
        this.email     = magazijn.getEmail();
        this.opmerking = magazijn.getOpmerking();
    }

    public JsonMagazijnResponse (
            final boolean success,
            final List magazijns) {
        super(success);
        this.magazijns = magazijns;
    }

    public String getId ()                            {return id;}

    public void setId (final String id)               {this.id = id;}

    public String getNaam ()                          {return naam;}

    public void setNaam (final String naam)           {this.naam = naam;}

    public String getAdres ()                         {return adres;}

    public void setAdres (final String adres)         {this.adres = adres;}

    public String getTelefoon ()                      {return telefoon;}

    public void setTelefoon (final String telefoon)   {this.telefoon = telefoon;}

    public String getEmail ()                         {return email;}

    public void setEmail (final String email)         {this.email = email;}

    public String getOpmerking ()                     {return opmerking;}

    public void setOpmerking (final String opmerking) {this.opmerking = opmerking;}

    public List getMagazijns ()                       {return magazijns;}

    public void setMagazijns (final List magazijns)   {this.magazijns = magazijns;}
}
