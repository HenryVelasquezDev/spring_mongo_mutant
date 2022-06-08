# MUTANT PROJECT - SPRING BOOT - MONGO DB

## Descripción aplicativo

Proyecto desarrollado con spring boot y MongoDB Atlas.

El proyecto consiste en detectar si un humano es mutante basándose en su secuencia de ADN

## Reglas del aplicativo

1.  Parámetro de entrada un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN y cada caracter de cada fila representa una columna de la misma tabla (NxN)

        Ej:
        ["ATGCGA","CAGTGC","TTATGT","AACATG","CCCTTA","TCACTG"]

        Tabla NxN:
            ATGCGA
            CAGTGC
            TTATGT
            AACATG
            CCCTTA
            TCACTG

2.  Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

3.  Se identifica si un humano es mutante, si se encuentra más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.

## Configuracion Proyecto

1.  Descargar el proyecto de forma local con la sentencia:

        git clone <url_repositorio>

2.  Abrir el proyecto dentro del IDE de desarrollo y actualizar con maven el proyecto para actualizar las dependencias utilizadas

3.  Moficiar los parametros "spring.data.mongodb.uri" y "spring.data.mongodb.database" dentro del archivo \src\main\resources\application.properties 

        spring.data.mongodb.uri: corresponde a la cadena de conexión con el cluster de MongoDB Atlas
        spring.data.mongodb.database : corresponde al nombre de la base de datos que se utilizara, la BD se crea en caso de no existir

        Ej:
        spring.data.mongodb.uri=mongodb+srv://<db_user>:<password>@cluster0.acqg2.mongodb.net/?ssl=true&retryWrites=true&w=majority
        spring.data.mongodb.database=<db_nombre>

4.  Ejecutar el proyecto dentro del IDE de desarrollo

        Click Derecho sobre el proyecto -> Run As -> Spring Boot App
        
5.  Tambien se puede ejecutar el proyecto desde la ventana de comandos CMD, nos ubicamos dentro de la carpeta del proyecto con el comando cd y ejecutamos la siguiente sentencia

        cd <Ruta carpeta proyecto>
        mvnw.cmd spring-boot:run

## Rutas API

1.  Verificar secuencia de ADN

        Metodo: POST
        Ruta: http://localhost:8081/mutant/
        Body:
        {
            "dna": ["ATGCGA","CAGTGC","TTATGT","AACATG","CCCTTA","TCACTG"]
        }

        El cuerpo de la solicitud es un JSON el cual envia un arreglo de cadena de caracteres, el arreglo debe coincidir con una matriz de NxN, siendo estos valores la cantidad de elementos dentro del arreglo igual al tamaño de cada cadena de caracter dentro del arreglo:

        Ejemplos:
        - "dna": ["ATGC","CAGT","TTAT","AACG"]
        - "dna": ["ATG","TGC","TTA"]

        Status: 200 OK
        Response:
        {
            "id": "62a0d50dc804d24965eed97a",
            "dna": "ATGCGA,CAGTGC,TTATGT,AACATG,CCCTTA,TCACTG",
            "mutante": false
        }

        Se obtiene como respuesta el Id del registro en la BD de mongo, la secuencia ADN evaluada y si la secuencia corresponde o no a un mutante, lo cual indica que el proceso de evaluación y registro se ejecuto de forma correcta.

        

2.  Obtener estatus de las secuencias evaluadas entre humanos y mutantes

        Metodo: GET
        Ruta: http://localhost:8081/stats/

        Response:
        {
            "count_mutant_dna": 5,
            "count_human_dna": 4,
            "ratio": 1.25
        }
        
        Se obtiene como respuesta el conteo de ADNs verificados para humanos y mutantes, adicional el ratio que se obtiene del calculo de ambos valores.


## Códigos de respuesta API
    
1.  HTTP 200-OK: en caso de que no haya ningun error en la verificación y se pueda guardar la secuencia ADN evaluada.

2.  HTTP 403-Forbidden: en caso de existir algun error en la verificación de la secuencia, ya sea que posee caracteres fuera de los permitidos o no cumple con las caracteristicas de una tabla NxN
    

## Consideraciones
-   Si al momento de ejecutar el proyecto en el ambiente local aparece el siguiente error:
        
        https://stackoverflow.com/questions/65125510/spring-boot-mongodb-connectivity-issue
        
-   La solución es agregar la siguiente sentencia a los parametros de ejecución de la VM dentro del IDE utilizado

        -Djdk.tls.client.protocols=TLSv1.2 

-   Si al ejecutar el proyecto se obtiene un error debido a que el puerto que se quiere utilizar se encuentra ocupado por otra aplicación, la solución es modificar en el archivo \src\main\resources\application.properties el parametro "server.port"

        Ej: 
        server.port=<Puerto disponible>
