package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 08/05/2018.
 */

public class orders_detailslist {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_img")
    @Expose
    private String userImg;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("Products_name")
    @Expose
    private String productsName;
    @SerializedName("Products_price")
    @Expose
    private String productsPrice;
    @SerializedName("Products_image")
    @Expose
    private String productsImage;
    @SerializedName("Products_total")
    @Expose
    private Double productsTotal;
    @SerializedName("Products_qty")
    @Expose
    private String productsQty;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

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

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {
        this.productsPrice = productsPrice;
    }

    public Double getProductsTotal() {
        return productsTotal;
    }

    public void setProductsTotal(Double productsTotal) {
        this.productsTotal = productsTotal;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public void setProductsImage(String productsImage) {
        this.productsImage = productsImage;
    }


    public String getProductsQty() {
        return productsQty;
    }

    public void setProductsQty(String productsQty) {
        this.productsQty = productsQty;
    }

}
