package conexion; 
// Impottamos las librerias
import java.sql.DriverManager; 
import java.sql.Connection; 
import java.util.Properties; 
import java.io.InputStream; 
public class CreateConnection{
//  Agregamos los atributos privados y propios de conexion 
    private String hostname; 
    private String port; 
    private String database; 
    private String username; 
    private String password; 
    // Agregamos nuestro constructor
    public CreateConnection(){  
        // Preparamos la conexion y usaremos el try cath para el manejo de errores
        Properties config = new Properties();  
        try(InputStream archivo = getClass()
                .getClassLoader()
                .getResourceAsStream("conexion/db_properties.properties")){
        if(archivo == null){ 
            System.out.println("No encontrado en pathClass");
            return; 
        } 
        config.load(archivo); 
        hostname = config.getProperty("hostname");
        port = config.getProperty("port"); 
        database = config.getProperty("database"); 
        username = config.getProperty("username"); 
        password = config.getProperty("password"); 
            System.out.println("Cargando la conexion...");
        }catch(Exception e){ 
            e.printStackTrace();
        }
    } 
    public Connection getConnection(){
    Connection con = null; 
    try{
        String url = "jdbc:postgresql://"+hostname+":"+port+"/"+database; 
        con = DriverManager.getConnection(url, username, password); 
        System.out.println("Conexion exitosa a postgres!");
    }catch(Exception e){  
        System.out.println("Error de conexion");
        e.printStackTrace();
    }
    return con;
    }
}