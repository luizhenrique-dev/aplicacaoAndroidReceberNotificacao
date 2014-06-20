package br.ufg.inf.es.gcm;
//Final

//Classe que contém algumas constantes relacionadas ao projeto
public interface Config {
  
  // Servidor GCM usando Java
  // Pegar o ip do adaptaor de rede sem fio endereço IPv4:
	static final String APP_SERVER_URL =
   "http://192.168.1.12:8080/ServidorDeAplicacaoGCM/NotificacaoGCM?shareRegId=1";
 
  // Número do Projeto Google, email: lhfses@gmail.com
  static final String GOOGLE_PROJECT_ID = "825461449027";
  static final String MESSAGE_KEY = "message";
 
}