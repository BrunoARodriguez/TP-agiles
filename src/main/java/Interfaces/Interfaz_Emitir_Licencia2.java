package Interfaces;

import LogicaDeNegocios.DTOs.ContribuyenteDTO;
import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import gestores.GestorLicencia;
import gestores.GestorTitular;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDateTime;

public class Interfaz_Emitir_Licencia2 extends JPanel {

    private JTextField txt_dni;
    private JTextField txt_apellido;
    private JTextField txt_nombre;
    private JTextField txt_fecha_nacimiento;
    private JTextField txt_domocilio;
    private JCheckBox esDonanteCheckBox;
    private JTextArea txt_observaciones;
    private JCheckBox checkBoxA;
    private JCheckBox checkBoxB;
    private JCheckBox checkBoxC;
    private JCheckBox checkBoxD;
    private JCheckBox checkBoxE;
    private JCheckBox checkBoxF;
    private JCheckBox checkBoxG;
    private JTextField DDMMAATextField;
    private JTextField DDMMAATextField1;
    private JButton buttonAceptar;
    private JButton buttonCancelar;
    private JPanel rootPane;
    private JTextField tfTipoSangre;

    public Interfaz_Emitir_Licencia2(final MainFrame frame) {

        if (GestorTitular.titularAux != null) {
            txt_dni.setEditable(false);
        }

        cargar();

        DDMMAATextField.setText(LocalDateTime.now().toString());

        txt_dni.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                try {
                    GestorTitular.buscarTitularDTO(Long.valueOf(txt_dni.getText()));
                } catch (Exception e) {
                    //TODO cambiar esto por ventana emergente
                    System.out.println("Error parseando documento.");
                    e.printStackTrace();
                    txt_dni.setText("");
                    GestorTitular.titularAux = null;
                }
                cargar();
            }
        });


        buttonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LicenciaDTO licenciaDTO = new LicenciaDTO();
                if (checkBoxA.isSelected()) {
                    licenciaDTO.getClaseLicencias().add(ClaseLicencia.CLASE_A);
                }
                if (checkBoxB.isSelected()) {
                    licenciaDTO.getClaseLicencias().add(ClaseLicencia.CLASE_B);
                }
                if (checkBoxC.isSelected()) {
                    licenciaDTO.getClaseLicencias().add(ClaseLicencia.CLASE_C);
                }
                if (checkBoxD.isSelected()) {
                    licenciaDTO.getClaseLicencias().add(ClaseLicencia.CLASE_D);
                }
                if (checkBoxE.isSelected()) {
                    licenciaDTO.getClaseLicencias().add(ClaseLicencia.CLASE_E);
                }
                if (checkBoxF.isSelected()) {
                    licenciaDTO.getClaseLicencias().add(ClaseLicencia.CLASE_F);
                }
                if (checkBoxG.isSelected()) {
                    licenciaDTO.getClaseLicencias().add(ClaseLicencia.CLASE_G);
                }
                licenciaDTO.setObservacionesLicencia(txt_observaciones.getText());
                licenciaDTO.setFechaAltaLicencia(LocalDateTime.now());
                //TODO hacer cuadro de dialogo de error y exito Incliodos los campos incompletos del if.
                if (GestorTitular.titularAux != null && !licenciaDTO.getClaseLicencias().isEmpty() && !txt_observaciones.getText().isEmpty()) {
                    licenciaDTO.setFechaVencimientoLicencia(LocalDateTime.parse(DDMMAATextField1.getText()));
                    licenciaDTO.setDNI(Long.parseLong(txt_dni.getText()));
                    switch (GestorTitular.crearTitular(GestorTitular.titularAux)) {
                        case 0:
                            System.out.println("Titular creado");
                            switch (GestorLicencia.crearLicencia(licenciaDTO)) {
                                case 0:
                                    System.out.println("Exito");
                                    break;
                                case -2:
                                    System.out.println("Error al guardar en BD");
                                    break;
                            }
                            break;
                        case -3:
                            System.out.println("Titular ya existia");
                            switch (GestorLicencia.crearLicencia(licenciaDTO)) {
                                case 0:
                                    System.out.println("Exito");
                                    frame.cambiarPanel(MainFrame.PANE_MENU_OPERADOR);
                                    break;
                                case -2:
                                    System.out.println("Error al guardar en BD");
                                    break;
                            }
                            break;
                        case -1:
                            System.out.println("Documento Incorrecto");
                            break;
                        case -2:
                            System.out.println("Error en base de datos guardando titular.");
                            break;
                    }
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JDialogCancelar c = new JDialogCancelar(frame);
                if (c.fueCancelado()) {
                    GestorTitular.titularAux=null;
                    frame.cambiarPanel(MainFrame.PANE_MENU_OPERADOR);
                }
            }
        });
    }

    public void cargar() {
        if (GestorTitular.titularAux != null) {
            txt_dni.setText(GestorTitular.titularAux.getContribuyente().getNroDocumento().toString());
            txt_nombre.setText(GestorTitular.titularAux.getContribuyente().getNombre());
            txt_apellido.setText(GestorTitular.titularAux.getContribuyente().getApellido());
            txt_domocilio.setText(GestorTitular.titularAux.getContribuyente().getDomicilio());
            txt_fecha_nacimiento.setText(GestorTitular.titularAux.getContribuyente().getFechaDeNacimiento().toString());
            esDonanteCheckBox.setSelected(GestorTitular.titularAux.getDonante());
            tfTipoSangre.setText(GestorTitular.titularAux.getTipoSangre().getName());
            if(GestorTitular.titularAux.getTieneLicencias()) {
                DDMMAATextField1.setText(GestorLicencia.calcularVigencia(GestorTitular.titularAux.getContribuyente().getFechaDeNacimiento(), false, LocalDateTime.parse(DDMMAATextField.getText())).toString());
            }else{
                DDMMAATextField1.setText("");
            }
        }
        else{

            txt_dni.setText("");
            txt_nombre.setText("");
            txt_apellido.setText("");
            txt_domocilio.setText("");
            txt_fecha_nacimiento.setText("");
            esDonanteCheckBox.setSelected(false);
            tfTipoSangre.setText("");
            DDMMAATextField1.setText("");
        }
    }


    public JPanel getPane() {
        return rootPane;
    }


}
