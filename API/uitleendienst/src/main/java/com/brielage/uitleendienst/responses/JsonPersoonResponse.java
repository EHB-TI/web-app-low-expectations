package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Persoon;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonPersoonResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String voornaam;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String familienaam;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String adres;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String telefoon;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String opmerking;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List   persoons;

    public JsonPersoonResponse (
            final boolean success,
            Persoon persoon) {
        super(success);
        this.id          = persoon.getId();
        this.voornaam    = persoon.getVoornaam();
        this.familienaam = persoon.getFamilienaam();
        this.adres       = persoon.getAdres();
        this.telefoon    = persoon.getTelefoon();
        this.email       = persoon.getEmail();
        this.opmerking   = persoon.getOpmerking();
    }

    public JsonPersoonResponse (
            final boolean success,
            final List persoons) {
        super(success);
        this.persoons = persoons;
    }

    public String getId ()                                {return id;}

    public void setId (final String id)                   {this.id = id;}

    public String getVoornaam ()                          {return voornaam;}

    public void setVoornaam (final String voornaam)       {this.voornaam = voornaam;}

    public String getFamilienaam ()                       {return familienaam;}

    public void setFamilienaam (final String familienaam) {this.familienaam = familienaam;}

    public String getAdres ()                             {return adres;}

    public void setAdres (final String adres)             {this.adres = adres;}

    public String getTelefoon ()                          {return telefoon;}

    public void setTelefoon (final String telefoon)       {this.telefoon = telefoon;}

    public String getEmail ()                             {return email;}

    public void setEmail (final String email)             {this.email = email;}

    public String getOpmerking ()                         {return opmerking;}

    public void setOpmerking (final String opmerking)     {this.opmerking = opmerking;}

    public List getPersoons ()                            {return persoons;}

    public void setPersoons (final List persoons)         {this.persoons = persoons;}
}
