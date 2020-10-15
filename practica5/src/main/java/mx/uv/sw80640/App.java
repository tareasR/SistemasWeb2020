package mx.uv.sw80640;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        port(80);
        
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));



        get("/", (request, response) -> "Hola desde Spark");
        get("/hola", (request, response) -> {
            System.out.println("Request: " + request.queryParams());
            System.out.println("Request: " + request.queryParams("PrmEmail"));
            System.out.println("Request: " + request.queryParams("PrmPassword"));
            return "Hola " + request.queryParams("PrmEmail") + " desde Spark";
        });

        post("/adios", (request, response) -> {
            System.out.println("Request: " + request.queryParams());
            System.out.println("Request: " + request.queryParams("PrmEmail"));
            System.out.println("Request: " + request.queryParams("PrmPassword"));
            System.out.println("Request: " + request.body()); // pendiente ver como llenar el body
            System.out.println("Request: " + request.contentType());
            return "Hola " + request.queryParams("PrmEmail") + " desde Spark";
        });
    }
}
