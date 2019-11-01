package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Interfaz_Renovar_Licencia   {
    private JPanel rootPane;
    private JTextField tf_dni;
    private JTextField tf_nombre;
    private JTextField tf_apellido;
    private JCheckBox aCheckBox;
    private JCheckBox bCheckBox;
    private JCheckBox cCheckBox;
    private JCheckBox dCheckBox;
    private JCheckBox eCheckBox;
    private JCheckBox fCheckBox;
    private JCheckBox gCheckBox;
    private JTextField tf_desde;
    private JTextField tf_hasta;
    private JTable table_resultados;
    private JButton volverButton;
    private JButton buscarButton;
    private JButton renovarButton;
    private JScrollPane scrollPane;

    public JPanel getPane(){
        return rootPane;
    }

    public Interfaz_Renovar_Licencia() {
        String[] columns = {"DNI titular","Nombre titular", "Apellido titular", "Clase(s)", "Fecha Alta" };
        Object[][] data = {{}};

        TableModel tableModel;
        tableModel = new DefaultTableModel(columns,0);
        table_resultados= new JTable(tableModel);
        scrollPane.setViewportView(table_resultados);

/*        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);*/

    }

//    public static void main(String[] args) {
//        new Interfaz_Renovar_Licencia();
//    }
}
