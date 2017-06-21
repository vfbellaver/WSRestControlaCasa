/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import ConnectionDB.ConnectionFactory;
import Models.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vfbellaver & EderSMarques
 */
public class ControlaCasaDAO {

    /**
     * Busca uma lista de EVENTOS no BD
     *
     * @return List de Eventos
     */
    public List<Evento> getListaDeEventos() throws SQLException {
        List<Evento> lista = new LinkedList<Evento>();
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from agenda");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Evento ev = new Evento();
            ev.setIdEvento(rs.getInt("idEvento"));
            ev.setDataHora(rs.getTimestamp("dataHora"));
            ev.setLuzQuarto(rs.getBoolean("luzQuarto"));
            ev.setLuzSala(rs.getBoolean("luzSala"));
            ev.setTv(rs.getBoolean("tv"));
            ev.setVentilador(rs.getBoolean("ventilador"));
            ev.setStatus(rs.getBoolean("status"));
            lista.add(ev);
        }
        return lista;
    }

    /**
     * Recebe um id por parâmtro e busca um evento com esse ID
     *
     * @param id
     * @return Retorna um objeto do tipo EVENTO
     */
    public Evento getEventoId(int id) throws SQLException {
        Evento ev = null;
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from agenda where idEvento=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ev = new Evento();
            ev.setIdEvento(rs.getInt("idEvento"));
            ev.setDataHora(rs.getTimestamp("dataHora"));
            ev.setLuzQuarto(rs.getBoolean("luzQuarto"));
            ev.setLuzSala(rs.getBoolean("luzSala"));
            ev.setTv(rs.getBoolean("tv"));
            ev.setVentilador(rs.getBoolean("ventilador"));
            ev.setStatus(rs.getBoolean("status"));
        }
        return ev;
    }

    /**
     * Recebe um objeto do tipo EVENTO e atualiza o objeto no BD através do ID
     * desse objeto
     *
     * @param Evento ev
     */
    public void updateEvento(Evento ev) throws SQLException {

        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE agenda SET luzQuarto=?, luzSala=?, tv=?, "
                + "ventilador=?, dataHora=?  where idEvento=?");

        stmt.setBoolean(1, ev.isLuzQuarto());
        stmt.setBoolean(2, ev.isLuzSala());
        stmt.setBoolean(3, ev.isTv());
        stmt.setBoolean(4, ev.isVentilador());
        stmt.setTimestamp(5, ev.getDataHora());
        stmt.setInt(6, ev.getIdEvento());

        stmt.execute();

    }

    /**
     * Insere um novo evento no BANCO de Dados
     *
     * @param Evento ev
     */
    public String insertEvento(Evento event) throws SQLException {
        String retorno;
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO agenda (ventilador,luzSala,tv,luzQuarto,dataHora) "
                + "VALUES (?,?,?,?,?)");
        stmt.setBoolean(1, event.isVentilador());
        stmt.setBoolean(2, event.isLuzSala());
        stmt.setBoolean(3, event.isTv());
        stmt.setBoolean(4, event.isLuzQuarto());
        stmt.setTimestamp(5, event.getDataHora());
        stmt.execute();
        retorno = "EVENTO CADASTRADO COM SUCESSO";
        return retorno;
    }

    /**
     * Delete um evento do BD
     *
     * @param id
     */
    public void deleteEvento(int id) throws SQLException {
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE from agenda where idEvento=?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    /**
     * Atualiza o evento se ele foi executado.
     *
     * @param id
     * @param status
     */
    public void atualizarStatus(Evento evento) throws SQLException {
        System.out.println("STATUS EVENTO ID = " + evento.getIdEvento() + " STATUS = " + evento.isStatus());
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE agenda SET status=? where idEvento=?");
        stmt.setBoolean(1, evento.isStatus());
        stmt.setInt(2, evento.getIdEvento());
        stmt.execute();
        System.out.println("STATUS ATUALIZADO COM SUCESSO");
    }

    public Evento verificarAgenda(Timestamp valor) throws SQLException, ParseException {
        System.out.println("EXECUTANDO CONSULTA PARA ESTA DATA - " + valor);
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from agenda where dataHora=? AND status=false");
        stmt.setTimestamp(1, valor);
        ResultSet rs = stmt.executeQuery();
        Evento ev = null;
        while (rs.next()) {
            ev = new Evento();
            ev.setIdEvento(rs.getInt("idEvento"));
            ev.setDataHora(rs.getTimestamp("dataHora"));
            ev.setLuzQuarto(rs.getBoolean("luzQuarto"));
            ev.setLuzSala(rs.getBoolean("luzSala"));
            ev.setTv(rs.getBoolean("tv"));
            ev.setVentilador(rs.getBoolean("ventilador"));
            ev.setStatus(rs.getBoolean("status"));
            System.out.println("--------------------------------");
            System.out.println("ID DO EVENTO - " + ev.getIdEvento());
            System.out.println("DATA HORA - " + ev.getDataHora());
            System.out.println("LUZ QUARTO - " + ev.isLuzQuarto());
            System.out.println("LUZ SALA - " + ev.isLuzSala());
            System.out.println("TV - " + ev.isTv());
            System.out.println("VENTILADOR - " + ev.isVentilador());
        }
        return ev;
    }
}
