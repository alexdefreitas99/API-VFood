package com.viaflow.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.viaflow.api.model.Status;
import com.viaflow.api.model.Usuario;
import com.viaflow.api.resources.util.ResponseBuilderControl;
import com.viaflow.dao.DaoSupplier;
import com.viaflow.dao.DaoUsuario;

@Path("/usuario")
@Produces("application/json")
public class UsuarioResource {

	@GET
	@Path("/retrieve")
	public Response getUsuario(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Usuario usuario = new DaoUsuario().findById(id);
		if (usuario != null) {
			resBuilder = Response.ok().entity(usuario);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@GET
	@Path("/retrieveall")
	public Response getUsuarioAll() {
		return ResponseBuilderControl.allowOrigin(Response.ok().entity(new DaoUsuario().findAll())).build();
	}

	@POST
	@Path("/insert")
	public Response insert(Usuario usuario) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoUsuario().insert(usuario);
		resBuilder = Response.ok().entity(usuario);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/disable")
	public Response disable(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoUsuario().disable(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@PUT
	@Path("/edit")
	public Response update(Usuario usuario) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoUsuario().update(usuario);
		resBuilder = Response.ok().entity(usuario);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoUsuario().delete(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
}
