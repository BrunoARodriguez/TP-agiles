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
import java.time.LocalDateTime;

public class Interfaz_Emitir_Licencia2 extends JPanel{

    private JTextField txt_dni;
    private JTextField txt_apellido;
    private JTextField txt_nombre;
    private JTextField txt_fecha_nacimiento;
    private JTextField txt_domocilio;
    private JCheckBox esDonanteCheckBox;
    private JComboBox comboBox1;
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

    public Interfaz_Emitir_Licencia2(final MainFrame frame) {

        if (!(GestorTitular.titularAux == null)) {
            TitularDTO titular = new TitularDTO();
            ContribuyenteDTO contribuyente = new ContribuyenteDTO();
            contribuyente.setNroDocumento(GestorTitular.titularAux.getContribuyente().getNroDocumento());
            contribuyente.setNombre(GestorTitular.titularAux.getContribuyente().getNombreContribuyente());
            contribuyente.setApellido(GestorTitular.titularAux.getContribuyente().getApellidoContribuyente());
            contribuyente.setDomicilio(GestorTitular.titularAux.getContribuyente().getDomicilioContribuyente());
            contribuyente.setFechaDeNacimiento(GestorTitular.titularAux.getContribuyente().getFechaNacimientoContribuyente());
titular.setContribuyente(contribuyente);
titular.setTipoSangre(GestorTitular.titularAux.getTipoSangre());
titular.setObservaciones(GestorTitular.titularAux.getObservaciones());
titular.setDonante(GestorTitular.titularAux.getDonante());
            txt_dni.setText(titular.getContribuyente().getNroDocumento().toString());
        txt_nombre.setText(titular.getContribuyente().getNombre());
        txt_apellido.setText(titular.getContribuyente().getApellido());
        txt_domocilio.setText(titular.getContribuyente().getDomicilio());
        txt_observaciones.setText(titular.getObservaciones());
        txt_fecha_nacimiento.setText(titular.getContribuyente().getFechaDeNacimiento().toString());
        esDonanteCheckBox.setSelected(titular.getDonante());

        }

        buttonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LicenciaDTO licenciaDTO = new LicenciaDTO();
                licenciaDTO.setDNI(Long.parseLong(txt_dni.getText().toString()));
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
                licenciaDTO.setObservacionesLicencia(txt_observaciones.getText().toString());
                licenciaDTO.setFechaAltaLicencia(LocalDateTime.now());
                switch (GestorLicencia.crearLicencia(licenciaDTO)) {
                    case 0:
                        System.out.println("Exito");
                        break;
                    case -2:
                        System.out.println("Error al guardar");
                        break;
                    case -3:
                        System.out.println("Error en la base de datos");
                        break;
                }

            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JDialogCancelar c = new JDialogCancelar(frame);
                if(c.fueCancelado()) {
                    frame.backPreviousPane();
                }
            }
        });
    }



    public JPanel getPane(){
        return rootPane;
    }



}
