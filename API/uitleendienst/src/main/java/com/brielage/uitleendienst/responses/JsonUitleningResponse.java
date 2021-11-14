package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.Organisatie;
import com.brielage.uitleendienst.models.Uitlening;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

public class JsonUitleningResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String      id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Organisatie organisatie;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Magazijn    magazijn;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Date        start;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Date        eind;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Date        teruggebrachtOp;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String      opmerking;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List        uitlenings;

    public JsonUitleningResponse (
            final boolean success,
            Uitlening uitlening) {
        super(success);
        this.id              = uitlening.getId();
        this.organisatie     = uitlening.getOrganisatie();
        this.magazijn        = uitlening.getMagazijn();
        this.start           = uitlening.getStart();
        this.eind            = uitlening.getEind();
        this.teruggebrachtOp = uitlening.getTeruggebrachtOp();
        this.opmerking       = uitlening.getOpmerking();
    }

    public JsonUitleningResponse (
            final boolean success,
            final List uitlenings) {
        super(success);
        this.uitlenings = uitlenings;
    }

    public String getId ()                                      {return id;}

    public void setId (final String id)                         {this.id = id;}

    public Organisatie getOrganisatie ()                        {return organisatie;}

    public void setOrganisatie (final Organisatie organisatie)  {this.organisatie = organisatie;}

    public Magazijn getMagazijn ()                              {return magazijn;}

    public void setMagazijn (final Magazijn magazijn)           {this.magazijn = magazijn;}

    public Date getStart ()                                     {return start;}

    public void setStart (final Date start)                     {this.start = start;}

    public Date getEind ()                                      {return eind;}

    public void setEind (final Date eind)                       {this.eind = eind;}

    public Date getTeruggebrachtOp ()                           {return teruggebrachtOp;}

    public void setTeruggebrachtOp (final Date teruggebrachtOp) {this.teruggebrachtOp = teruggebrachtOp;}

    public String getOpmerking ()                               {return opmerking;}

    public void setOpmerking (final String opmerking)           {this.opmerking = opmerking;}

    public List getUitlenings ()                                {return uitlenings;}

    public void setUitlenings (final List uitlenings)           {this.uitlenings = uitlenings;}
}
