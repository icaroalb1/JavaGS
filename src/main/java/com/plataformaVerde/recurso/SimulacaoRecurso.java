package com.plataformaVerde.recurso;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

import com.plataformaVerde.bo.SimulacaoBO;
import com.plataformaVerde.modelo.Simulacao;

@Path("/simulacoes")
public class SimulacaoRecurso {

    private SimulacaoBO simulacaoBO;

    public SimulacaoRecurso() {
        this.simulacaoBO = new SimulacaoBO();
    }

    // Endpoint para listar todas as simulações
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSimulacoes() {
        try {
            List<Simulacao> simulacoes = simulacaoBO.listarSimulacoes();
            return Response.ok(simulacoes).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar as simulações: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint para adicionar uma nova simulação
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarSimulacao(Simulacao simulacao) {
        try {
            simulacaoBO.adicionarSimulacao(simulacao);
            return Response.status(Response.Status.CREATED).entity(simulacao).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro nos dados da simulação: " + e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar a simulação: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint para atualizar uma simulação existente
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarSimulacao(@PathParam("id") int id, Simulacao simulacao) {
        try {
            simulacao.setId(id);
            simulacaoBO.adicionarSimulacao(simulacao); // Adiciona ou atualiza a lógica
            return Response.ok(simulacao).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro nos dados da simulação: " + e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar a simulação: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint para excluir uma simulação existente
    @DELETE
    @Path("/{id}")
    public Response excluirSimulacao(@PathParam("id") int id) {
        try {
            simulacaoBO.excluirSimulacao(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir a simulação: " + e.getMessage())
                    .build();
        }
    }
}
