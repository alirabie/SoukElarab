package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 07/05/2018.
 */

public class MatjarInfo {
    @SerializedName("shope_name")
    @Expose
    private String shopeName;
    @SerializedName("shope_logo")
    @Expose
    private String shopeLogo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("public")
    @Expose
    private String _public;

    public String getShopeName() {
        return shopeName;
    }

    public void setShopeName(String shopeName) {
        this.shopeName = shopeName;
    }

    public String getShopeLogo() {
        return shopeLogo;
    }

    public void setShopeLogo(String shopeLogo) {
        this.shopeLogo = shopeLogo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublic() {
        return _public;
    }

    public void setPublic(String _public) {
        this._public = _public;
    }
}
