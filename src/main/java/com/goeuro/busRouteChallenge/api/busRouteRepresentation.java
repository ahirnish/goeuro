package com.goeuro.busRouteChallenge.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class busRouteRepresentation {
    @NotNull
    private int dep_sid;
    @NotNull
    private int arr_sid;
    @NotNull
    private boolean direct_bus_route;

    public busRouteRepresentation() {
        // Jackson deserialization
    }

    @JsonProperty
    public int getDep_sid() {
	return dep_sid;
    }

    @JsonProperty
    public int getArr_sid() {
	return arr_sid;
    }

    @JsonProperty
    public boolean getDirect_bus_route() {
	return direct_bus_route;
    }

    public void setDep_sid( int dep_sid ) {
	this.dep_sid = dep_sid;
    }

    public void setArr_sid( int arr_sid ) {
	this.arr_sid = arr_sid;
    }

    public void setDirect_bus_route( boolean direct_bus_route ) {
	this.direct_bus_route = direct_bus_route;
    }

    public busRouteRepresentation(int dep_sid, int arr_sid, boolean direct_bus_route) {
	this.dep_sid = dep_sid;
	this.arr_sid = arr_sid;
	this.direct_bus_route = direct_bus_route;
    }
}