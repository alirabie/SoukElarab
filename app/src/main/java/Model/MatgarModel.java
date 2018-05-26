package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 07/05/2018.
 */

public class MatgarModel {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("matjar_info")
    @Expose
    private MatjarInfo matjarInfo;
    @SerializedName("Products")
    @Expose
    private List<product> products = null;
    @SerializedName("Count")
    @Expose
    private Integer count;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public MatjarInfo getMatjarInfo() {
        return matjarInfo;
    }

    public void setMatjarInfo(MatjarInfo matjarInfo) {
        this.matjarInfo = matjarInfo;
    }

    public List<product> getProducts() {
        return products;
    }

    public void setProducts(List<product> products) {
        this.products = products;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
