package Model;

/**
 * Created by Asmaa on 08/04/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RequestsModel {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("def_img")
    @Expose
    private String defImg;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("fav")
    @Expose
    private Integer fav;
    String trader_id;
    String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTrader_id() {
        return trader_id;
    }

    public void setTrader_id(String trader_id) {
        this.trader_id = trader_id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefImg() {
        return defImg;
    }

    public void setDefImg(String defImg) {
        this.defImg = defImg;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getFav() {
        return fav;
    }

    public void setFav(Integer fav) {
        this.fav = fav;
    }

}
