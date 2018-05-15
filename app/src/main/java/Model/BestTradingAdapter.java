package Model;

/**
 * Created by ahmed on 4/27/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BestTradingAdapter {


    @SerializedName("trader_id")
    @Expose
    private String traderId;
    @SerializedName("shope_logo")
    @Expose
    private String shopeLogo;
    @SerializedName("shope_name")
    @Expose
    private String shopeName;
    @SerializedName("trader_info")
    @Expose
    private String traderInfo;
    @SerializedName("trader_start")
    @Expose
    private String traderStart;
    String address;

    public String getAddress() {
        return address;
    }
    String product;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public String getShopeLogo() {
        return shopeLogo;
    }

    public void setShopeLogo(String shopeLogo) {
        this.shopeLogo = shopeLogo;
    }

    public String getShopeName() {
        return shopeName;
    }

    public void setShopeName(String shopeName) {
        this.shopeName = shopeName;
    }

    public String getTraderInfo() {
        return traderInfo;
    }

    public void setTraderInfo(String traderInfo) {
        this.traderInfo = traderInfo;
    }

    public String getTraderStart() {
        return traderStart;
    }

    public void setTraderStart(String traderStart) {
        this.traderStart = traderStart;
    }


}
