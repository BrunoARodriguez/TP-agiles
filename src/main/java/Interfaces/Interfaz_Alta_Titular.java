package Interfaces;

import LogicaDeNegocios.DTOs.ContribuyenteDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Entidades.Contribuyente;
import LogicaDeNegocios.Enumerations.TipoSangre;
import gestores.GestorTitular;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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

    public Interfaz_Alta_Titular(final MainFrame frame) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        TipoSangre[] tipoSangre = TipoSangre.values();
        for (TipoSangre t : tipoSangre) {
            cbTipoSangre.addItem(t.getName());
        }

        AbstractDocument doc = (AbstractDocument) tfNumeroDocumento.getDocument();
        doc.setDocumentFilter(new LimiteTexto(8));

        taObservaciones.setColumns(0);
        taObservaciones.setLineWrap(true);
        taObservaciones.setWrapStyleWord(true);
        AbstractDocument doc_1 = (AbstractDocument) taObservaciones.getDocument();
        doc_1.setDocumentFilter(new LimiteTexto(250));
        taObservaciones.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        taObservaciones.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

        buscarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sNroDocumento = tfNumeroDocumento.getText().toString();
                Long nroDocumento;
                if (!sNroDocumento.isEmpty()) {
                    nroDocumento = Long.valueOf(sNroDocumento);
                    ContribuyenteDTO contribuyenteDTO = new ContribuyenteDTO();
                    contribuyenteDTO.setNroDocumento(nroDocumento);
                    contribuyenteDTO = GestorTitular.buscarContribuyente(contribuyenteDTO);

                    if (contribuyenteDTO == null) {

                        System.out.println("Titular no encontrado");
                        JOptionPane.showMessageDialog(frame, "Documento Incorrecto", "Titular", JOptionPane.ERROR_MESSAGE);
                        tfNumeroDocumento.setText("");
                        tfApellido.setText("");
                        tfNombre.setText("");
                        tfFDeNac.setText("");
                        tfDomicilio.setText("");
                    } else {
                        tfApellido.setText(contribuyenteDTO.getApellido());
                        tfNombre.setText(contribuyenteDTO.getNombre());
                        tfFDeNac.setText(dateTimeFormatter.format(contribuyenteDTO.getFechaDeNacimiento()));
                        tfDomicilio.setText(contribuyenteDTO.getDomicilio());
                    }
                } else {

                    System.out.println("Titular no encontrado");
                    JOptionPane.showMessageDialog(frame, "Documento Incorrecto", "Titular", JOptionPane.ERROR_MESSAGE);

                    tfNumeroDocumento.setText("");
                    tfApellido.setText("");
                    tfNombre.setText("");
                    tfFDeNac.setText("");
                    tfDomicilio.setText("");
                }
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TitularDTO titularDTO = new TitularDTO();
                try {
                    titularDTO.setDni(Long.valueOf(tfNumeroDocumento.getText()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Documento ingresado no es valido.", "Licencia", JOptionPane.ERROR_MESSAGE);
                }

                titularDTO.setDonante(esDonanteCheckBox.isSelected());
                titularDTO.setObservaciones(taObservaciones.getText());
                for (TipoSangre t : TipoSangre.values()) {
                    if (t.getName().equals(cbTipoSangre.getSelectedItem())) {
                        titularDTO.setTipoSangre(t);
                    }
                }
                ContribuyenteDTO contribuyenteDTO = new ContribuyenteDTO();
                contribuyenteDTO.setNroDocumento(titularDTO.getDni());
                titularDTO.setContribuyente(GestorTitular.buscarContribuyente(contribuyenteDTO));
                titularDTO.setTieneLicencias(false);
                GestorTitular.titularAux = titularDTO;
                if (GestorTitular.validarTitularExistente(titularDTO.getDni())) {
                    System.out.println("Ya existe titular.");
                    JOptionPane.showMessageDialog(frame, "Ya existe titular", "Titular", JOptionPane.ERROR_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(frame, "Titular creado con exito", "Titular", JOptionPane.INFORMATION_MESSAGE);
                    frame.cambiarPanel(MainFrame.PANE_EMITIR_LICENCIA);
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
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

    public JPanel getPane() {
        return rootPane;
    }
}
