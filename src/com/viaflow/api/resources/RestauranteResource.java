package com.viaflow.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.viaflow.api.model.Restaurante;
import com.viaflow.api.model.Status;
import com.viaflow.api.resources.util.ResponseBuilderControl;
import com.viaflow.dao.DaoRestaurante;
import com.viaflow.dao.DaoSupplier;

@Path("/restaurante")
@Produces("application/json")
public class RestauranteResource {

	@GET
	@Path("/retrieve")
	public Response getRestaurante(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Restaurante restaurante = new DaoRestaurante().findById(id);
		if (restaurante != null) {
			resBuilder = Response.ok().entity(restaurante);
		} else {
			Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();

	}

	@GET
	@Path("/retrieveall")
	public Response getRestauranteAll() {
		return ResponseBuilderControl.allowOrigin(Response.ok().entity(new DaoRestaurante().findAll())).build();
	}

	@POST
	@Path("/insert")
	public Response insert(Restaurante restaurante) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoRestaurante().insert(restaurante);
		resBuilder = Response.ok().entity(restaurante);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/disable")
	public Response disable(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoRestaurante().disable(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@PUT
	@Path("/edit")
	public Response update(Restaurante restaurante) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoRestaurante().update(restaurante);
		resBuilder = Response.ok().entity(restaurante);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoRestaurante().delete(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
}
