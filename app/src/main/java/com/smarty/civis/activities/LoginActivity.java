package com.smarty.civis.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smarty.civis.R;
import com.smarty.civis.utils.AuthUtils;
import com.smarty.civis.utils.PrefUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private final String LOG_TAG = LoginActivity.class.getSimpleName();

//    /**
//     * A dummy authentication store containing known user names and passwords.
//     * TODO: remove after connecting to a real authentication system.
//     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo@example.com:hello", "bar@example.com:world"
//    };
//    /**
//     * Keep track of the login task to ensure we can cancel it if requested.
//     */
//    private UserLoginTask mAuthTask = null;

    // UI references.
//    private AutoCompleteTextView mEmailView;
//    private EditText mPasswordView;
//    private View mLoginOverlay;

    private boolean mLoginRequested = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If we are already authorized go to MainActivity directly
        if(!TextUtils.isEmpty(PrefUtils.getCode(this))){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        // Set up the login form.
//        mLoginOverlay = findViewById(R.id.login_overlay);
//        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_text);
//
//        mPasswordView = (EditText) findViewById(R.id.password);
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login_text || id == EditorInfo.IME_NULL) {
//                    attemptLogin(findViewById(R.id.login_button));
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    public void skipLogin(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * Attempts to log in.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin(View view) {
        mLoginRequested = true;
        Intent intent = AuthUtils.getAuthenticationIntent();
        startActivity(intent);

    /*
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
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
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
        */
    }

//    private boolean isEmailValid(String email) {
//        //Simple email validity check
//        return email.contains("@");
//    }
//
//    private boolean isPasswordValid(String password) {
//        //Simple email password check
//        return password.length() > 4;
//    }
//
//    /**
//     * Shows the progress UI
//     */
//    private void showProgress(final boolean show) {
//        int animTime = getResources().getInteger(android.R.integer.config_longAnimTime);
//        mLoginOverlay.setVisibility(show ? View.VISIBLE : View.GONE);
//        mLoginOverlay.animate().setDuration(animTime).alpha(show ? (float) 0.5 : 0);
//    }
//
//    /**
//     * Represents an asynchronous login/registration task used to authenticate
//     * the user.
//     */
//    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
//
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean success = false;
        Uri uri = getIntent().getData();
        if (uri != null){
            String code = uri.getQueryParameter("code");
            Log.i(LOG_TAG, "Code URI " + uri);
            if(code != null){
                success = true;
                PrefUtils.putCode(this, uri);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            Log.i(LOG_TAG, "Authorization code: " + code);
        }
        if(!success && mLoginRequested){
            mLoginRequested = false;
            Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
        }
    }
}

