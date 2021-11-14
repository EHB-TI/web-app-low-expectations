package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.VerantwoordelijkeMagazijn;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonVerantwoordelijkeMagazijnResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String   id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Persoon  persoon;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Magazijn magazijn;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List     verantwoordelijkeMagazijns;

    public JsonVerantwoordelijkeMagazijnResponse (
            final boolean success,
            VerantwoordelijkeMagazijn verantwoordelijkeMagazijn) {
        super(success);
        this.id       = verantwoordelijkeMagazijn.getId();
        this.persoon  = verantwoordelijkeMagazijn.getPersoon();
        this.magazijn = verantwoordelijkeMagazijn.getMagazijn();
    }

    public JsonVerantwoordelijkeMagazijnResponse (
            final boolean success,
            final List verantwoordelijkeMagazijns) {
        super(success);
        this.verantwoordelijkeMagazijns = verantwoordelijkeMagazijns;
    }

    public String getId ()                                                            {return id;}

    public void setId (final String id)                                               {this.id = id;}

    public Persoon getPersoon ()                                                      {return persoon;}

    public void setPersoon (final Persoon persoon)                                    {this.persoon = persoon;}

    public Magazijn getMagazijn ()                                                    {return magazijn;}

    public void setMagazijn (final Magazijn magazijn)                                 {this.magazijn = magazijn;}

    public List getVerantwoordelijkeMagazijns ()                                      {return verantwoordelijkeMagazijns;}

    public void setVerantwoordelijkeMagazijns (final List verantwoordelijkeMagazijns) {this.verantwoordelijkeMagazijns = verantwoordelijkeMagazijns;}
}
