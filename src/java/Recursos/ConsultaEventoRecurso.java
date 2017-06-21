/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Dao.ControlaCasaDAO;
import Models.Evento;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("consultaevento")
public class ConsultaEventoRecurso {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{datahora}")
    public Evento getEvento(@PathParam(value = "datahora") Long valor) {
        try {
            Timestamp ts = new Timestamp(valor);
            System.out.println("CONSULTANDO EVENTO A SER EXECUTANDO NA SEGUINTE DATA "+ts);
            return new ControlaCasaDAO().verificarAgenda(ts);  
        } catch (SQLException ex) {
            System.out.println("ERRO AO BUSCAR EVENTO PARA EXECUTAR "+ex.getMessage());
            return null;
        } catch (ParseException ex) {
            System.out.println("ERRO AO BUSCAR EVENTO PARA EXECUTAR "+ex.getMessage());
            return null;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateStatus(Evento evento) {
        System.out.println("ATUALIZANDO STATUS DO EVENTO "+evento.getIdEvento()+" PARA EXECUTADO ");
        try {
            new ControlaCasaDAO().atualizarStatus(evento);
        } catch (SQLException ex) {
            System.out.println("ERRO AO ATUALIZAR STATUS EVENTO - "+ex.getMessage());
        }
    }
}
