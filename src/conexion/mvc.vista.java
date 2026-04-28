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
