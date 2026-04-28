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



















































package mvc.vista;

import mvc.controlador.empleadoControlador;
import mvc.modelo.empleadoModelo;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class empleadoVista extends javax.swing.JFrame {

    empleadoControlador controlador = new empleadoControlador();

    public empleadoVista() {
        initComponents();
        cargarTabla();
    }

    
    public void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        controlador.ObtenerTodos().forEach(emp ->
            modelo.addRow(new Object[]{
                emp.getId(), emp.getNombre(), emp.getApellido(),
                emp.getNit(), emp.getSalario(),
                emp.getDireccion(), emp.getTelefono()
            })
        );
    }

    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        controlador.agregar(
            txtNombre.getText(),
            txtApellido.getText(),
            txtNit.getText(),
            Double.parseDouble(txtSalario.getText()),
            txtDireccion.getText(),
            txtTelefono.getText()
        );
        cargarTabla();
    }

    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {
            javax.swing.JTextField[] campos = {
                txtId, txtNombre, txtApellido, txtNit,
                txtSalario, txtDireccion, txtTelefono
            };

            for (int i = 0; i < campos.length; i++) {
                campos[i].setText(jTable1.getValueAt(fila, i).toString());
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new empleadoVista().setVisible(true));
    }

    private void initComponents() {}

    private javax.swing.JButton btnAgregar;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtApellido, txtDireccion, txtId,
            txtNit, txtNombre, txtSalario, txtTelefono;
}
