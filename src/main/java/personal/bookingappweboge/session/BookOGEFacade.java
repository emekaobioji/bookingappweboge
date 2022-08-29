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
import personal.bookingappweboge.entities.BookOGE;

//added later to enable creation of a booking
import personal.bookingappweboge.entities.TeacherOGE;
import personal.bookingappweboge.entities.ClassrOGE;
import personal.bookingappweboge.entities.StudentOGE;
import personal.bookingappweboge.entities.TeamOGE;
import personal.bookingappweboge.entities.OffenceOGE;



/**
 *
 * @author emeka
 */
@Stateless
public class BookOGEFacade extends AbstractFacade<BookOGE> {
    
    public String book(TeacherOGE teacher, StudentOGE student, TeamOGE team, 
            ClassrOGE classr, OffenceOGE offence, String dateofbooking){
        StringBuilder sb = new StringBuilder();
        try{
            BookOGE bookoge = new BookOGE();
            bookoge.setTeacherid(teacher.getId());
            bookoge.setStudentid(student.getId());
            bookoge.setTeamid(team.getId());
            bookoge.setClassrid(classr.getId());
            bookoge.setOffenceid(offence.getId());
            bookoge.setDateofbooking(dateofbooking);
            bookoge.setId(count()+1);
            create(bookoge);
            sb.append(teacher.getName());
            sb.append(" booked ");
            sb.append(student.getName());
            sb.append(" of Team ");
            sb.append(team.getName());
            sb.append(" in ");
            sb.append(classr.getClassr());
            sb.append(" for ");
            sb.append(offence.getDescription());
            sb.append(" on ");
            sb.append(dateofbooking);
            sb.append(".");
        }catch(Exception e){
            sb.append(e.getMessage());
            return sb.toString();
        }
        
        
        //return returnMessage;
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public List<BookOGE> getBookOGEs() {        
        List<BookOGE> bookoges = findAll();
        System.out.println("BookOGEFacade.findAll()="+bookoges.size());
        return bookoges;
    }

    @PersistenceContext(unitName = "bookingappwebogepu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookOGEFacade() {
        super(BookOGE.class);
    }
    
}
