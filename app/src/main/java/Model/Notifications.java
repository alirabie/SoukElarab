package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asmaa on 05/05/2018.
 */

public class Notifications {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("noti")
    @Expose
    private ArrayList<Notus> noti = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public ArrayList<Notus> getNoti() {
        return noti;
    }

    public void setNoti(ArrayList<Notus> noti) {
        this.noti = noti;
    }
}
