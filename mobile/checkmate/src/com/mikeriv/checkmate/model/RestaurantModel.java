package com.mikeriv.checkmate.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.mikeriv.checkmate.R;
import com.mikeriv.checkmate.util.RestaurantKeys;
import org.json.JSONException;
import org.json.JSONObject;

public class RestaurantModel implements Parcelable {

  private String mName;
  private String mPhoneNumber;
  private String mAddress;
  private String mCity;
  private String mState;
  private String mZipCode;
  private String mEmail;
  private String mVenmoId;
  private RestaurantMenu mMenu;

  public RestaurantModel(JSONObject model, Resources res) {
    // Converts the JSON for a restaurant into a model object
    mName = res.getString(R.string.default_restaurant_name);
    mEmail = res.getString(R.string.default_email);
    mVenmoId = res.getString(R.string.default_venmo);
    mAddress = res.getString(R.string.default_address);
    mZipCode = res.getString(R.string.default_zip_code);
    mPhoneNumber = res.getString(R.string.default_phone);
    mCity = res.getString(R.string.default_city);
    mState = res.getString(R.string.default_state);
    mMenu = new RestaurantMenu();

    // Try to create the model from the JSON
    try {
      mName =  model.getString(RestaurantKeys.NAME);
      mEmail = model.getString(RestaurantKeys.EMAIL);
      mVenmoId =  model.getString(RestaurantKeys.VENMO_USER_ID);
      mPhoneNumber =  model.getString(RestaurantKeys.PHONE);
      mAddress =  model.getString(RestaurantKeys.ADDRESS);
      mCity =  model.getString(RestaurantKeys.CITY);
      mState =  model.getString(RestaurantKeys.STATE);
      mZipCode =  model.getString(RestaurantKeys.ZIP);
      mMenu.setup(model.getJSONObject(RestaurantKeys.MENU));
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public RestaurantModel(Parcel in) {
    mName = in.readString();
    mEmail = in.readString();
    mVenmoId = in.readString();
    mPhoneNumber = in.readString();
    mAddress = in.readString();
    mCity = in.readString();
    mState =in.readString();
    mZipCode = in.readString();
    mMenu = in.readParcelable(RestaurantMenu.class.getClassLoader());
  }

  public String getName() {
    return mName;
  }

  public String getPhoneNumber() {
    return mPhoneNumber;
  }

  public String getAddress() {
    return mAddress;
  }

  public String getCity() {
    return mCity;
  }

  public String getState() {
    return mState;
  }

  public String getZipCode() {
    return mZipCode;
  }


  public String getEmail() {
    return mEmail;
  }

  public String getmVenmoId() {
    return mVenmoId;
  }

  public RestaurantMenu getMenu() {
    return mMenu;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel out, int flags) {
    out.writeString(mName);
    out.writeString(mEmail);
    out.writeString(mVenmoId);
    out.writeString(mPhoneNumber);
    out.writeString(mAddress);
    out.writeString(mCity);
    out.writeString(mState);
    out.writeString(mZipCode);
    out.writeParcelable(mMenu, 0);
  }

  // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
  public static final Parcelable.Creator<RestaurantModel> CREATOR = new Parcelable.Creator<RestaurantModel>() {
    public RestaurantModel createFromParcel(Parcel in) {
      return new RestaurantModel(in);
    }

    public RestaurantModel[] newArray(int size) {
      return new RestaurantModel[size];
    }
  };

}
