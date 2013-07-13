package com.mikeriv.checkmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.mikeriv.checkmate.model.RestaurantModel;
import com.mikeriv.checkmate.util.RestaurantKeys;

public class CheckMateMainActivity extends Activity {

  private RestaurantModel mRestaurant;

  private TextView mMainName;
  private TextView mMainAddress;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    mMainName = (TextView) findViewById(R.id.main_name);
    mMainAddress = (TextView) findViewById(R.id.main_address);
    // Get restaurant info a  =
   if (savedInstanceState != null) {
      mRestaurant = savedInstanceState.getParcelable(RestaurantKeys.RESTAURANT);
   } else {
     Intent currentIntent = getIntent();
     mRestaurant = currentIntent.getParcelableExtra(RestaurantKeys.RESTAURANT);
    }
    if (mRestaurant != null) {
      mMainName.setText(mRestaurant.getName());
      mMainAddress.setText(mRestaurant.getAddress());
    }


  }


}
