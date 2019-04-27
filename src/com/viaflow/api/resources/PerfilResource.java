package com.viaflow.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.viaflow.api.model.Perfil;
import com.viaflow.api.model.Status;
import com.viaflow.api.resources.util.ResponseBuilderControl;
import com.viaflow.dao.DaoPerfil;
import com.viaflow.dao.DaoSupplier;

@Path("/perfil")
@Produces("application/json")
public class PerfilResource {

	@GET
	@Path("/retrieve")
	public Response getPerfi(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Perfil perfil = new DaoPerfil().findById(id);
		if (perfil != null) {
			resBuilder = Response.ok().entity(perfil);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@GET
	@Path("/retrieveall")
	public Response getPerfilAll() {
		return ResponseBuilderControl.allowOrigin(Response.ok().entity(new DaoPerfil().findAll())).build();
	}

	@POST
	@Path("/insert")
	public Response insert(Perfil perfil) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoPerfil().insert(perfil);
		resBuilder = Response.ok().entity(perfil);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/edit")
	public Response update(Perfil perfil) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoPerfil().update(perfil);
		resBuilder = Response.ok().entity(perfil);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/disable")
	public Response disable(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoPerfil().disable(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();

	}

	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoPerfil().delete(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
}
