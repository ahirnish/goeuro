package com.goeuro.busRouteChallenge.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.jersey.params.IntParam;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Collections;
import java.io.*;

import com.goeuro.busRouteChallenge.api.busRouteRepresentation;

@Path("/api/direct")
@Produces(MediaType.APPLICATION_JSON)
public class busRouteResource {
    private final String path;
    public busRouteResource(String path) {
	this.path = path;
    }

    public static boolean exist( ArrayList<ArrayList<Integer>> a, int x, int y){
        boolean res = false;
	if(a.get(x).size() == 0 || a.get(y).size() == 0 ){
	    return false;
	}
	if( x == y ) {
	    return true;
	}
	ArrayList<Integer> did = a.get(x);
	ArrayList<Integer> sid = a.get(y);
	Collections.sort(did);
	Collections.sort(sid);
	for( int i=0,j=0;i<did.size() && j<sid.size();) {
	    if( did.get(i) == sid.get(j)){
		return true;
	    } else if(did.get(i) > sid.get(j)){
		j++;
		
	    } else {
		i++;
	    }
	}
	//res = !(Collections.disjoint( did, sid ));
        return false;
    }
    
    @GET
    @Timed
    public busRouteRepresentation sayHello(@QueryParam("dep_sid") IntParam 
					   param1, @QueryParam("arr_sid") IntParam param2) throws java.io.FileNotFoundException{
	final String fileName = path;
	ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
	for( int i = 0;i < 1000001; i++){
	    a.add(new ArrayList<Integer>() );
	}
        Scanner input = new Scanner(new File(fileName));
        int testCount = 0;
        int count = 1;
	int dep_sid=param1.get(), arr_sid=param2.get();
        while(input.hasNextLine())
	{
	    Scanner colReader = new Scanner(input.nextLine());
	    if(count==1){
		testCount = colReader.nextInt();
	    } else {
		int c = 1;
		int routeId = -1;
		int station;
		while(colReader.hasNextInt())
		    {
			if( c == 1){
			    routeId = colReader.nextInt();
			}
			else {
			    station = colReader.nextInt();
			    a.get( station ).add( routeId );
			}
			c++;
		    }
	    }
	    count++;
	}
	return new busRouteRepresentation(dep_sid, arr_sid, exist(a,dep_sid,arr_sid));
    }
}