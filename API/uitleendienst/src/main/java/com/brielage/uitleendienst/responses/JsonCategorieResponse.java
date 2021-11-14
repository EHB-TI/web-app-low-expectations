package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Categorie;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JsonCategorieResponse
        extends JsonResponse {
    @JsonInclude (Include.NON_NULL)
    private String id;
    @JsonInclude (Include.NON_NULL)
    private String naam;
    @JsonInclude (Include.NON_NULL)
    private String omschrijving;

    public JsonCategorieResponse (
            final boolean success,
            Categorie categorie) {
        super(success);
        this.id           = categorie.getId();
        this.naam         = categorie.getNaam();
        this.omschrijving = categorie.getOmschrijving();
    }

    public String getId ()                                  {return id;}

    public void setId (final String id)                     {this.id = id;}

    public String getNaam ()                                {return naam;}

    public void setNaam (final String naam)                 {this.naam = naam;}

    public String getOmschrijving ()                        {return omschrijving;}

    public void setOmschrijving (final String omschrijving) {this.omschrijving = omschrijving;}
}
