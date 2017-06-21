/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Dao.ControlaCasaDAO;
import Models.Evento;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author vfbellaver & EderSMarques
 */
@Path("evento")
public class EventoRecurso {

    //http://localhost:8084/WSRestControlaCasa/evento
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getListaEventos() {
        try {
            System.out.println("BUSCANDO LISTA DE EVENTOS");
            return new ControlaCasaDAO().getListaDeEventos();
        } catch (SQLException ex) {
            System.out.println("ERRO AO BUSCAR LISTA - " + ex.getMessage());
            return null;
        }
    }

    //http://localhost:8084/WSRestControlaCasa/evento/2
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Evento getListaEventoId(@PathParam(value = "id") int id) {
        try {
            System.out.println("BUSCANDO EVENTO ESPECIFICO DE ID " + id);
            return new ControlaCasaDAO().getEventoId(id);
        } catch (SQLException ex) {
            System.out.println("ERRO AO PEGAR UM EVENTO - " + ex.getMessage());
            return null;
        }
    }

    //http://localhost:8084/WSRestControlaCasa/evento
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertEvento(Evento event) {

        try {
            System.out.println("INSERINDO EVENTO COM SEGUINTES INFORMAÇÕES");
            System.out.println("EVENTO");
            System.out.println("Data Hora - " + event.getDataHora());
            System.out.println("Luz Quarto - " + event.isLuzQuarto());
            System.out.println("Luz Sala  - " + event.isLuzSala());
            System.out.println("Ventilador - " + event.isVentilador());
            System.out.println("Televisão - " + event.isTv());
            new ControlaCasaDAO().insertEvento(event);
            return Response.ok("Inserido com Sucesso!!!").build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }

    //http://localhost:8084/WSRestControlaCasa/evento
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEvento(Evento evento) {
        try {
            System.out.println("ATUALIZANDO EVENTO DE ID " + evento.getIdEvento());
            new ControlaCasaDAO().updateEvento(evento);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }

    //http://localhost:8084/WSRestControlaCasa/evento/4
    @DELETE
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteEvento(@PathParam(value = "id") int id) {
        try {
            System.out.println("DELETAR EVENTO DE ID " + id);
            new ControlaCasaDAO().deleteEvento(id);
            return Response.ok().build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }

    }
}
