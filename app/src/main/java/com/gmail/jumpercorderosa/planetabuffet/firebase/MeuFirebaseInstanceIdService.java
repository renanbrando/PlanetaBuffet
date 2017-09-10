package com.gmail.jumpercorderosa.planetabuffet.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

//rodar no terminal para ativar o analytics
//C:\opensource\Android_SDK\sdk\platform-tools\adb shell setprop debug.firebase.analytics.app com.gmail.jumpercorderosa.planetabuffet

//se o app já estiver instalado, tem q desinstalar
public class MeuFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public void onTokenRefresh() {

        //pega o token do usuário
        super.onTokenRefresh();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshToken);
    }

    private void sendRegistrationToServer(String refreshToken) {
        Log.d(TAG, "Refreshed Token:" + refreshToken);
    }
}
