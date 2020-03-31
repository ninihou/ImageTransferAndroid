package idv.david.imagetransferandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// 此Activity將會以對話視窗模式顯示，呼叫setResult()設定回傳結果
public class LoginDialogActivity extends AppCompatActivity {
    private EditText etUser;
    private EditText etPassword;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        findViews();
        setResult(RESULT_CANCELED);
    }

    private void findViews() {
        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (user.length() <= 0 || password.length() <= 0) {
                    showMessage(R.string.msg_InvalidUserOrPassword);
                    return;
                }

                if (isUserValid(user, password)) {
                    SharedPreferences pref = getSharedPreferences(Util.PREF_FILE,
                            MODE_PRIVATE);
                    pref.edit()//寫入
                            .putBoolean("login", true)
                            .putString("user", user)
                            .putString("password", password)
                            .apply();
                    setResult(RESULT_OK);
                    finish();//back to upload view
                } else {
                    showMessage(R.string.msg_InvalidUserOrPassword);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pref = getSharedPreferences(Util.PREF_FILE, MODE_PRIVATE);
        boolean login = pref.getBoolean("login", false);
        if (login) {
            String name = pref.getString("user", "");
            String password = pref.getString("password", "");
            if (isUserValid(name, password)) {
                setResult(RESULT_OK);
                finish();//back to main view
            } else {
                showMessage(R.string.msg_InvalidUserOrPassword);
            }
        }
    }

    private void showMessage(int msgResId) {
        tvMessage.setText(msgResId);
    }

    private boolean isUserValid(String name, String password) {
        // 應該連線至server端檢查帳號密碼是否正確
        return name.equals("abc");
    }

}
