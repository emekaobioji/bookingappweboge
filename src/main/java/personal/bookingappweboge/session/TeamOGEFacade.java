/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal.bookingappweboge.session;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personal.bookingappweboge.entities.TeamOGE;

/**
 *
 * @author emeka
 */
@Stateless
public class TeamOGEFacade extends AbstractFacade<TeamOGE> {
    
    public List<TeamOGE> getTeamOGEs() {        
        List<TeamOGE> teamOGEs = findAll();
        System.out.println("TeamOGEFacade.findAll()="+teamOGEs.size());
        return teamOGEs;
    }


    @PersistenceContext(unitName = "bookingappwebogepu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeamOGEFacade() {
        super(TeamOGE.class);
    }
    
}
