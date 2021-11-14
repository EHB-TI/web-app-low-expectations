package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.Rol;
import com.brielage.uitleendienst.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonUserResponse
        extends JsonResponse {
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String  id;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String  username;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private String  password;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Rol     rol;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Persoon persoon;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List    users;

    public JsonUserResponse (
            final boolean success,
            User user) {
        super(success);
        this.id       = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.rol      = user.getRol();
        this.persoon  = user.getPersoon();
    }

    public JsonUserResponse (
            final boolean success,
            final List users) {
        super(success);
        this.users = users;
    }

    public String getId ()                          {return id;}

    public void setId (final String id)             {this.id = id;}

    public String getUsername ()                    {return username;}

    public void setUsername (final String username) {this.username = username;}

    public String getPassword ()                    {return password;}

    public void setPassword (final String password) {this.password = password;}

    public Rol getRol ()                            {return rol;}

    public void setRol (final Rol rol)              {this.rol = rol;}

    public Persoon getPersoon ()                    {return persoon;}

    public void setPersoon (final Persoon persoon)  {this.persoon = persoon;}

    public List getUsers ()                         {return users;}

    public void setUsers (final List users)         {this.users = users;}
}
