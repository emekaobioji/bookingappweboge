package personal.bookingappweboge.jsf;

import personal.bookingappweboge.entities.OffenceOGE;
import personal.bookingappweboge.jsf.util.JsfUtil;
import personal.bookingappweboge.jsf.util.PaginationHelper;
import personal.bookingappweboge.session.OffenceOGEFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

@Named("offenceOGEController")
@SessionScoped
public class OffenceOGEController implements Serializable {

    private OffenceOGE current;
    private DataModel items = null;
    @EJB
    private personal.bookingappweboge.session.OffenceOGEFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private String offenceDescription;
    private Integer offenceid;

    public OffenceOGEController() {
    }
    public Integer createOffence(){
        return getFacade().createOffence(offenceid, offenceDescription).getId();
    }
    public void setOffenceDescription(String text){
        offenceDescription = text;
        /*
        owing to the fact that user can just type out a new offence descrip.
        each time description is changed, one has to verify if a new offence
        has been created. In order that when it is time to persist the book,
        the offence with id -1 is created before the book is created.
        */
        OffenceOGE offenceoge = getFacade().findOffenceWithDescription(text);
        setOffenceid(offenceoge.getId());
    }
    public String getOffenceDescription(){
        return offenceDescription;
    }
    public void setOffenceid(int id){
        this.offenceid = id;
    }
    public Integer getOffenceid(){
        return offenceid;
    }
    public List<String> loadOffenceOGEDescriptions(String userTyped) {
        System.out.println("OffenceOGEConroller:loadOffenceOGEDescriptions(String userTyped):userTyped="+userTyped);
        List<OffenceOGE> offenceOGEs =  getFacade().findAllWhereDescriptionContains(userTyped);
        System.out.println("OffenceOGEConroller:loadOffenceOGEDescriptions(String userTyped):offenceOGEs="+offenceOGEs.size());
        Iterator<OffenceOGE> ioffenceOGEs = offenceOGEs.iterator();
        ArrayList<String> offenceDescriptions = new ArrayList();
        while(ioffenceOGEs.hasNext()){
            offenceDescriptions.add(ioffenceOGEs.next().getDescription());
        }
        return offenceDescriptions;
    }
    public OffenceOGE getSelected() {
        if (current == null) {
            current = new OffenceOGE();
            selectedItemIndex = -1;
        }
        return current;
    }

    private OffenceOGEFacade getFacade() {
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
        current = (OffenceOGE) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new OffenceOGE();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OffenceOGECreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (OffenceOGE) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OffenceOGEUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (OffenceOGE) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OffenceOGEDeleted"));
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

    public OffenceOGE getOffenceOGE(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = OffenceOGE.class)
    public static class OffenceOGEControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OffenceOGEController controller = (OffenceOGEController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "offenceOGEController");
            return controller.getOffenceOGE(getKey(value));
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
            if (object instanceof OffenceOGE) {
                OffenceOGE o = (OffenceOGE) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + OffenceOGE.class.getName());
            }
        }

    }

}
