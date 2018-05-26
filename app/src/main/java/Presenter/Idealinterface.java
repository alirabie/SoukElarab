package Presenter;

import Model.Addfavouritmodel;
import Model.Addproductmodel;
import Model.Allcategories;
import Model.Allorders;
import Model.Drivers;

import Model.MatgarModel;
import Model.Notifications;

import Model.Orderdetails;
import Model.Productcategoriesmodel;
import Model.orders_detailslist;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by asmaa.mostafa on 13/03/2017.
 */
public interface Idealinterface {
   /* @POST("api/forgetpassword")
    Call<ForgetPassModel>ForgetPassword(@Body Object data);
    @POST("api/login")
    Call<LoginModel>login(@Body Object data);
    @POST("api/driver/home")
    Call<DriverStatusModel>getDriverstatus(@Body Object data);
    @POST("api/driver/change_type")
    Call<ChangeStatusmodel>changeStatusDriver(@Body Object data);
    @POST("api/profile")
    Call<profileModel>getDrverprofile(@Body Object data);

    @POST("api/profile_update")
    Call<profileModel>editDriverProfile(@Body Object data);*/
   //

   @POST("api/matjar/add_delete_driver_fav")
   Call<Addfavouritmodel>addfavourit(@Body Object data);
   //
   @POST("api/matjar/category_view")
   Call<Productcategoriesmodel>getproductCategories(@Body Object data);
   //
   @POST("api/matjar/add_product")

   Call<Addproductmodel>addProduct(@Body Object data);
// drivers
   @POST("api/matjar/all_drivers")
   Call<Drivers>GetDrivers(@Body Object data);
   // getNotifications

   @POST("api/matjar/notification")
   Call<Notifications> getNotifications(@Body Object data);
   //all orders
   @POST("api/matjar/all_orders")
   Call<Allorders> GetAllOrders(@Body Object data);
   //

   @POST("api/matjar/category_view")
   Call<Allcategories> getCategory(@Body Object data);
   //


   @POST("api/matjar/matjar_view")
   Call<MatgarModel> getMatgarview(@Body Object data);
   //

   @POST("api/matjar/de_order")
   Call<Orderdetails> getAllOrdersdetails(@Body Object data);
}
