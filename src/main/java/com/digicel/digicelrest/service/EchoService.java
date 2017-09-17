package com.digicel.digicelrest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.digicel.digicelrest.model.ServiceModel;

@Path("/")
public class EchoService {
	
	@POST	
	@Path("{parameter}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public String ping(ServiceModel model){
		return "NOP";
	}

}
