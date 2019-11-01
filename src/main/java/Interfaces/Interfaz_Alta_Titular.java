package Interfaces;

import LogicaDeNegocios.DTOs.ContribuyenteDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Enumerations.TipoSangre;
import gestores.GestorTitular;

import javax.swing.*; import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

        TipoSangre[] tipoSangre = TipoSangre.values();
        for(TipoSangre t : tipoSangre){
            cbTipoSangre.addItem(t.getName());
        }

        buscarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sNroDocumento = tfNumeroDocumento.getText().toString();
                Long nroDocumento;
                if (sNroDocumento != "") {
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
                        tfFDeNac.setText("");
                        tfDomicilio.setText("");
                    } else {
                        tfApellido.setText(contribuyenteDTO.getApellido());
                        tfNombre.setText(contribuyenteDTO.getNombre());
                        //TODO refactorizar el formateo de fecha de nacimiento
                        tfFDeNac.setText(contribuyenteDTO.getFechaDeNacimiento().getDayOfMonth()+1 + "/" + contribuyenteDTO.getFechaDeNacimiento().getMonthValue() + "/" + contribuyenteDTO.getFechaDeNacimiento().getYear());
                        tfDomicilio.setText(contribuyenteDTO.getDomicilio());
                    }
                }
                else{
                    tfNumeroDocumento.setText("Documento no encontrado");
                    tfApellido.setText("");
                    tfNombre.setText("");
                    //TODO refactorizar el formateo de fecha de nacimiento
                    tfFDeNac.setText("");
                    tfDomicilio.setText("");
                }
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TitularDTO titularDTO = new TitularDTO();
                titularDTO.setDni(Long.valueOf(tfNumeroDocumento.getText()));
                titularDTO.setDonante(esDonanteCheckBox.isSelected());
                titularDTO.setObservaciones(taObservaciones.getText());
                for(TipoSangre t : TipoSangre.values()){
                    if(t.getName().equals(cbTipoSangre.getSelectedItem())){
                        titularDTO.setTipoSangre(t);
                    }
                }
                switch (GestorTitular.crearTitular(titularDTO)){
                    case 0: System.out.println("Titular creado con exito.");break;
                    case -1: System.out.println("Contribuyente no encontrado en la base de datos.");break;
                    case -2: System.out.println("Error guardando titular en base de datos.");break;
                    case -3: System.out.println("Titular ya existe con este documeto."); break;
                }
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
