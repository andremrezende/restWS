package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;
/**
 * 
 * @author Rezende
 *
 */
@Path("projetos")
public class ProjetoResource {
	@GET
	@Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
	public String busca (@PathParam("id") long id) {
		ProjetoDAO dao = new ProjetoDAO();
		return dao.busca(id).toXML();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		URI uri = URI.create("/projetos/" + projeto.getId());
		
		return Response.created(uri).build();
	}
	
	@DELETE
	@Path("{projetoID}")
	public Response removeProjeto(@PathParam("projetoID") long projetoID) {
		ProjetoDAO dao = new ProjetoDAO();
		Projeto projeto = dao.busca(projetoID);
		dao.remove(projeto.getId());
		return Response.ok().build();
	}
}
