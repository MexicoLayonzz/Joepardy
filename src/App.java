import backend.*;
import frontend.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class App 
{
    public static void main(String[] args) throws Exception 
    {

        JOptionPane.showMessageDialog(null, "Bienvenido a la aplicación de creación de preguntas SQL.\n" +
                "Esta aplicación te permitirá crear preguntas SQL y guardarlas en un archivo JSON.\n" +
                "¡Disfruta creando tus preguntas!");
        //-----------------------------Creación de la Datos-----------------------------
        //Creación de una materia
        Materia materia = new Materia("Bases de datos");
        
        
        ArrayList<Categoria> categorias = new ArrayList<Categoria>() {{
            add(new Categoria("Select"));
            add(new Categoria("Where"));
            add(new Categoria("Order by"));
            add(new Categoria("Limit"));
            add(new Categoria("Distinct"));
            add(new Categoria("In/Not In"));
            add(new Categoria("Between"));
            add(new Categoria("Like"));
            add(new Categoria("Funciones Agregadas"));
            add(new Categoria("Join"));
        }};
        
        // Agregar las categorías a la materia
        materia.agregarCategorias(categorias);

        //Creación de dificultades
        Dificultad facil = new Dificultad(100);
        Dificultad medio = new Dificultad(200);
        Dificultad dificil = new Dificultad(300);
        Dificultad muyDificil = new Dificultad(400);
        Dificultad extremo = new Dificultad(500);

        //Creación de preguntas
        ArrayList<Pregunta> select = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra todas las columnas de la tabla cliente.", "SELECT * FROM cliente;", facil));
            add(Pregunta.agregarPregunta("Muestra solo las columnas nombre y email de la tabla cliente.", "SELECT nombre, email FROM cliente;", medio));
            add(Pregunta.agregarPregunta("Muestra la palabra 'Hola' como una columna llamada saludo.", "SELECT 'Hola' AS saludo;", dificil));
            add(Pregunta.agregarPregunta("Muestra el valor 2025-01-01 como una columna llamada fecha_inicio.", "SELECT DATE '2025-01-01' AS fecha_inicio;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra el resultado de conectar el nombre y apellido del cliente separados por un espacio.", "SELECT nombre || ' ' || apellido AS nombre_completo FROM clientes;", extremo));
        }};
        
        ArrayList<Pregunta> where = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra los productos cuyo precio sea exactamente 100.", "SELECT * FROM producto WHERE precio = 100;", facil));
            add(Pregunta.agregarPregunta("Muestra los clientes que viven en la ciudad de 'Monterrey'.", "SELECT * FROM cliente WHERE ciudad = 'Monterrey';", medio));
            add(Pregunta.agregarPregunta("Muestra los pedidos cuyo total sea diferente de 500.", "SELECT * FROM pedido WHERE total != 500;", dificil));
            add(Pregunta.agregarPregunta("Muestra las ventas con un total mayor a 1000 y menor a 5000.", "SELECT * FROM venta WHERE total > 1000 AND total < 5000;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra los registros donde el campo teléfono no es nulo y el campo email termina en .com.", "SELECT * FROM cliente WHERE telefono IS NOT NULL AND email LIKE '%.com';", extremo));
        }};

        ArrayList<Pregunta> orderBy = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra todos los productos ordenados por nombre (ascendente).", "SELECT * FROM producto ORDER BY nombre ASC;", facil));
            add(Pregunta.agregarPregunta("Muestra los productos ordenados por precio de mayor a menor.", "SELECT * FROM producto ORDER BY precio DESC;", medio));
            add(Pregunta.agregarPregunta("Muestra los alumnos ordenados por apellido y luego por nombre.", "SELECT * FROM alumno ORDER BY apellido ASC, nombre ASC;", dificil));
            add(Pregunta.agregarPregunta("Muestra las películas ordenadas por la duración (de menor a mayor).", "SELECT * FROM pelicula ORDER BY duracion ASC;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra los artículos ordenados por la cantidad de caracteres en el nombre.", "SELECT * FROM articulo ORDER BY LENGTH(nombre);", extremo));
        }};
        
        ArrayList<Pregunta> limit = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra los primeros 5 registros de la tabla usuarios.", "SELECT * FROM usuario LIMIT 5;", facil));
            add(Pregunta.agregarPregunta("Muestra los primeros 3 productos con menor precio.", "SELECT * FROM producto ORDER BY precio ASC LIMIT 3;", medio));
            add(Pregunta.agregarPregunta("Muestra los siguientes 10 clientes después de omitir los primeros 5.", "SELECT * FROM cliente OFFSET 5 LIMIT 10;", dificil));
            add(Pregunta.agregarPregunta("Muestra 1 solo empleado con el mayor salario.", "SELECT * FROM empleado ORDER BY salario DESC LIMIT 1;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra los últimos 2 pedidos (considerando fecha de pedido descendente).", "SELECT * FROM pedido ORDER BY fecha_pedido DESC LIMIT 2;", extremo));
        }};
        
        ArrayList<Pregunta> distinct = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra todas las ciudades distintas de la tabla clientes.", "SELECT DISTINCT ciudad FROM cliente;", facil));
            add(Pregunta.agregarPregunta("Muestra los tipos de producto únicos en la tabla productos.", "SELECT DISTINCT tipo FROM producto;", medio));
            add(Pregunta.agregarPregunta("Muestra combinaciones distintas de nombre y apellido en la tabla personas.", "SELECT DISTINCT nombre, apellido FROM persona;", dificil));
            add(Pregunta.agregarPregunta("Muestra la cantidad de correos electrónicos únicos en la tabla usuarios.", "SELECT COUNT(DISTINCT email) FROM usuario;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra todas las fechas de pedidos distintas en la tabla ventas.", "SELECT DISTINCT fecha FROM venta;", extremo));
        }};
        ArrayList<Pregunta> inNotIn = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra los productos cuyo ID es 1, 3 o 7.", "SELECT * FROM producto WHERE id IN (1,3,7);", facil));
            add(Pregunta.agregarPregunta("Muestra los alumnos que están en los grupos 101, 103 y 105.", "SELECT * FROM alumno WHERE grupo IN (101, 103, 105);", medio));
            add(Pregunta.agregarPregunta("Muestra los empleados que no trabajan en los departamentos 2 ni 4.", "SELECT * FROM empleado WHERE departamento_id NOT IN (2, 4);", dificil));
            add(Pregunta.agregarPregunta("Muestra los artículos cuya categoría se encuentra en una subconsulta que selecciona categorías activas.", "SELECT * FROM articulo WHERE categoria_id IN (SELECT id FROM categorias WHERE activa = TRUE);", muyDificil));
            add(Pregunta.agregarPregunta("Muestra los usuarios cuyo ID no aparece en la tabla logins.", "SELECT * FROM usuario WHERE id NOT IN (SELECT usuario_id FROM login);", extremo));
        }};
        
        ArrayList<Pregunta> between = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra los productos con precio entre 100 y 200.", "SELECT * FROM producto WHERE precio BETWEEN 100 AND 200;", facil));
            add(Pregunta.agregarPregunta("Muestra los eventos con fecha entre '2023-01-01' y '2023-12-31'.", "SELECT * FROM evento WHERE fecha BETWEEN '2023-01-01' AND '2023-12-31';", medio));
            add(Pregunta.agregarPregunta("Muestra las edades entre 18 y 25 de la tabla personas.", "SELECT * FROM persona WHERE edad BETWEEN 18 AND 25;", dificil));
            add(Pregunta.agregarPregunta("Muestra los libros con entre 100 y 300 páginas.", "SELECT * FROM libro WHERE paginas BETWEEN 100 AND 300;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra los salarios que estén entre 8000 y 12000, inclusive.", "SELECT * FROM empleado WHERE salario BETWEEN 8000 AND 12000;", extremo));
        }};
        
        ArrayList<Pregunta> like = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra los productos cuyo nombre empieza con 'C'.", "SELECT * FROM producto WHERE nombre LIKE 'C%';", facil));
            add(Pregunta.agregarPregunta("Muestra los usuarios cuyo correo contiene 'gmail'.", "SELECT * FROM usuario WHERE email LIKE '%gmail%';", medio));
            add(Pregunta.agregarPregunta("Muestra los clientes cuyo nombre termina en 'o'.", "SELECT * FROM cliente WHERE nombre LIKE '%o';", dificil));
            add(Pregunta.agregarPregunta("Muestra los artículos cuyo código tiene exactamente 5 caracteres.", "SELECT * FROM articulo WHERE LENGTH(codigo) = 5;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra los estudiantes cuyo nombre contiene doble letra 'a'.", "SELECT * FROM estudiante WHERE nombre LIKE '%aa%';", extremo));
        }};

        ArrayList<Pregunta> funcionesAgregadas = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra la cantidad total de clientes.", "SELECT COUNT(*) FROM clientes;", facil));
            add(Pregunta.agregarPregunta("Muestra el promedio de precios en la tabla productos.", "SELECT AVG(precio) FROM producto;", medio));
            add(Pregunta.agregarPregunta("Muestra la cantidad de empleados por cada departamento.", "SELECT departamento_id, COUNT(*) FROM empleado GROUP BY departamento_id;", dificil));
            add(Pregunta.agregarPregunta("Muestra la suma total de ventas agrupadas por mes.", "SELECT DATE_TRUNC('month', fecha) AS mes, SUM(total) FROM venta GROUP BY DATE_TRUNC('month', fecha);", muyDificil));
            add(Pregunta.agregarPregunta("Muestra los departamentos con más de 10 empleados.", "SELECT departamento_id FROM empleado GROUP BY departamento_id HAVING COUNT(*)>10;", extremo));
        }};

        ArrayList<Pregunta> join = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("Muestra todos los pedidos con los nombres de los clientes.", "SELECT pedidos.id, clientes.nombre FROM pedido JOIN cliente ON pedidos.cliente_id = clientes.id;", facil));
            add(Pregunta.agregarPregunta("Muestra los empleados junto con el nombre de su departamento.", "SELECT empleados.nombre, departamentos.nombre FROM empleado JOIN departamento ON empleados.departamento_id = departamentos.id;", medio));
            add(Pregunta.agregarPregunta("Muestra los productos vendidos en cada pedido.", "SELECT pedidos.id, productos.nombre FROM pedido JOIN detalles_pedido ON pedidos.id = detalles_pedido.pedido_id JOIN producto ON detalles_pedido.producto_id = productos.id;", dificil));
            add(Pregunta.agregarPregunta("Muestra el nombre del estudiante y el nombre del curso en el que está inscrito.", "SELECT estudiantes.nombre, cursos.nombre FROM inscripcion JOIN estudiante ON inscripciones.estudiante_id = estudiantes.id JOIN curso ON inscripciones.curso_id = cursos.id;", muyDificil));
            add(Pregunta.agregarPregunta("Muestra todos los artículos con el nombre de su categoría.", "SELECT a.nombre AS articulo, c.nombre AS categoria FROM articulo a JOIN categoria c ON a.categoria_id = c.id;", extremo));
        }};
        // Continúa con las demás categorías: IN / NOT IN, BETWEEN, LIKE, Funciones agregadas, JOIN.

        ArrayList<ArrayList<Pregunta>> preguntasPorCategoria = new ArrayList<ArrayList<Pregunta>>() {{
            add(select);
            add(where);
            add(orderBy);
            add(limit);
            add(distinct);
            add(inNotIn);
            add(between);
            add(like);
            add(funcionesAgregadas);
            add(join);
            // Agrega más listas de preguntas aquí si es necesario
        }};

        // Asignar las preguntas a las categorías en orden
        for (int i = 0; i < categorias.size() && i < preguntasPorCategoria.size(); i++) {
            categorias.get(i).agregarPreguntas(preguntasPorCategoria.get(i));
        }

        //-----------------------------Fin de la Datos-----------------------------

        //-----------------------------Creación de Archivo----------------------------- 
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");

        String nombreFichero = "storage/"+ nombre +".json"; // Ruta del archivo de texto
        
        CreacionArchivo(nombreFichero, materia); // Crear el archivo con el contenido de la materia
        
        //------------------------------Fin de Archivo -----------------------------

        //------------------------------Mostrar la Ventana-----------------------------
        mostrarVentanaPrincipal();
        //-------------------------------Fin de la Ventana-----------------------------


    }

    public static void mostrarVentanaPrincipal() 
    {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }

    public static void CreacionArchivo(String rutaFichero, Materia materia) 
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Crear un objeto Gson para convertir a JSON

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) 
        {
            gson.toJson(materia, bw); // Escribir el contenido en el archivo
        } catch (IOException e) 
        {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    public static void leerArchivo(String rutaFichero) 
    {
        Gson gson = new Gson(); // Crear un objeto Gson para leer el JSON

        try(BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) 
        {
            Materia materia = gson.fromJson(br, Materia.class); // Leer el contenido del archivo
            System.out.println(materia); // Mostrar el contenido en consola
        } catch (IOException e) 
        {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}
