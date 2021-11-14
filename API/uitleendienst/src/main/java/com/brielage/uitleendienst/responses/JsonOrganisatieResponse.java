package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Organisatie;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonOrganisatieResponse
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
    private List   organisaties;

    public JsonOrganisatieResponse (
            final boolean success,
            Organisatie organisatie) {
        super(success);
        this.id        = organisatie.getId();
        this.naam      = organisatie.getNaam();
        this.adres     = organisatie.getAdres();
        this.telefoon  = organisatie.getTelefoon();
        this.email     = organisatie.getEmail();
        this.opmerking = organisatie.getOpmerking();
    }

    public JsonOrganisatieResponse (
            final boolean success,
            final List organisaties) {
        super(success);
        this.organisaties = organisaties;
    }

    public String getId ()                                {return id;}

    public void setId (final String id)                   {this.id = id;}

    public String getNaam ()                              {return naam;}

    public void setNaam (final String naam)               {this.naam = naam;}

    public String getAdres ()                             {return adres;}

    public void setAdres (final String adres)             {this.adres = adres;}

    public String getTelefoon ()                          {return telefoon;}

    public void setTelefoon (final String telefoon)       {this.telefoon = telefoon;}

    public String getEmail ()                             {return email;}

    public void setEmail (final String email)             {this.email = email;}

    public String getOpmerking ()                         {return opmerking;}

    public void setOpmerking (final String opmerking)     {this.opmerking = opmerking;}

    public List getOrganisaties ()                        {return organisaties;}

    public void setOrganisaties (final List organisaties) {this.organisaties = organisaties;}
}
