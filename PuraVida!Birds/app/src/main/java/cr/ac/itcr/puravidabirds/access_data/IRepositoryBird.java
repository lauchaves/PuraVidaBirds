package cr.ac.itcr.puravidabirds.access_data;

import java.util.ArrayList;

import cr.ac.itcr.puravidabirds.entitys.Bird;

/**
 * Created by Laurens on 12/04/2016.
 */
public interface IRepositoryBird <Object> {
    public boolean Save(Object object);

    public boolean Update(Object object);

    public boolean Delete(Object object);

    public ArrayList<String> GetAll();

    public ArrayList<Object> GetBy(Object object);

    public Bird GetByName(String object);
}

