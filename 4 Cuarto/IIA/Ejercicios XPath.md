# Ejercicios XPath

## 1) Escribe las rutas siguientes.

|**Descripción**|**Ruta**|
| :-: | :-: |
|ISBN de todos los libros|<p></p><p>//book/@isbn</p><p></p>|
|Títulos y precios de todos los libros|<p></p><p>//book/price | //book/title</p><p></p>|
|Los nodos títulos de los libros que valen más de 35.00 |<p></p><p>//book[price > 45]/title</p><p></p>|
|Libro con el valor del atributo isbn 111111|<p>//book[@isbn = 111111]</p><p></p><p></p>|

## 2) Describe el significado de las siguientes rutas.

|**Descripción**|**Ruta**|
| :-: | :-: |
|Todos los nodos de tipo texto de los libros que están dentro de una librería. (No tienen ninguno)|<p>/bookstore/book/text()</p><p></p><p></p>|
|Todos los atributos de todos los libros y sus sucesores que están en una librería.|<p>/bookstore/book/title/..//@\*</p><p></p><p></p>|
|El último titulo de cada libro dentro de una librería.|<p>/bookstore/book/title[last()]</p><p></p><p></p>|
|El penultimo libro de todo el documento.|<p>//book[last()-1]</p><p></p><p></p>|

