package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();

        // Configuracion de Permisos de Perfil y Correo
        LoginButton loginButton = findViewById(R.id.facebookLoginButton);
        loginButton.setPermissions(Arrays.asList("email", "public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Llama al método para obtener los datos del usuario
                getUserProfile(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException exception) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void getUserProfile(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            // Obtén los datos del usuario
                            String firstName = object.getString("first_name");
                            String lastName = object.getString("last_name");
                            String email = object.getString("email");

                            // Imprime los datos
                            System.out.println("Nombre: " + firstName);
                            System.out.println("Apellido: " + lastName);
                            System.out.println("Correo: " + email);

                            goToNextActivity(firstName, lastName, email);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void goToNextActivity(String firstName, String lastName, String email) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("first_name", firstName);
        intent.putExtra("last_name", lastName);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
}
