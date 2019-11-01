package Interfaces;

import LogicaDeNegocios.DTOs.ContribuyenteDTO;
import gestores.GestorTitular;

import javax.swing.*; import javax.swing.JPanel;
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
                String sNroDocumento = tfNumeroDocumento.getText();
                Long nroDocumento;
                if (sNroDocumento != null) {
                    nroDocumento = Long.valueOf(sNroDocumento);
                    ContribuyenteDTO contribuyenteDTO = new ContribuyenteDTO();
                    contribuyenteDTO.setNroDocumento(nroDocumento);
                    contribuyenteDTO = GestorTitular.buscarContribuyente(contribuyenteDTO);

                    //TODO refactorizar el mensaje de error, a debatir con el gurpo la mejor opcion
                    if (contribuyenteDTO == null) {
                        tfNumeroDocumento.setText("Documento no encontrado");
                        tfApellido.setText("");
                        tfNombre.setText("");
                        //TODO refactorizar el formateo de fecha de nacimiento
                        tfFDeNac.setText(contribuyenteDTO.getFechaDeNacimiento().getDayOfMonth()+1 + "/" + contribuyenteDTO.getFechaDeNacimiento().getMonthValue() + "/" + contribuyenteDTO.getFechaDeNacimiento().getYear());
                        tfDomicilio.setText(contribuyenteDTO.getDomicilio());
                    } else {
                        tfApellido.setText(contribuyenteDTO.getApellido());
                        tfNombre.setText(contribuyenteDTO.getNombre());
                        //TODO refactorizar el formateo de fecha de nacimiento
                        tfFDeNac.setText(contribuyenteDTO.getFechaDeNacimiento().getDayOfMonth()+1 + "/" + contribuyenteDTO.getFechaDeNacimiento().getMonthValue() + "/" + contribuyenteDTO.getFechaDeNacimiento().getYear());
                        tfDomicilio.setText(contribuyenteDTO.getDomicilio());
                    }
                }
            }
        });


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        taObservaciones.setToolTipText("Escribi algo wey");
    }

    public JPanel getPane(){
        return rootPane;
    }
}
