package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Rol;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonRolResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String naam;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String omschrijving;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List   rols;

    public JsonRolResponse (
            final boolean success,
            Rol rol) {
        super(success);
        this.id           = rol.getId();
        this.naam         = rol.getNaam();
        this.omschrijving = rol.getOmschrijving();
    }

    public JsonRolResponse (
            final boolean success,
            final List rols) {
        super(success);
        this.rols = rols;
    }

    public String getId ()                                  {return id;}

    public void setId (final String id)                     {this.id = id;}

    public String getNaam ()                                {return naam;}

    public void setNaam (final String naam)                 {this.naam = naam;}

    public String getOmschrijving ()                        {return omschrijving;}

    public void setOmschrijving (final String omschrijving) {this.omschrijving = omschrijving;}

    public List getRols ()                                  {return rols;}

    public void setRols (final List rols)                   {this.rols = rols;}
}
