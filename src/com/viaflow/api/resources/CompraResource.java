package com.viaflow.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import com.viaflow.api.model.Compra;
import com.viaflow.api.model.Status;
import com.viaflow.api.resources.util.ResponseBuilderControl;
import com.viaflow.dao.DaoCompra;
import com.viaflow.dao.DaoSupplier;

@Path("/compra")
@Produces("application/json")
public class CompraResource {
	@GET
	@Path("/retrieve")
	public Response getCompra(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Compra compra = new DaoCompra().findById(id);
		if (compra != null) {
			resBuilder = Response.ok().entity(compra);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@GET
	@Path("/retrieveall")
	public Response getCompraAll() {
		return ResponseBuilderControl.allowOrigin(Response.ok().entity(new DaoCompra().findAll())).build();
	}

	@POST
	@Path("/insert")
	public Response insert(Compra compra) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoCompra().insert(compra);
		resBuilder = Response.ok().entity(compra);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@PUT
	@Path("/disable")
	public Response disable(@QueryParam("id") int id)  {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoCompra().disable(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
		
	}
	
	@PUT
	@Path("/edit")
	public Response update (Compra compra) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoCompra().update(compra);
		resBuilder = Response.ok().entity(compra);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
}
