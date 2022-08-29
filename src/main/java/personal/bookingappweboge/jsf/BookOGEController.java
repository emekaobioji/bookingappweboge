package personal.bookingappweboge.jsf;

import personal.bookingappweboge.entities.BookOGE;
import personal.bookingappweboge.jsf.util.JsfUtil;
import personal.bookingappweboge.jsf.util.PaginationHelper;
import personal.bookingappweboge.session.BookOGEFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("bookOGEController")
@SessionScoped
public class BookOGEController implements Serializable {

    private BookOGE current;
    private DataModel items = null;
    @EJB
    private personal.bookingappweboge.session.BookOGEFacade ejbFacade;
    
    //added later for creating a booking object
    @EJB
    private personal.bookingappweboge.session.ClassrOGEFacade classrFacade;
    @EJB
    private personal.bookingappweboge.session.TeacherOGEFacade teacherFacade;
    @EJB
    private personal.bookingappweboge.session.TeamOGEFacade teamFacade;
    @EJB
    private personal.bookingappweboge.session.OffenceOGEFacade offenceFacade;
    @EJB
    private personal.bookingappweboge.session.StudentOGEFacade studentFacade;
    @EJB
    private personal.bookingappweboge.entities.ClassrOGE classrOGE;
    @EJB
    private personal.bookingappweboge.entities.TeacherOGE teacherOGE;
    @EJB
    private personal.bookingappweboge.entities.TeamOGE teamOGE;
    @EJB
    private personal.bookingappweboge.entities.OffenceOGE offenceOGE;
    @EJB
    private personal.bookingappweboge.entities.StudentOGE studentOGE;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    //ids for holding values of a the current booking object on the webpage
    private int classrid;
    private int offenceid;
    private int studentid;
    private int teacherid;
    private int teamid;
    
    //messages
    private String bookmessage;
    
    public int getClassrid(){
        return classrid;
    }

    public void setClassrid(int id){
        classrid=id;
    }

    public int getOffenceid(){
        return offenceid;
    }
    
    public void setOffenceid(int id){
        offenceid=id;
    }

    public int getStudentid(){
        return studentid;
    }
    
    public void setStudentid(int id){
        studentid=id;
    }
    
    public int getTeacherid(){
        return teacherid;
    }
    
    public void setTeacherid(int id){
        teacherid=id;
    }
    
    public int getTeamid(){
        return teamid;
    }
    
    public void setTeamid(int id){
        teamid=id;
    }
    
    public String getBookmessage(){
        return bookmessage;
    }
    
    public void setBookmessage(String msg){
        bookmessage=msg;
    }
    
    public void book(Integer offenceid,String dateofbooking){
        System.out.println("book: called");
        teacherOGE = teacherFacade.find(teacherid);
        studentOGE = studentFacade.find(studentid);
        classrOGE = classrFacade.find(classrid);
        offenceOGE = offenceFacade.find(offenceid);
        teamOGE = teamFacade.find(teamid);
        bookmessage = getFacade().book(teacherOGE, studentOGE, teamOGE, classrOGE, offenceOGE, dateofbooking);
    }
    
    public BookOGEController() {
    }

    public BookOGE getSelected() {
        if (current == null) {
            current = new BookOGE();
            selectedItemIndex = -1;
        }
        return current;
    }
    
//    public void create(String teacherName, String className, String studentName,
//            String teamName,String offenceDescription){
//            TeacherFacade.
//    }

    private BookOGEFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (BookOGE) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new BookOGE();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BookOGECreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (BookOGE) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BookOGEUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (BookOGE) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BookOGEDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public BookOGE getBookOGE(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = BookOGE.class)
    public static class BookOGEControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BookOGEController controller = (BookOGEController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bookOGEController");
            return controller.getBookOGE(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof BookOGE) {
                BookOGE o = (BookOGE) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + BookOGE.class.getName());
            }
        }

    }

}
