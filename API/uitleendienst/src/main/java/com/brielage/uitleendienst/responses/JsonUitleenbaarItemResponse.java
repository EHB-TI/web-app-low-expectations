package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class JsonUitleenbaarItemResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String    id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String    naam;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Integer   eenheid;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Float     prijs;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Date      periode;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Categorie categorie;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List      uitleenbareItems;

    public JsonUitleenbaarItemResponse (
            final boolean success,
            UitleenbaarItem uitleenbaarItem) {
        super(success);
        this.id        = uitleenbaarItem.getId();
        this.naam      = uitleenbaarItem.getNaam();
        this.eenheid   = uitleenbaarItem.getEenheid();
        this.prijs     = uitleenbaarItem.getPrijs();
        this.periode   = uitleenbaarItem.getPeriode();
        this.categorie = uitleenbaarItem.getCategorie();
    }

    public JsonUitleenbaarItemResponse (
            final boolean success,
            final List uitleenbareItems) {
        super(success);
        this.uitleenbareItems = uitleenbareItems;
    }

    public String getId ()                                        {return id;}

    public void setId (final String id)                           {this.id = id;}

    public String getNaam ()                                      {return naam;}

    public void setNaam (final String naam)                       {this.naam = naam;}

    public Integer getEenheid ()                                  {return eenheid;}

    public void setEenheid (final Integer eenheid)                {this.eenheid = eenheid;}

    public Float getPrijs ()                                      {return prijs;}

    public void setPrijs (final Float prijs)                      {this.prijs = prijs;}

    public Date getPeriode ()                                     {return periode;}

    public void setPeriode (final Date periode)                   {this.periode = periode;}

    public Categorie getCategorie ()                              {return categorie;}

    public void setCategorie (final Categorie categorie)          {this.categorie = categorie;}

    public List getUitleenbareItems ()                            {return uitleenbareItems;}

    public void setUitleenbareItems (final List uitleenbareItems) {this.uitleenbareItems = uitleenbareItems;}
}
