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
import personal.bookingappweboge.entities.TeacherOGE;

/**
 *
 * @author emeka
 */
@Stateless
public class TeacherOGEFacade extends AbstractFacade<TeacherOGE> {

    public List<TeacherOGE> getTeacherOGEs() {        
        List<TeacherOGE> teacheroges = findAll();
        System.out.println("TeacherOGEFacade.findAll()="+teacheroges.size());
        return teacheroges;
    }

    
    @PersistenceContext(unitName = "bookingappwebogepu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeacherOGEFacade() {
        super(TeacherOGE.class);
    }
    
}
