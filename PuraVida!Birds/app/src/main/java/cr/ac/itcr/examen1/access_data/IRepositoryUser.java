package cr.ac.itcr.examen1.access_data;

import java.util.ArrayList;

/**
 * Created by Laurens on 11/04/2016.
 */


public interface IRepositoryUser<Object> {

    public boolean Save(Object object);

    public boolean Update(Object object);

    public boolean Delete(Object object);

    public ArrayList<Object> GetAll();

    public ArrayList<Object> GetBy(Object object);
}
