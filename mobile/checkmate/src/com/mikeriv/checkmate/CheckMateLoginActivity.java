package com.mikeriv.checkmate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class CheckMateLoginActivity extends Activity {

  public static final String RESTAURANT_VENMO_USER_ID_KEY = "RESTAURANT_VENMO_USER_ID";
  public static final String RESTAURANT_NAME_KEY = "RESTAURANT_NAME";
  public static final String RESTAURANT_MENU_KEY = "RESTAURANT_MENU";
  public static final String RESTAURANT_EMAIL_KEY = "RESTAURANT_EMAIL";
  public static final String RESAURANT_PHONE_KEY = "RESTAURANT_PHONE";
  private static final String APP_AUTHENTICATION_URL = "http://10.0.2.2:3000/login";

  private static final String TAG = CheckMateLoginActivity.class.getName();

  public static String RESTAURANT_APP_USER_ID;

  /**
   * A dummy authentication store containing known user names and passwords.
   * TODO: remove after connecting to a real authentication system.
   */
  private static final String[] DUMMY_CREDENTIALS = new String[]{
          "test@test.com:1234", "food@food.com:food"};

  /**
   * The default email to populate the email field with.
   */
  public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

  /**
   * Keep track of the login task to ensure we can cancel it if requested.
   */
  private UserLoginTask mAuthTask = null;

  // Values for email and password at the time of the login attempt.
  private String mEmail;
  private String mPassword;

  // UI references.
  private EditText mEmailView;
  private EditText mPasswordView;
  private View mLoginFormView;
  private View mLoginStatusView;
  private TextView mLoginStatusMessageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login);
    System.out.println("Blah");

    // Set up the login form.
    mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
    mEmailView = (EditText) findViewById(R.id.email);
    mEmailView.setText(mEmail);

    mPasswordView = (EditText) findViewById(R.id.password);
    mPasswordView
            .setOnEditorActionListener(new TextView.OnEditorActionListener() {
              @Override
              public boolean onEditorAction(TextView textView, int id,
                                            KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                  attemptLogin();
                  return true;
                }
                return false;
              }
            });

    mLoginFormView = findViewById(R.id.login_form);
    mLoginStatusView = findViewById(R.id.login_status);
    mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

    findViewById(R.id.sign_in_button).setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                attemptLogin();
              }
            });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.activity_login, menu);
    return true;
  }

  /**
   * Attempts to sign in or register the account specified by the login form.
   * If there are form errors (invalid email, missing fields, etc.), the
   * errors are presented and no actual login attempt is made.
   */
  public void attemptLogin() {
    if (mAuthTask != null) {
      return;
    }

    // Reset errors.
    mEmailView.setError(null);
    mPasswordView.setError(null);

    // Store values at the time of the login attempt.
    mEmail = mEmailView.getText().toString();
    mPassword = mPasswordView.getText().toString();

    boolean cancel = false;
    View focusView = null;

    // Check for a valid password.
    if (TextUtils.isEmpty(mPassword)) {
      mPasswordView.setError(getString(R.string.error_field_required));
      focusView = mPasswordView;
      cancel = true;
    } else if (mPassword.length() < 4) {
      mPasswordView.setError(getString(R.string.error_invalid_password));
      focusView = mPasswordView;
      cancel = true;
    }

    // Check for a valid email address.
    if (TextUtils.isEmpty(mEmail)) {
      mEmailView.setError(getString(R.string.error_field_required));
      focusView = mEmailView;
      cancel = true;
    } else if (!mEmail.contains("@")) {
      mEmailView.setError(getString(R.string.error_invalid_email));
      focusView = mEmailView;
      cancel = true;
    }

    if (cancel) {
      // There was an error; don't attempt login and focus the first
      // form field with an error.
      focusView.requestFocus();
    } else {
      // Show a progress spinner, and kick off a background task to
      // perform the user login attempt.
      mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
      showProgress(true);
      mAuthTask = new UserLoginTask();
      mAuthTask.execute((Void) null);
    }
  }

  /**
   * Shows the progress UI and hides the login form.
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  private void showProgress(final boolean show) {
    // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
    // for very easy animations. If available, use these APIs to fade-in
    // the progress spinner.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(
              android.R.integer.config_shortAnimTime);

      mLoginStatusView.setVisibility(View.VISIBLE);
      mLoginStatusView.animate().setDuration(shortAnimTime)
              .alpha(show ? 1 : 0)
              .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                  mLoginStatusView.setVisibility(show ? View.VISIBLE
                          : View.GONE);
                }
              });

      mLoginFormView.setVisibility(View.VISIBLE);
      mLoginFormView.animate().setDuration(shortAnimTime)
              .alpha(show ? 0 : 1)
              .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                  mLoginFormView.setVisibility(show ? View.GONE
                          : View.VISIBLE);
                }
              });
    } else {
      // The ViewPropertyAnimator APIs are not available, so simply show
      // and hide the relevant UI components.
      mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
  }

  /**
   * Represents an asynchronous login/registration task used to authenticate
   * the user.
   */
  public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
    private String mResult;

    @Override
    protected Boolean doInBackground(Void... params) {
      // TODO: attempt authentication against a network service.
      if (mEmail == null || mPassword == null) {
        return false;
      }
      String response;
      int responseCode = -1;
      HttpURLConnection connection;
      try {
        connection = (HttpURLConnection) new URL(APP_AUTHENTICATION_URL).openConnection();
        String userPasswordCombination = mEmail + ":" + mPassword;
        String basicAuth =
                "Basic " + new String(Base64.encode(userPasswordCombination.getBytes(), Base64.NO_WRAP));
        basicAuth = "Basic " + new String(userPasswordCombination);

        connection.setRequestProperty("Authorization", basicAuth);
        connection.setUseCaches(false);
        connection.connect();

        while (responseCode == -1) {
         // wait
          responseCode = connection.getResponseCode();
          response = connection.getResponseMessage();

        }

      } catch (Exception exception) {
        Log.e(TAG, exception.toString());
      }



      for (String credential : DUMMY_CREDENTIALS) {
        String[] pieces = credential.split(":");
        if (pieces[0].equals(mEmail)) {
          // Account exists, return true if the password matches.
          return pieces[1].equals(mPassword);
        }
      }

      // TODO: register the new account here.
      return false;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
      mAuthTask = null;
      showProgress(false);

      if (success) {
        //Start new activity and pass the venmo user name
        Intent mainIntent = new Intent(CheckMateLoginActivity.this, CheckMateMainActivity.class);
        mainIntent.putExtra(RESTAURANT_VENMO_USER_ID_KEY, "venmo");
        mainIntent.putExtra(RESTAURANT_NAME_KEY, "name");
        mainIntent.putExtra(RESTAURANT_EMAIL_KEY, "email");
        mainIntent.putExtra(RESTAURANT_MENU_KEY, "menu");
        mainIntent.putExtra(RESAURANT_PHONE_KEY, "phone");
        CheckMateLoginActivity.this.startActivity(mainIntent);
        finish();
      } else {
        mPasswordView
                .setError(getString(R.string.error_incorrect_password));
        mPasswordView.requestFocus();
      }
    }

    @Override
    protected void onCancelled() {
      mAuthTask = null;
      showProgress(false);
    }
  }
}
