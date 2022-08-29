/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal.bookingappweboge.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personal.bookingappweboge.entities.ClassrOGE;

/**
 *
 * @author emeka
 */
@Stateless
public class ClassrOGEFacade extends AbstractFacade<ClassrOGE> {

    public List<ClassrOGE> getClassrOGEs() {        
        List<ClassrOGE> classroges = findAll();
        System.out.println("ClassrOGEFacade.findAll()="+classroges.size());
        return classroges;
    }

    
    @PersistenceContext(unitName = "bookingappwebogepu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassrOGEFacade() {
        super(ClassrOGE.class);
    }
    
}
