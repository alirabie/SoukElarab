package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 05/05/2018.
 */

public class Allorders {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("order")
    @Expose
    private List<Order> order = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
