package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.CarrinhoDAO;
/**
 * 
 * @author Rezende
 *
 */
@Path("carrinhos")
public class CarrinhoResource {
	@GET
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public String busca (@PathParam("id") long id) {
		CarrinhoDAO dao = new CarrinhoDAO();
		return dao.busca(id).toJSON();
	}
}