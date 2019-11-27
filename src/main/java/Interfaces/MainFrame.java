package Interfaces;

import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Usuario;
import LogicaDeNegocios.Enumerations.Roles;
import gestores.GestorBD;
import gestores.GestorTitular;
import gestores.GestorUsuario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public static final int PANE_EMITIR_LICENCIA=1;
    public static final int PANE_IMPRIMIR_LICENCIA=2;
    public static final int PANE_ALTA_TITULAR=3;
    public static final int PANE_LICENCIAS_EXPIRADAS=4;
    public static final int PANE_RENOVAR_LICENCIAS =5;
    public static final int PANE_MENU_OPERADOR=6;
    public static final int PANE_VER_FORMATO_LICENCIA=7;

    public static int MENU_ACTUAL;


    private Container previousPane;

    public MainFrame(int pane){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 800 , 600);
    this.cambiarPanel(pane);


        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

//Create the menu bar.
        menuBar = new JMenuBar();

//Build the first menu.
        menu = new JMenu("Licencias");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

//a group of JMenuItems
        JMenuItem menuItem1 = new JMenuItem("Emitir Licencia",
                KeyEvent.VK_T);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorTitular.titularAux=null;
                MainFrame.this.cambiarPanel(PANE_EMITIR_LICENCIA);
            }
        });

        menu.add(menuItem1);

        menuItem = new JMenuItem("Imprimir Licencia",
                KeyEvent.VK_T);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorTitular.titularAux=null;
                MainFrame.this.cambiarPanel(PANE_IMPRIMIR_LICENCIA);
            }
        });
        menu.add(menuItem);

        JMenuItem menuItem2 = new JMenuItem("Renovar Licencia",
                KeyEvent.VK_T);
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorTitular.titularAux=null;
                MainFrame.this.cambiarPanel(PANE_RENOVAR_LICENCIAS);
            }
        });
        menu.add(menuItem2);




//a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

//Build second menu in the menu bar.
        menu = new JMenu("Titulares");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        menuItem = new JMenuItem("Alta Titular",
                KeyEvent.VK_T);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorTitular.titularAux=null;
                MainFrame.this.cambiarPanel(PANE_ALTA_TITULAR);
            }
        });
        menu.add(menuItem);
// tercer menu
        menu = new JMenu("Busquedas");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        menuItem = new JMenuItem("Licencias Expiradas",
                KeyEvent.VK_T);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorTitular.titularAux=null;
                MainFrame.this.cambiarPanel(PANE_LICENCIAS_EXPIRADAS);
            }
        });
        menu.add(menuItem);



        // CENTRAR PANTALLA
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth()/2;
    double height = screenSize.getHeight()/2;
    this.setLocationRelativeTo(null);
    //this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
}

    public MainFrame() {

    }

    public void cambiarPanel(int pane){
        previousPane=this.getContentPane();

        switch (pane){
            case PANE_EMITIR_LICENCIA:{
                this.setContentPane(new Interfaz_Emitir_Licencia2(this).getPane());
                break;
            }
            case PANE_MENU_OPERADOR:{
                this.setContentPane(new Interfaz_Menu_Operador(this).getPane());
                break;
            }
            case PANE_ALTA_TITULAR:{
                this.setContentPane(new Interfaz_Alta_Titular(this).getPane());
                break;
            }
            case PANE_IMPRIMIR_LICENCIA:{
                this.setContentPane(new Interfaz_Imprimir_Licencia(this).getPane());
               // this.setContentPane(new LicenciaDeConducir().getPane());
                break;
            }
            case PANE_RENOVAR_LICENCIAS:{
                this.setContentPane(new Interfaz_Renovar_Licencia(this).getPane());
                break;
            }
            case  PANE_LICENCIAS_EXPIRADAS:{
                this.setContentPane(new Interfaz_Licencias_Expiradas(this).getPane());
                break;
            }
            case PANE_VER_FORMATO_LICENCIA:{
                //this.setContentPane(new LicenciaDeConducir(this).getPane());
                break;
            }
        }
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
    }

    public void cambiarPanelConLicencias(int pane, ArrayList<LicenciaDTO> licenciaDTOS){
        previousPane=this.getContentPane();

        switch (pane){
            case PANE_VER_FORMATO_LICENCIA:{
                this.setContentPane(new LicenciaDeConducir(this, licenciaDTOS).getPane());
                break;
            }
        }
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
    }

    public int getMenuActual() {
        return MENU_ACTUAL;
    }

    public void backPreviousPane() {
        getContentPane().setVisible(false);
        this.setContentPane(previousPane);
        getContentPane().setVisible(true);
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        try{
            emf = Persistence.createEntityManagerFactory("persistencia");
        }catch (Exception e){
            System.out.println("No se pudo conectar la base de datos");
            e.printStackTrace();
        }
        GestorBD.setEmf(emf);
        GestorUsuario.setUsuario(new Usuario(Roles.OPERADOR, "Ematomas",123L));
        EventQueue.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame(MainFrame.PANE_MENU_OPERADOR);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
