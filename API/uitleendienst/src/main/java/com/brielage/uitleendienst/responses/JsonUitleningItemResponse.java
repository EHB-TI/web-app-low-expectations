package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.models.UitleningItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class JsonUitleningItemResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String          id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Uitlening       uitlening;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private UitleenbaarItem item;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private int             aantal;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Date            teruggebrachtOp;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private int             aantalTeruggebracht;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List            uitleningItems;

    public JsonUitleningItemResponse (
            final boolean success,
            UitleningItem uitleningItem) {
        super(success);
        this.id                  = uitleningItem.getId();
        this.uitlening           = uitleningItem.getUitlening();
        this.item                = uitleningItem.getItem();
        this.aantal              = uitleningItem.getAantal();
        this.teruggebrachtOp     = uitleningItem.getTeruggebrachtOp();
        this.aantalTeruggebracht = uitleningItem.getAantalTeruggebracht();
    }

    public JsonUitleningItemResponse (
            final boolean success,
            final List uitleningItems) {
        super(success);
        this.uitleningItems = uitleningItems;
    }

    public String getId ()                                             {return id;}

    public void setId (final String id)                                {this.id = id;}

    public Uitlening getUitlening ()                                   {return uitlening;}

    public void setUitlening (final Uitlening uitlening)               {this.uitlening = uitlening;}

    public UitleenbaarItem getItem ()                                  {return item;}

    public void setItem (final UitleenbaarItem item)                   {this.item = item;}

    public int getAantal ()                                            {return aantal;}

    public void setAantal (final int aantal)                           {this.aantal = aantal;}

    public Date getTeruggebrachtOp ()                                  {return teruggebrachtOp;}

    public void setTeruggebrachtOp (final Date teruggebrachtOp)        {this.teruggebrachtOp = teruggebrachtOp;}

    public int getAantalTeruggebracht ()                               {return aantalTeruggebracht;}

    public void setAantalTeruggebracht (final int aantalTeruggebracht) {this.aantalTeruggebracht = aantalTeruggebracht;}

    public List getUitleningItems ()                                   {return uitleningItems;}

    public void setUitleningItems (final List uitleningItems)          {this.uitleningItems = uitleningItems;}
}
