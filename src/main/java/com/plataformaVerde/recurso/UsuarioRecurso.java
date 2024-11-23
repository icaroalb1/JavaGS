package com.plataformaVerde.recurso;

import com.plataformaVerde.bo.UsuarioBO;
import com.plataformaVerde.modelo.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioRecurso {
    private UsuarioBO usuarioBO;

    public UsuarioRecurso() {
        this.usuarioBO = new UsuarioBO(); // Instância do BO
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvarUsuario(Usuario usuario) {
        try {
            boolean valido = usuarioBO.validarUsuario(usuario);
            if (!valido) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Dados do usuário são inválidos.")
                        .build();
            }
            usuarioBO.salvarUsuario(usuario); // Salva o usuário
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar o usuário: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        try {
            return Response.ok(usuarioBO.listarUsuarios()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar os usuários: " + e.getMessage())
                    .build();
        }
    }
}
