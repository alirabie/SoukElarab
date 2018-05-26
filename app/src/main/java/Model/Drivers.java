package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 02/05/2018.
 */

public class Drivers {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("driver")
    @Expose
    private List<Driver> driver = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Driver> getDriver() {
        return driver;
    }

    public void setDriver(List<Driver> driver) {
        this.driver = driver;
    }
}
