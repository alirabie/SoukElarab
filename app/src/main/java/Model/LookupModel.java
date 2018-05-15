package Model;

/**
 * Created by ahmed on 5/7/2018.
 */

public class LookupModel {
    String car_id;
    String name;

    public LookupModel(String car_id, String name) {
        this.car_id = car_id;
        this.name = name;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
