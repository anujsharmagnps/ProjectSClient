package com.smartecab.projectsdriver.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartecab.projectsdriver.Base.BaseActivity;
import com.smartecab.projectsdriver.MainActivity;
import com.smartecab.projectsdriver.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @InjectView(R.id.btnLogin)
    Button loginButton;
    @InjectView(R.id.txtUserName)
    EditText txtEmail;
    @InjectView(R.id.txtPassword)
    EditText txtPassword;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new LoginPresenter(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.Login(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class );
        LoginActivity.this.startActivity(intent);
    }
}
