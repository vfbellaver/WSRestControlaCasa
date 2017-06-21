/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author vfbellaver & EderSMarques
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(CorsFilter.class);
        return resources;
    }

    /**
     * Não modifique o método addRestResourceClasses (). É automaticamente      
     * Preenchido com todos os recursos definidos no projeto. Se necessário,
     * comente chamando esse método em getClasses ().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Recursos.ConsultaEventoRecurso.class);
        resources.add(Recursos.EventoRecurso.class);
    }

}
