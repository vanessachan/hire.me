package com.bemobi.teste;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vanessa Chan on 14/01/2017.
 */
public class AliasError {
    private String alias;
    @JsonProperty("err_code")
    private String code;
    private String description;

    public AliasError(String alias, String code, String description) {
        this.alias = alias;
        this.code = code;
        this.description = description;
    }

    public AliasError(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
