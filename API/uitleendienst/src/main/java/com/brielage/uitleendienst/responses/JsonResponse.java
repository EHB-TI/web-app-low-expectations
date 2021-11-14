package com.brielage.uitleendienst.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public class JsonResponse {
    private final boolean success;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private       Map     errors;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private       Map     data;

    public JsonResponse (final boolean success) {this.success = success;}

    public boolean isSuccess ()                 {return success;}

    public Map getErrors ()                     {return errors;}

    public void setErrors (final Map errors)    {this.errors = errors;}

    public Map getData ()                       {return data;}

    public void setData (final Map data)        {this.data = data;}
}
