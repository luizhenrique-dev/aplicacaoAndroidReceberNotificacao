package br.ufg.inf.es.gcm;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
 
import android.content.Context;
import android.util.Log;
 
public class CompartilharServidorExterno {
 
  public String compartilhaRegIdComServidorApp(final Context context,
      final String regId) {
 
    String result = "";
    Map<String, String> paramsMap = new HashMap<String, String>();
    paramsMap.put("regId", regId);
    try {
      URL serverUrl = null;
      try {
        serverUrl = new URL(Config.APP_SERVER_URL);
      } catch (MalformedURLException e) {
        Log.e("AppUtil", "Erro de Conex�o com a URL: "
            + Config.APP_SERVER_URL, e);
        result = "URL Inv�lida: " + Config.APP_SERVER_URL;
      }
 
      StringBuilder postBody = new StringBuilder();
      Iterator<Entry<String, String>> iterator = paramsMap.entrySet()
          .iterator();
 
      while (iterator.hasNext()) {
        Entry<String, String> param = iterator.next();
        postBody.append(param.getKey()).append('=')
            .append(param.getValue());
        if (iterator.hasNext()) {
          postBody.append('&');
        }
      }
      String body = postBody.toString();
      byte[] bytes = body.getBytes();
      HttpURLConnection httpCon = null;
      try {
        httpCon = (HttpURLConnection) serverUrl.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setUseCaches(false);
        httpCon.setFixedLengthStreamingMode(bytes.length);
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Content-Type",
            "application/x-www-form-urlencoded;charset=UTF-8");
        OutputStream out = httpCon.getOutputStream();
        out.write(bytes);
        out.close();
 
        int status = httpCon.getResponseCode();
        if (status == 200) {
          result = "RegId compartilhado com o Servidor de Aplica��o. RegId: "
              + regId;
        } else {
          result = "Falha ao postar." + " Status: " + status;
        }
      } finally {
        if (httpCon != null) {
          httpCon.disconnect();
        }
      }
 
    } catch (IOException e) {
      result = "Falha ao postar. Erro no compartilhamento com o Servidor de Aplica��o.";
      Log.e("AppUtil", "Erro no compartilhamento com o Servidor de Aplica��o: " + e);
    }
    return result;
  }
}