## Requisitos
- **Java 17 Correto**: Necesario para ejecutar el proyecto.
- **Maven**: Utilizado para compilar y construir el proyecto.
- **Docker** 
- **Kubernetes**: se habilita servicio de kubernetes desde el de docker desktop tal como se ve en las imagenes de evidencia.
- cree una cuenta gratuita de azure para cosmo db, pero para el event hub, por tema de costos use uno de prueba que tenia del proyecto pasado y funciono correctamente
por favor cambiar por unos acordes a los propios.

# Obtener codigo fuente
```.
git clone https://github.com/vguerrero/ibk-clientes-service
```
# Instrucciones para correr el proyecto
* compilar el proyecto con el comando en la raiz del proyecto:<br>
```.
mvn clean install
```

* la imagen ya esta publicada en mi repositorio de docker hub y se carga desde la configuracion del deployment.yml
* https://hub.docker.com/repository/docker/vguerrero/cliente-service/general

* de todas maneras dejo el comando para la creacion de la imagen
```.
docker build -t cliente-service .
```
* curls del servicio
guardado
 ```.
  curl --location 'http://localhost:8081/clientes' \
  --header 'consumerid: SMP' \
  --header 'traceparent: 00-db65adadcc7ab67b6eaa38521c34c42a-c1d2e415b56c412b-01' \
  --header 'devicetype: AND' \
  --header 'deviceid: MA42FJ799HF' \
  --header 'Content-Type: application/json' \
  --data ' {
  "nombre": "andres",
  "apellido": "navarro",
  "apellidoMaterno":"soto",
  "fecha_creacion": 1729272916926,
  "activo": true

  }'
```
consulta
 ```.
 curl --location 'http://localhost:8081/clientes'
 ```
se deja carpeta de evidencia de la funcionalidad lograda en la practica
[evidencias](evidencias)

Muchas gracias.


