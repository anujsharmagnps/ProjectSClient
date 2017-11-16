package com.smartecab.projectsdriver.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smartecab.projectsdriver.Base.BaseActivity;
import com.smartecab.projectsdriver.MainActivity;
import com.smartecab.projectsdriver.R;

import butterknife.BindView;
import butterknife.ButterKnife;
;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.btnLogin)
    Button loginButton;
    @BindView(R.id.txtUserName)
    EditText txtEmail;
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
