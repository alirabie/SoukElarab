package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 07/05/2018.
 */

public class Orderdetails {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("order")
    @Expose
    public orders_detailslist orders_list;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public orders_detailslist getOrders_list() {
        return orders_list;
    }

    public void setOrders_list(orders_detailslist orders_list) {
        this.orders_list = orders_list;
    }
}
