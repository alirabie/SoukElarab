package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 05/05/2018.
 */

public class Allcategories {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("categores")
    @Expose
    private List<Categore> categores = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Categore> getCategores() {
        return categores;
    }

    public void setCategores(List<Categore> categores) {
        this.categores = categores;
    }
}
