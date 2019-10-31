package com.ranjith.CollinearPoints;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ranjith.CollinearPoints.Model.Line;
import com.ranjith.CollinearPoints.Model.Point;
import com.ranjith.CollinearPoints.Service.PlaneRestService;
/**
 * @author Ranjith
 *
 */
/**
 * Root resource (exposed at "plane" path)
 */

@Path("plane")
public class Collinear {

	PlaneRestService planeRest = new PlaneRestService();
     
	//To get all the points in the space 
    @GET
    @Path("/space")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Point> getPoints(){
    	return planeRest.getAllPointsInPlane() ;
    }
    
    //To get lines with at least n points
    @GET
    @Path("/lines/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Line> getLines(@PathParam("n") int n){
    	return planeRest.getAllLinesOfSize(n) ;
    }
    
    
    //To add a point in space
    @POST
    @Path("/point")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String setPoints(Point pt){
    	
    	   	
    	planeRest.addPointToPlane(pt);
    	return "point added to plane" ;
    }
    
   
    //To remove all the points in space
    @DELETE
    @Path("/space")
    @Produces(MediaType.APPLICATION_JSON)
    public String removePoints() {
    	
    	return planeRest.removeAllPointsInSpace();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
