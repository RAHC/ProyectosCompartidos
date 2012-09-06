/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fecha;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class DateBean {

    public String getCurrentDate() {
        String dateString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dateString =sdf.format(date);
        return dateString;  
    }
}



