package com.mikeriv.checkmate.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.mikeriv.checkmate.util.JSONUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RestaurantMenu implements Parcelable {

  private Map<String, Object> mItems;

  public RestaurantMenu() {
    mItems = new HashMap<String, Object>();
  }

  public RestaurantMenu(Parcel in) {
    this();
    mItems = in.readHashMap(ClassLoader.getSystemClassLoader());
  }

  public void setup(JSONObject data)  {
    //Convert JSON Data to hash map
    try {
      mItems = JSONUtils.toMap(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the price of an item in this menu
   * @param item - the item name to get the price for
   * @return the price of the item
   */
  public String getPrice(String item) {
    if (mItems.containsKey(item)) {
      return (String) mItems.get(item);
    }
    return "";
  }

  /**
   * Adds a menu item to this list
   * @param item
   * @param price
   * @return
   */
  public boolean addItem(String item, float price) {
    if (item == null || item.length() <= 1) {
      return false;
    }
    if (price < 0) {
      return false;
    }
    mItems.put(item, String.valueOf(price));
    return true;
  }

  public boolean removeItem(String item) {
    if (item == null) {
      return false;
    }
    String removed = (String) mItems.remove(item);
    if (removed == null) {
      return false;
    }
    return true;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel out, int flags) {
    out.writeMap(mItems);
  }

  // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
  public static final Parcelable.Creator<RestaurantMenu> CREATOR = new Parcelable.Creator<RestaurantMenu>() {
    public RestaurantMenu createFromParcel(Parcel in) {
      return new RestaurantMenu(in);
    }

    public RestaurantMenu[] newArray(int size) {
      return new RestaurantMenu[size];
    }
  };

}
