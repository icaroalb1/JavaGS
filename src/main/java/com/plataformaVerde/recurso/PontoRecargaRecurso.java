package com.plataformaVerde.recurso;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import com.plataformaVerde.bo.PontoRecargaBO;
import com.plataformaVerde.modelo.PontoRecarga;

@Path("/pontos-recarga") // Define o endpoint base para este recurso
public class PontoRecargaRecurso {

    private PontoRecargaBO bo = new PontoRecargaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PontoRecarga> listar() throws SQLException {
        return bo.listarPontos();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionar(PontoRecarga ponto) {
        try {
            bo.adicionarPonto(ponto);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(PontoRecarga ponto) {
        try {
            bo.atualizarPonto(ponto);
            return Response.ok().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {
        try {
            bo.excluirPonto(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
