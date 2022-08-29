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
import javax.persistence.criteria.CriteriaBuilder;
import personal.bookingappweboge.entities.OffenceOGE;
import personal.bookingappweboge.entities.OffenceOGE_;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author emeka
 */
@Stateless
public class OffenceOGEFacade extends AbstractFacade<OffenceOGE> {
    
    /*
     * create an OffenceOGE if the id is -1 i.e. does not already exists, else
     * find it and return.
     */
    public OffenceOGE createOffence(Integer id, String description){
        if(id == -1){
            Integer offenceCount = count()+1;
            OffenceOGE offenceOGE = new OffenceOGE();
            offenceOGE.setId(offenceCount);
            offenceOGE.setDescription(description);
            create(offenceOGE);
            return offenceOGE;
        }else{
            OffenceOGE offenceOGE = find(id);
            return offenceOGE;
        }
    }
    
    public OffenceOGE findOffenceWithDescription(String description) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OffenceOGE> cq = cb.createQuery(OffenceOGE.class);
        Root<OffenceOGE> offenceoge = cq.from(OffenceOGE.class);
        cq.where(cb.equal(offenceoge.get(OffenceOGE_.description), description.trim()));
        List<OffenceOGE> offenceoges = getEntityManager().createQuery(cq).getResultList();
        if(offenceoges != null){
            if (!offenceoges.isEmpty()){
                return offenceoges.get(0);
            }else{
                //create an OffenceOGE without id i.e. -1
                OffenceOGE offenceogeNew = new OffenceOGE();
                offenceogeNew.setDescription(description);
                offenceogeNew.setId(-1);
                return offenceogeNew;
            }
        }else{
            //create an OffenceOGE without id i.e. -1
            OffenceOGE offenceogeNew = new OffenceOGE();
            offenceogeNew.setDescription(description);
            offenceogeNew.setId(-1);
            return offenceogeNew;
        }
    }
    
    public List<OffenceOGE> findAllWhereDescriptionContains(String regex) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OffenceOGE> cq = cb.createQuery(OffenceOGE.class);
        Root<OffenceOGE> offenceoge = cq.from(OffenceOGE.class);
        cq.where(cb.like(offenceoge.get(OffenceOGE_.description), "%"+regex.trim()+"%"));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<OffenceOGE> getOffenceOGEs() {        
        List<OffenceOGE> offenceoges = findAll();
        System.out.println("OffenceOGEFacade.findAll()="+offenceoges.size());
        return offenceoges;
    }
    
    @PersistenceContext(unitName = "bookingappwebogepu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OffenceOGEFacade() {
        super(OffenceOGE.class);
    }
    
}
