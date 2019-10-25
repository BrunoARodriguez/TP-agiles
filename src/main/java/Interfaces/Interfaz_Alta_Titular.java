package Interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz_Alta_Titular {
    private JTextField tfNumeroDocumento;
    private JTextField tfApellido;
    private JTextField tfNombre;
    private JTextField tfFDeNac;
    private JTextField tfDomicilio;
    private JComboBox cbTipoSangre;
    private JTextArea taObservaciones;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel rootPane;
    private JButton buscarDatosButton;
    private JCheckBox esDonanteCheckBox;

    public Interfaz_Alta_Titular() {
        buscarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sNroDocumento=tfNumeroDocumento.getText();
                Integer nroDocumento;
                if(sNroDocumento != null){
                    Integer.valueOf(sNroDocumento);
                }

                //acá tenemos que buscar nroDocumento enla base de datos y traer toda la info

                

            }
        });
    }

    public JPanel getPane(){
        return rootPane;
    }
}
