package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 05/05/2018.
 */

public class Order {
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_img")
    @Expose
    private String userImg;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("address")
    @Expose
    private Integer address;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }
}
