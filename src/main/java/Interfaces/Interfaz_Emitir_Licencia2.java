package Interfaces;

import LogicaDeNegocios.DTOs.CarnetDTO;
import LogicaDeNegocios.DTOs.ContribuyenteDTO;
import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Exceptions.ExcepcionCrearLicencia;
import LogicaDeNegocios.Exceptions.ExcepcionCrearTitular;
import gestores.GestorBD;
import gestores.GestorLicencia;
import gestores.GestorTitular;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Interfaz_Emitir_Licencia2(final MainFrame frame) {


        if (GestorTitular.titularAux != null) {
            txt_dni.setEditable(false);
        }

        AbstractDocument doc = (AbstractDocument) txt_dni.getDocument();
        doc.setDocumentFilter(new LimiteTexto(8));

        txt_observaciones.setLineWrap(true);
        txt_observaciones.setWrapStyleWord(true);
        AbstractDocument doc_1 = (AbstractDocument) txt_observaciones.getDocument();
        doc_1.setDocumentFilter(new LimiteTexto(250));
        txt_observaciones.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        txt_observaciones.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

        DDMMAATextField.setText(dateTimeFormatter.format(LocalDateTime.now()));

        cargar();

        txt_dni.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (txt_dni.isEditable()) {
                    String dniString = txt_dni.getText().toString();
                    if (!dniString.isEmpty()) {
                        GestorTitular.buscarTitularDTO(Long.valueOf(txt_dni.getText()));
                        if (GestorTitular.titularAux == null) {
                            JOptionPane.showMessageDialog(frame, "Documento Incorrecto", "Licencia", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("Error parseando documento.");
                        JOptionPane.showMessageDialog(frame, "Documento Incorrecto", "Licencia", JOptionPane.ERROR_MESSAGE);
                        txt_dni.setText("");
                        GestorTitular.titularAux = null;
                    }
                    cargar();
                }
            }
        });

        buttonAceptar.addActionListener(actionEvent -> {
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

            if (GestorTitular.titularAux != null && !licenciaDTO.getClaseLicencias().isEmpty() && !txt_observaciones.getText().isEmpty()) {
                licenciaDTO.setFechaVencimientoLicencia(LocalDateTime.parse((DDMMAATextField1.getText()), dateTimeFormatter));
                licenciaDTO.setDNI(Long.parseLong(txt_dni.getText()));

                try {

                    GestorTitular.crearTitular(GestorTitular.titularAux);

                    GestorLicencia.crearLicencia(licenciaDTO);
                    JOptionPane.showMessageDialog(frame, "Licencia con id " + licenciaDTO.getIdLicencia() + " creada con exito.", "Operacion Realizada", JOptionPane.INFORMATION_MESSAGE);
                    CarnetDTO carnetDTO = GestorLicencia.buscarCarnetDTO(licenciaDTO.getIdLicencia());
                    carnetDTO.setComprobante(licenciaDTO.getComprobante());
                    ArrayList listaCarnetImprimir = new ArrayList<CarnetDTO>();
                    listaCarnetImprimir.add(carnetDTO);
                    frame.cambiarPanelConLicencias(MainFrame.PANE_VER_FORMATO_LICENCIA, listaCarnetImprimir);
                    GestorTitular.titularAux = null;

                } catch (ExcepcionCrearLicencia e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Error al crear licencia", JOptionPane.ERROR_MESSAGE);
                } catch (ExcepcionCrearTitular e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Error al crear Titular", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(frame, "Complete los campos requeridos.", "Campos vacios.", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JDialogCancelar c = new JDialogCancelar(frame);
                if (c.fueCancelado()) {
                    GestorTitular.titularAux = null;
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
            txt_fecha_nacimiento.setText(dateTimeFormatter.format(GestorTitular.titularAux.getContribuyente().getFechaDeNacimiento()));
            esDonanteCheckBox.setSelected(GestorTitular.titularAux.getDonante());
            tfTipoSangre.setText(GestorTitular.titularAux.getTipoSangre().getName());

            if (GestorTitular.titularAux.getTieneLicencias()) {
                DDMMAATextField1.setText(dateTimeFormatter.format(GestorLicencia.calcularVigencia(GestorTitular.titularAux.getContribuyente().getFechaDeNacimiento(), false, LocalDateTime.parse((DDMMAATextField.getText()), dateTimeFormatter))));
            } else {
                DDMMAATextField1.setText(dateTimeFormatter.format(GestorLicencia.calcularVigencia(GestorTitular.titularAux.getContribuyente().getFechaDeNacimiento(), true, LocalDateTime.parse((DDMMAATextField.getText()), dateTimeFormatter))));
            }
        } else {
            txt_dni.setEditable(true);
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
