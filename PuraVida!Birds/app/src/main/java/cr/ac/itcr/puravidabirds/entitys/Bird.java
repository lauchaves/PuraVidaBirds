package cr.ac.itcr.puravidabirds.entitys;

/**
 * Created by Laurens on 12/04/2016.
 */
public class Bird {

     int Id;
     String birdName;
     String birdScientificName;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String imagePath;



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBirdName() {
        return birdName;
    }

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }

    public String getBirdScientificName() {
        return birdScientificName;
    }

    public void setBirdScientificName(String birdScientificName) {
        this.birdScientificName = birdScientificName;
    }





}
