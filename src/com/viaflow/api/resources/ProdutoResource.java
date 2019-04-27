package com.viaflow.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.viaflow.api.model.Produto;
import com.viaflow.api.model.Status;
import com.viaflow.api.resources.util.ResponseBuilderControl;
import com.viaflow.dao.DaoProduto;
import com.viaflow.dao.DaoSupplier;

@Path("/produto")
@Produces("application/json")
public class ProdutoResource {
	@GET
	@Path("/retrieve")
	public Response getProduto(@QueryParam("id") int id) {
		Response.ResponseBuilder resBuilder = null;
		Produto produto = new DaoProduto().findById(id);

		if (produto != null) {
			resBuilder = Response.ok().entity(produto);
		} else {
			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
		}
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}

	@GET
	@Path("/retrieveall")
	public Response getProdutoAll() {
		return ResponseBuilderControl.allowOrigin(Response.ok().entity(new DaoProduto().findAll())).build();
	}

	@GET
	@Path("/get")
	public Response getByIdRestaurante(@QueryParam("id") int id) {
		return ResponseBuilderControl.allowOrigin(Response.ok().entity(new DaoProduto().getByIdRestaurante(id))).build();
	}
	
	@POST
	@Path("/insert")
	public Response insert(Produto produto) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoProduto().insert(produto);
		resBuilder = Response.ok().entity(produto);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@PUT
	@Path("/edit")
	public Response update (Produto produto) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoProduto().update(produto);
		resBuilder = Response.ok().entity(produto);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
	
	@PUT
	@Path("/disable")
	public Response disable (@QueryParam ("id") int id ) {
		Response.ResponseBuilder resBuilder = null;
		DaoSupplier.getDaoProduto().disable(id);
		resBuilder = Response.ok().entity(id);
		return ResponseBuilderControl.allowOrigin(resBuilder).build();
	}
}
