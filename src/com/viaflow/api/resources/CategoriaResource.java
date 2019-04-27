package com.viaflow.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.viaflow.api.model.Categoria;
import com.viaflow.api.model.Status;
import com.viaflow.api.resources.util.ResponseBuilderControl;
import com.viaflow.dao.DaoCategoria;
import com.viaflow.dao.DaoSupplier;

@Path("/categoria")
@Produces("application/json")
public class CategoriaResource {

	@GET
	@Path("/retrieve")
	public Response getCategoria(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Categoria categoria = new DaoCategoria().findById(id);
		if (categoria != null) {
			resBuilder = Response.ok().entity(categoria);
		} else {
			resBuilder = Response.ok().entity(Status.NAOENCONTRADO);
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@GET
	@Path("/retrieveall")
	public Response getCategoriaAll() {
		return ResponseBuilderControl.allowOrigin(Response.ok().entity(new DaoCategoria().findAll())).build();
	}

	@POST
	@Path("/insert")
	public Response insert(Categoria categoria) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoCategoria().insert(categoria);
		resBuilder = Response.ok().entity(categoria);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/disable")
	public Response disable(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoCategoria().disable(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/edit")
	public Response update(Categoria categoria) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoCategoria().update(categoria);
		resBuilder = Response.ok().entity(categoria);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoCategoria().delete(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

}
