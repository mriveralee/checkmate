package com.mikeriv.checkmate;

import android.app.Activity;
import android.content.res.Resources;
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
    Resources res = getResources();

    String email = res.getString(R.string.default_email);
    String venmo = res.getString(R.string.default_venmo);
    String name = res.getString(R.string.default_restaurant_name);
    String address = res.getString(R.string.default_address);
    String zip = res.getString(R.string.default_zip_code);
    String phone = res.getString(R.string.default_phone);
    String city = res.getString(R.string.default_city);
    String state = res.getString(R.string.default_state);
    String menuStr = res.getString(R.string.default_menu);
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
