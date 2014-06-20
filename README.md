ClienteGCM
==================================

Repositório que irá conter os arquivos referentes a aplicação que irá receber uma notificação enviada a ele, faz parte do projeto de Integração de Aplicações.


==================================

Necessidade do cliente:

Aplicativo Android para receber as notificações. Este é o aplicativo para recebimento de uma notificação. Por meio deste aplicativo uma notificação (mensagem) enviada com a aplicação anterior é recebida. 

=================================

A pasta que contém o Projeto final da aplicação é nomeada "Projeto Final". O projeto se chama ClienteGCM.
O ícone utilizado pela aplicação encontra-se disponível em: https://www.flickr.com/photos/124499700@N05/with/14385779485/.
Caso queira utilizá-lo verifique a licença.

=================================

#Informações Importantes sobre configurações do Projeto Final

##Sobre Google Play

###É importante configurar o caminho para o projeto Google Play para a aplicação funcionar. No Eclipse, Clique File > Import. selecione Android > Existing Android Code into Worspace. Nome da Pasta para importar: google-play-services_lib.

###Geralmente o caminho no Windows é: "C:\Program Files\adt-bundle-Windows-x86_64-20140321\sdk\extras\google\google_play_services\libproject\google-play-services_lib".

###Depois de importar, precisamos adicionar o projeto como dependências da aplicação Android.

##Sobre o servidor de aplicação

###O servidor foi desenvolvido utilizando Java Web (JSP) e Apache Tomcat 7.0.53 na IDE de Programação NetBeans versão 7.3.

###A URL de conexão com o servidor fica na classe Config.java. Por padrão ele vem como localhost utilizando a porta 8080 do apache tomcat.

###Configura sua String de conexão de acordo com a sua máquina.
