package br.ufg.inf.es.gcm;

import android.content.Context;
import android.content.Intent;

public class Utilidades {
	/**
     * URL do Servidor (como http://my_host:8080/gcm-demo)
     */
    static final String SERVER_URL = null;

    /**
     * ID do Google API project registrado para usar GCM.
     */
    static final String SENDER_ID = "AIzaSyCb_mkcj2P_1gVu11vEyWqOXm5RWvLPsvs";

    /**
     * Tag usada no log das mensagens.
     */
    static final String TAG = "GCMDemo";

    /**
     * Intent utilizado para mostrar a mensagem na tela.
     */
    static final String DISPLAY_MESSAGE_ACTION =
            "br.ufg.inf.es.gcm.DISPLAY_MESSAGE";

    /**
     * Intent extra que contém a mensagem a ser mostrada.
     */
    static final String EXTRA_MESSAGE = "message";

    /**
     * Notifica a UI para mostrar a mensagem.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context context da aplicação.
     * @param message mensagem a ser mostrada.
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
