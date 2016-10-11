package com.goeuro.busRouteChallenge;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class busRouteConfiguration extends Configuration {
    // TODO: implement service configuration
    @NotEmpty
    private String path;
    
    @JsonProperty
    public String getPath() {
	if( path == null ) {
	    return "Micro service for GoEuro";
	}
	return path;
    }
    @JsonProperty
    public void setPath(String path) {
	this.path = path;
    }
}
