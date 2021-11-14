package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.BeschikbaarItem;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonBeschikbaarItemResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String          id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private UitleenbaarItem uitleenbaarItem;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Magazijn        magazijn;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private int             aantalTotaal;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private int             aantalBeschikbaar;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private int             aantalGereserveerd;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List            beschikbareItems;


    public JsonBeschikbaarItemResponse (
            final boolean success,
            BeschikbaarItem beschikbaarItem) {
        super(success);
        this.id                 = beschikbaarItem.getId();
        this.uitleenbaarItem    = beschikbaarItem.getUitleenbaarItem();
        this.magazijn           = beschikbaarItem.getMagazijn();
        this.aantalTotaal       = beschikbaarItem.getAantalTotaal();
        this.aantalBeschikbaar  = beschikbaarItem.getAantalBeschikbaar();
        this.aantalGereserveerd = beschikbaarItem.getAantalGereserveerd();
    }

    public JsonBeschikbaarItemResponse (
            final boolean success,
            final List beschikbareItems) {
        super(success);
        this.beschikbareItems = beschikbareItems;
    }

    public String getId ()                                                 {return id;}

    public void setId (final String id)                                    {this.id = id;}

    public UitleenbaarItem getUitleenbaarItem ()                           {return uitleenbaarItem;}

    public void setUitleenbaarItem (final UitleenbaarItem uitleenbaarItem) {this.uitleenbaarItem = uitleenbaarItem;}

    public Magazijn getMagazijn ()                                         {return magazijn;}

    public void setMagazijn (final Magazijn magazijn)                      {this.magazijn = magazijn;}

    public int getAantalTotaal ()                                          {return aantalTotaal;}

    public void setAantalTotaal (final int aantalTotaal)                   {this.aantalTotaal = aantalTotaal;}

    public int getAantalBeschikbaar ()                                     {return aantalBeschikbaar;}

    public void setAantalBeschikbaar (final int aantalBeschikbaar)         {this.aantalBeschikbaar = aantalBeschikbaar;}

    public int getAantalGereserveerd ()                                    {return aantalGereserveerd;}

    public void setAantalGereserveerd (final int aantalGereserveerd)       {this.aantalGereserveerd = aantalGereserveerd;}

    public List getBeschikbareItems ()                                     {return beschikbareItems;}

    public void setBeschikbareItems (final List beschikbareItems)          {this.beschikbareItems = beschikbareItems;}
}
