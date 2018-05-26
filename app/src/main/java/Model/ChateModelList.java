package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 5/19/2018.
 */

public class ChateModelList {

    @SerializedName("chat_id")
    @Expose
    private String chatId;
    @SerializedName("user_id")
    @Expose

    private String userId;
    @SerializedName("matjar_id")
    @Expose
    private String matjarId;
    @SerializedName("matjar_image")
    @Expose
    private String matjarImage;
    @SerializedName("matjar_name")
    @Expose
    private String matjarName;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMatjarId() {
        return matjarId;
    }

    public void setMatjarId(String matjarId) {
        this.matjarId = matjarId;
    }

    public String getMatjarImage() {
        return matjarImage;
    }

    public void setMatjarImage(String matjarImage) {
        this.matjarImage = matjarImage;
    }

    public String getMatjarName() {
        return matjarName;
    }

    public void setMatjarName(String matjarName) {
        this.matjarName = matjarName;
    }

}

