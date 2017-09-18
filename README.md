# digicel-rest

Servicio que se encarga de exponer servicio rest generico. De acuerdo al requerimiento se expondra un Path el cual por medio de un parametro enviado permita definir cual es el servicio a consumir. A nivel de seguridad se tendra una autenticación basic por medio de usuario y contraseña la cual será utilizada para de igual forma consumir los servicios internos. El servicio expuesto debe permitir recibir un mensaje que contendrá la información con la que se consumira el servicio interno de Digicel. Se adjunta documentación de los servicios rest internos a consumir.



Servicio 2

Endpoint Rest Request-Reply. Componente que se encarga de exponer servicio rest generico. De acuerdo al requerimiento se expondra un Path el cual por medio de un parametro enviado permita definir cual es la cola en donde se registrará el mensaje. El servicio expuesto debe permitir recibir un mensaje que contendrá la información que se registrará en la cola.




mvn spring-boot:run

 
 
curl -H "Content-Type: application/json" -X POST -d '{ "parameters":{"accountId":"1","addressId":"1"  }}' http://localhost:20001/getaccount



curl -H "Content-Type: application/json" -X POST -d '{ "parameters":{"accountId":"1","addressId":"1"  }}' http://localhost:20002/getaccount