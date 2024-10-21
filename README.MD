## Requisitos
- **Java 17 Correto**: Necesario para ejecutar el proyecto.
- **Maven**: Utilizado para compilar y construir el proyecto.
- **Docker** 
- **Kubernetes**: yo por ejemplo habilite el de docker desktop
- cree una cuenta gratuita de azure para cosmo db, pero para el event hub use uno de prueba que tenia del proyecto pasado
por favor cambiar por unos acordes a las pruebas



# Instrucciones para correr el proyecto
* compilar el proyecto con el comando en la raiz del proyecto:<br>
```.
mvn clean install
```

# Construccion de la imagen docker desde el Dockerfile
* desde la raiz del proyecto correr el siguiente comando:<br>
```.
docker build -t cliente-service .
```
la imagen esta publicada en mi docker hub y se jala desde la configuracion del deployment.yml


