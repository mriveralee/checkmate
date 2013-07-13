package com.mikeriv.checkmate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.mikeriv.checkmate.util.RestaurantKeys;

public class CheckMateMainActivity extends Activity {

  private TextView mMainName;
  private TextView mMainAddress;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String email = "",
            venmo = "",
            name = "",
            address = "",
            zip = "",
            phone = "",
            city = "",
            state = "",
            menuStr = "";
    try {
      email = savedInstanceState.getString(RestaurantKeys.EMAIL);
      menuStr = savedInstanceState.getString(RestaurantKeys.MENU);
      venmo = savedInstanceState.getString(RestaurantKeys.VENMO_USER_ID);
      name = savedInstanceState.getString(RestaurantKeys.NAME);
      phone = savedInstanceState.getString(RestaurantKeys.PHONE);
      address = savedInstanceState.getString(RestaurantKeys.ADDRESS);
      city = savedInstanceState.getString(RestaurantKeys.CITY);
      state = savedInstanceState.getString(RestaurantKeys.STATE);
      zip = savedInstanceState.getString(RestaurantKeys.ZIP);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //TODO Convert menu back to json

    setContentView(R.layout.main);
    mMainName = (TextView) findViewById(R.id.main_name);
    mMainName.setText(name);

    mMainAddress = (TextView) findViewById(R.id.main_address);
    mMainAddress.setText(address);
  }



}
