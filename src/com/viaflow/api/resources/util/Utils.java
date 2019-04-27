//package com.viaflow.api.resources.util;
//
//import javax.ws.rs.core.Response;
//
//import com.viaflow.api.model.Status;
//import com.viaflow.api.model.Usuario;
//import com.viaflow.dao.DaoSupplier;
//import com.viaflow.dao.DaoUsuario;
//
//public class Utils {
//	
////	private Object user = new DaoUsuario();
//	
//	public Response findById(DaoSupplier dao, int id){
//		Response.ResponseBuilder resBuilder = null;
//		Usuario usuario = new DaoUsuario().findById(id);
//		if (usuario != null) {
//			resBuilder = Response.ok().entity(usuario);
//		} else {
//			resBuilder = Response.ok().entity(new Status(Status.NAOENCONTRADO));
//		}
//		return ResponseBuilderControl.allowOrigin(resBuilder).build();
//	}
//}