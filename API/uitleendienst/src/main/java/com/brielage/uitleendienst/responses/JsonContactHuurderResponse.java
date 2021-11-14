package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.ContactHuurder;
import com.brielage.uitleendienst.models.Organisatie;
import com.brielage.uitleendienst.models.Persoon;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonContactHuurderResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String      id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Persoon     persoon;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Organisatie organisatie;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String      opmerking;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List        contactHuurders;

    public JsonContactHuurderResponse (
            final boolean success,
            ContactHuurder contactHuurder) {
        super(success);
        this.id          = contactHuurder.getId();
        this.persoon     = contactHuurder.getPersoon();
        this.organisatie = contactHuurder.getOrganisatie();
        this.opmerking   = contactHuurder.getOpmerking();
    }

    public JsonContactHuurderResponse (
            final boolean success,
            final List contactHuurders) {
        super(success);
        this.contactHuurders = contactHuurders;
    }

    public String getId ()                                      {return id;}

    public void setId (final String id)                         {this.id = id;}

    public Persoon getPersoon ()                                {return persoon;}

    public void setPersoon (final Persoon persoon)              {this.persoon = persoon;}

    public Organisatie getOrganisatie ()                        {return organisatie;}

    public void setOrganisatie (final Organisatie organisatie)  {this.organisatie = organisatie;}

    public String getOpmerking ()                               {return opmerking;}

    public void setOpmerking (final String opmerking)           {this.opmerking = opmerking;}

    public List getContactHuurders ()                           {return contactHuurders;}

    public void setContactHuurders (final List contactHuurders) {this.contactHuurders = contactHuurders;}
}
