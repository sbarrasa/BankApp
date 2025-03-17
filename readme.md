# BankCustomers DEMO

>Funciona con JAVA 17+

### Pruebas desde el código fuente 
1) Descagar del repositorio de https://github.com/sbarrasa/BankApp
1.b) o abrir el .zip con el proyecto
2) Abrir en el IDE (InteliJ, Eclipse, etc.) 
2.b) o compilar con maven (se incluye el pom.xml)
3) Ejecutar para levantar la app. Se levantará.
3.1) Servidor Tomcat
3.2) Servidor H2 con la DB Bank
3.3) Aplicación

### Pruebas desde BankCustomers.jar
1) Descargar BankCustomers.jar
2) ejecutar 
> java -jar BankCustomers.jar


### SERVICIOS
La app tiene 4 controllers separados que podrían ponerse en microservicios independientes.

### CustomerController 
> Api: http://localhost:8080/api/customers

> Api tests: src/test/java/http/customer-api-test.http

* Operaciones:
1) Alta (POST)
recibe como path param el id del customer (ej: DNI)
recibe un JSON con los datos del customer
levanta excepción en caso de duplicidad (BAD_REQUEST)
2) Actualización (PUT)
idem alta
levanta excepción en caso de no existir el cliente (NOT_FOUND)
2) Borrado (DELETE)
recibe path param con id del customer
sale con NOT_FOUND en caso de no existir
en caso de OK devuelve el JSON del customer borrado por si se desea mostrar en el consumidor del servicio 
4) Consulta (GET)
recibe path param con id del customer
devuelve OK y el JSON del customer sin productos
en caso de no existir sale con NOT_FOUND
1.5) Listado de customers (GET)
igual que la consulta individual per no recibe ningún parámetro 
devuelve los datos de todos los customers sin productos

### 2) CustomerProductsController 
> Api: http://localhost:8080/api/customers/{customerId}/products

> ApiTest: src/test/java/http/customer-products-api-test.http

1) Alta de producto (POST)
recibe el id del customer como path param
y el JSON del producto en el body
verifica existencia de customer y duplicidad de producto
2) Borrado de producto/s
recibe el id del customer como path param
recibe un producto con atributos de ejemplo para usar como criterio
elemina todos los productos que cumplan con todos los criterios
de esta manera, en la misma operación 
se puede usar para eliminar todos los productos con "branch": "VISA" 
o con un cbu determinado o que sean creiticios o que sean VISA y tarjeta de crédito
3) Consulta de un producto (GET)
/{customerId}/products/filter 
con el mismo criterio del borrado, 
devuelve todos los productos que cumplan con el criterio especificado en el sample
4) Consulta de todos los productos de un customer (GET)
devuelve el listado de todos los productos de un customer

### Codes
> Api: http://localhost:8080/codes/{enumtype}

> Api tests: src/test/java/http/codes-api-test.http

Este controller permite consultar todos los códigos con sus descripciones
usados tanto para manipulación como consultas de products y customers

* GENDER
* PRODUCT_TYPE
* CURRENCY
* BRANCH

### RemoteControl
> Api: http://localhost:8080/remote/customers/{customerId}

> Api tests: src/test/java/http/remote-api-test.http

A modo de demostración, este controller expone una única operación (GET)
que llama a los otros dos controllers para armar un customer con todos sus productos



### JUnit test
Se incluyen tests unitarios para el ProductMatcher
