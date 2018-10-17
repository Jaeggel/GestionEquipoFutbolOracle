/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partido;

import java.sql.Statement;
import Principal.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author JL
 */
public class Jugadores_Partido extends javax.swing.JFrame {

    /**
     * Creates new form Jugadores_Partido
     */
    public static Statement estSQL=Login.estSQL;
    boolean swCMB=true;
     
    public Jugadores_Partido() {
        initComponents();
        //conexionBD();
        insertImage();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/icono.png"));
        setIconImage(icon);
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana Partido");
        this.setResizable(false);
        lblId_Partido.setText(Ventana_Partido.idPartido);
        
        cerrar();
        swCMB=false;
        cargarComboJugador(cmbJ1);
        cargarComboJugador(cmbJ2);
        cargarComboJugador(cmbJ3);
        cargarComboJugador(cmbJ4);
        cargarComboJugador(cmbJ5);
        cargarComboJugador(cmbJ6);
        cargarComboJugador(cmbJ7);
        cargarComboJugador(cmbJ8);
        cargarComboJugador(cmbJ9);
        cargarComboJugador(cmbJ10);
        cargarComboJugador(cmbJ11);
        cmbJ1.setSelectedIndex(0);
        cmbJ2.setSelectedIndex(1);
        cmbJ3.setSelectedIndex(2);
        cmbJ4.setSelectedIndex(3);
        cmbJ5.setSelectedIndex(4);
        cmbJ6.setSelectedIndex(5);
        cmbJ7.setSelectedIndex(6);
        cmbJ8.setSelectedIndex(7);
        cmbJ9.setSelectedIndex(8);
        cmbJ10.setSelectedIndex(9);
        cmbJ11.setSelectedIndex(10);
        genNombreJugador(cmbJ1, txtJ1);
        genNombreJugador(cmbJ2, txtJ2);
        genNombreJugador(cmbJ3, txtJ3);
        genNombreJugador(cmbJ4, txtJ4);
        genNombreJugador(cmbJ5, txtJ5);
        genNombreJugador(cmbJ6, txtJ6);
        genNombreJugador(cmbJ7, txtJ7);
        genNombreJugador(cmbJ8, txtJ8);
        genNombreJugador(cmbJ9, txtJ9);
        genNombreJugador(cmbJ10, txtJ10);
        genNombreJugador(cmbJ11, txtJ11);
        genPosicion(cmbJ1, txtPosJ1);
        genPosicion(cmbJ2, txtPosJ2);
        genPosicion(cmbJ3, txtPosJ3);
        genPosicion(cmbJ4, txtPosJ4);
        genPosicion(cmbJ5, txtPosJ5);
        genPosicion(cmbJ6, txtPosJ6);
        genPosicion(cmbJ7, txtPosJ7);
        genPosicion(cmbJ8, txtPosJ8);
        genPosicion(cmbJ9, txtPosJ9);
        genPosicion(cmbJ10, txtPosJ10);
        genPosicion(cmbJ11, txtPosJ11);
        
        swCMB=true;
    }
    public void cerrar()
    {
        try
        {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter ()
            {
                public void windowClosing(WindowEvent e)
                {
                    dispose();
                }
            });        
        this.setVisible(true);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void insertImage()
    {
        ImagePanel Imagen = new ImagePanel();
        panelMain.add(Imagen);
        panelMain.repaint();
    }
    public class ImagePanel extends JPanel 
    {
        public ImagePanel()
        {
            //Se crea un método cuyo parámetro debe ser un objeto Graphics
            this.setSize(690,690);
        }
        @Override
        public void paint(Graphics grafico)
        {
            Dimension height = getSize();
            //Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
            ImageIcon Img = new ImageIcon(getClass().getResource("/Img/fondoMain2.jpg")); 
            //se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
            setOpaque(false);
            super.paintComponent(grafico);
        }
    }
    public void genNombreJugador(JComboBox combo,JTextField texto)
    {
        Procesos sql=new Procesos();
        ResultSet rsP=sql.consultarDatos(estSQL, "select nombres_jugador,apellidos_jugador from C##JOEL.jugador ");
        Object valorO;
        try
        {
            rsP.next();
            valorO=rsP.getObject(1);
            if(valorO==null)
            {
                System.out.println("NO HAY VALORES");
            }else
            {
                try
                {
                    String codJugador=combo.getSelectedItem().toString();
                    ResultSet rs=sql.consultarDatos(estSQL, "select nombres_jugador,apellidos_jugador  from C##JOEL.jugador where cod_jugador='"+codJugador+"'");    
                    rs.next();
                    texto.setText(String.valueOf(rs.getObject(1)).trim()+" "+String.valueOf(rs.getObject(2)).trim());
                }catch(java.lang.NullPointerException e)
                {

                }catch(Exception e){}
                }
        }catch(Exception e)
        {}
    
    }
    public void genPosicion(JComboBox combo,JTextField texto)
    {
        Procesos sql=new Procesos();
        ResultSet rsP=sql.consultarDatos(estSQL, "select posicion from C##JOEL.jugador ");
        Object valorO;
        try
        {
            rsP.next();
            valorO=rsP.getObject(1);
            if(valorO==null)
            {
                System.out.println("NO HAY VALORES");
            }else
            {
                try
                {
                    String codJugador=combo.getSelectedItem().toString();
                    ResultSet rs=sql.consultarDatos(estSQL, "select posicion from C##JOEL.jugador where cod_jugador='"+codJugador+"'");    
                    rs.next();
                    texto.setText(String.valueOf(rs.getObject(1)).trim());
                }catch(java.lang.NullPointerException e)
                {

                }catch(Exception e){}
                }
        }catch(Exception e)
        {}
    
    
    }
    public void cargarComboJugador(JComboBox combo)
    {
        combo.removeAllItems();
        swCMB=false;
        Procesos sql=new Procesos();
        ResultSet rs=sql.consultarDatos(estSQL, "select cod_jugador from C##JOEL.jugador order by 1 asc");
        sql.buscarDatosCombo(rs, combo);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblId_Partido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbJ1 = new javax.swing.JComboBox();
        cmbJ2 = new javax.swing.JComboBox();
        cmbJ3 = new javax.swing.JComboBox();
        cmbJ4 = new javax.swing.JComboBox();
        cmbJ5 = new javax.swing.JComboBox();
        cmbJ6 = new javax.swing.JComboBox();
        cmbJ7 = new javax.swing.JComboBox();
        cmbJ8 = new javax.swing.JComboBox();
        cmbJ9 = new javax.swing.JComboBox();
        cmbJ10 = new javax.swing.JComboBox();
        cmbJ11 = new javax.swing.JComboBox();
        txtJ1 = new javax.swing.JTextField();
        txtJ2 = new javax.swing.JTextField();
        txtJ3 = new javax.swing.JTextField();
        txtJ4 = new javax.swing.JTextField();
        txtJ5 = new javax.swing.JTextField();
        txtJ6 = new javax.swing.JTextField();
        txtJ7 = new javax.swing.JTextField();
        txtJ8 = new javax.swing.JTextField();
        txtJ9 = new javax.swing.JTextField();
        txtJ10 = new javax.swing.JTextField();
        txtJ11 = new javax.swing.JTextField();
        txtPosJ1 = new javax.swing.JTextField();
        txtPosJ2 = new javax.swing.JTextField();
        txtPosJ3 = new javax.swing.JTextField();
        txtPosJ4 = new javax.swing.JTextField();
        txtPosJ5 = new javax.swing.JTextField();
        txtPosJ6 = new javax.swing.JTextField();
        txtPosJ7 = new javax.swing.JTextField();
        txtPosJ8 = new javax.swing.JTextField();
        txtPosJ9 = new javax.swing.JTextField();
        txtPosJ10 = new javax.swing.JTextField();
        txtPosJ11 = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel3.setText("Jugadores a Disputar Cotejo N°: ");

        lblId_Partido.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        lblId_Partido.setText("0");

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel1.setText("Jugador N° 1: ");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel2.setText("Jugador N° 2:");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel5.setText("Jugador N° 3:");

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel6.setText("Jugador N° 4: ");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel7.setText("Jugador N° 5:");

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel8.setText("Jugador N° 6:");

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel9.setText("Jugador N° 7: ");

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel10.setText("Jugador N° 8:");

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel11.setText("Jugador N° 9:");

        jLabel12.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel12.setText("Jugador N° 10:");

        jLabel13.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel13.setText("Jugador N° 11:");

        cmbJ1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ1ItemStateChanged(evt);
            }
        });
        cmbJ1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ1FocusLost(evt);
            }
        });

        cmbJ2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ2ItemStateChanged(evt);
            }
        });
        cmbJ2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ2FocusLost(evt);
            }
        });

        cmbJ3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ3ItemStateChanged(evt);
            }
        });
        cmbJ3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ3FocusLost(evt);
            }
        });

        cmbJ4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ4ItemStateChanged(evt);
            }
        });
        cmbJ4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ4FocusLost(evt);
            }
        });

        cmbJ5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ5ItemStateChanged(evt);
            }
        });
        cmbJ5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ5FocusLost(evt);
            }
        });

        cmbJ6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ6ItemStateChanged(evt);
            }
        });
        cmbJ6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ6FocusLost(evt);
            }
        });

        cmbJ7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ7ItemStateChanged(evt);
            }
        });
        cmbJ7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ7FocusLost(evt);
            }
        });

        cmbJ8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ8ItemStateChanged(evt);
            }
        });
        cmbJ8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ8FocusLost(evt);
            }
        });

        cmbJ9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ9ItemStateChanged(evt);
            }
        });
        cmbJ9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ9FocusLost(evt);
            }
        });

        cmbJ10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ10ItemStateChanged(evt);
            }
        });
        cmbJ10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ10FocusLost(evt);
            }
        });

        cmbJ11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJ11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJ11ItemStateChanged(evt);
            }
        });
        cmbJ11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbJ11FocusLost(evt);
            }
        });

        txtJ1.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ2.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ3.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ4.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ5.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ6.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ7.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ8.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ9.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ10.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtJ11.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ1.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ2.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ3.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ4.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ5.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ6.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ7.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ8.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ9.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ10.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtPosJ11.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        btnConfirmar.setBackground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/conf.png"))); // NOI18N
        btnConfirmar.setText("Confirmar Alineacion ");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/canc.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(lblId_Partido))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(43, 43, 43)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbJ1, 0, 100, Short.MAX_VALUE)
                            .addComponent(cmbJ2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJ11, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJ2)
                            .addComponent(txtJ3)
                            .addComponent(txtJ4)
                            .addComponent(txtJ5)
                            .addComponent(txtJ6)
                            .addComponent(txtJ7)
                            .addComponent(txtJ8)
                            .addComponent(txtJ9)
                            .addComponent(txtJ10)
                            .addComponent(txtJ11, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtJ1))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPosJ1)
                            .addComponent(txtPosJ2)
                            .addComponent(txtPosJ3)
                            .addComponent(txtPosJ4)
                            .addComponent(txtPosJ5)
                            .addComponent(txtPosJ6)
                            .addComponent(txtPosJ7)
                            .addComponent(txtPosJ8)
                            .addComponent(txtPosJ9)
                            .addComponent(txtPosJ10)
                            .addComponent(txtPosJ11, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnConfirmar)
                        .addGap(96, 96, 96)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                        .addGap(53, 53, 53)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblId_Partido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbJ10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJ10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPosJ10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cmbJ11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJ11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosJ11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbJ1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ1ItemStateChanged
       if (evt.getSource()==cmbJ1&& swCMB) 
        {
            genNombreJugador(cmbJ1, txtJ1);
            genPosicion(cmbJ1, txtPosJ1);
            cmbJ2.requestFocus();
        }
    }//GEN-LAST:event_cmbJ1ItemStateChanged

    private void cmbJ2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ2ItemStateChanged
        if (evt.getSource()==cmbJ2&& swCMB) 
        {
            genNombreJugador(cmbJ2, txtJ2);
            genPosicion(cmbJ2, txtPosJ2);
            cmbJ3.requestFocus();
        }
    }//GEN-LAST:event_cmbJ2ItemStateChanged

    private void cmbJ3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ3ItemStateChanged
         if (evt.getSource()==cmbJ3&& swCMB) 
        {
            genNombreJugador(cmbJ3, txtJ3);
            genPosicion(cmbJ3, txtPosJ3);
            cmbJ4.requestFocus();
        }
    }//GEN-LAST:event_cmbJ3ItemStateChanged

    private void cmbJ4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ4ItemStateChanged
         if (evt.getSource()==cmbJ4&& swCMB) 
        {
            genNombreJugador(cmbJ4, txtJ4);
            genPosicion(cmbJ4, txtPosJ4);
            cmbJ5.requestFocus();
        }
    }//GEN-LAST:event_cmbJ4ItemStateChanged

    private void cmbJ5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ5ItemStateChanged
         if (evt.getSource()==cmbJ5&& swCMB) 
        {
            genNombreJugador(cmbJ5, txtJ5);
            genPosicion(cmbJ5, txtPosJ5);
            cmbJ6.requestFocus();
        }
    }//GEN-LAST:event_cmbJ5ItemStateChanged

    private void cmbJ6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ6ItemStateChanged
         if (evt.getSource()==cmbJ6&& swCMB) 
        {
            genNombreJugador(cmbJ6, txtJ6);
            genPosicion(cmbJ6, txtPosJ6);
            cmbJ7.requestFocus();
        }
    }//GEN-LAST:event_cmbJ6ItemStateChanged

    private void cmbJ7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ7ItemStateChanged
         if (evt.getSource()==cmbJ7&& swCMB) 
        {
            genNombreJugador(cmbJ7, txtJ7);
            genPosicion(cmbJ7, txtPosJ7);
            cmbJ8.requestFocus();
        }
    }//GEN-LAST:event_cmbJ7ItemStateChanged

    private void cmbJ8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ8ItemStateChanged
         if (evt.getSource()==cmbJ8&& swCMB) 
        {
            genNombreJugador(cmbJ8, txtJ8);
            genPosicion(cmbJ8, txtPosJ8);
            cmbJ9.requestFocus();
        }
    }//GEN-LAST:event_cmbJ8ItemStateChanged

    private void cmbJ9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ9ItemStateChanged
         if (evt.getSource()==cmbJ9&& swCMB) 
        {
            genNombreJugador(cmbJ9, txtJ9);
            genPosicion(cmbJ9, txtPosJ9);
            cmbJ10.requestFocus();
        }
    }//GEN-LAST:event_cmbJ9ItemStateChanged

    private void cmbJ10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ10ItemStateChanged
         if (evt.getSource()==cmbJ10&& swCMB) 
        {
            genNombreJugador(cmbJ10, txtJ10);
            genPosicion(cmbJ10, txtPosJ10);
            cmbJ11.requestFocus();
        }
    }//GEN-LAST:event_cmbJ10ItemStateChanged

    private void cmbJ11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJ11ItemStateChanged
         if (evt.getSource()==cmbJ11&& swCMB) 
        {
            genNombreJugador(cmbJ11, txtJ11);
            genPosicion(cmbJ11, txtPosJ11);
            btnConfirmar.requestFocus();
        }
    }//GEN-LAST:event_cmbJ11ItemStateChanged

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        Procesos sql=new Procesos();
        
        String sentenciaSQL="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ1.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL1="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ2.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL2="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ3.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL3="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ4.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL4="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ5.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL5="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ6.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL6="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ7.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL7="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ8.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL8="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ9.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL9="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ10.getSelectedItem().toString().trim()+"')";
        String sentenciaSQL10="insert into C##JOEL.detalle_partido values ('"+lblId_Partido.getText().trim()+"','"+cmbJ11.getSelectedItem().toString().trim()+"')";
                                                                                            
        sql.ingresarDatos(estSQL, sentenciaSQL1);
        sql.ingresarDatos(estSQL, sentenciaSQL2);
        sql.ingresarDatos(estSQL, sentenciaSQL3);
        sql.ingresarDatos(estSQL, sentenciaSQL4);
        sql.ingresarDatos(estSQL, sentenciaSQL5);
        sql.ingresarDatos(estSQL, sentenciaSQL6);
        sql.ingresarDatos(estSQL, sentenciaSQL7);
        sql.ingresarDatos(estSQL, sentenciaSQL8);
        sql.ingresarDatos(estSQL, sentenciaSQL9);
        sql.ingresarDatos(estSQL, sentenciaSQL10);
        sql.ingresarDatos(estSQL, sentenciaSQL);
        JOptionPane.showMessageDialog(null,"Los Jugadores han salido al campo...!");
        this.dispose();

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbJ2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ2FocusLost
        if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ2.getSelectedItem().toString().trim()))
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
          
            cmbJ2.requestFocus();
            cmbJ2.setSelectedIndex(1);
        }else{
           // btnConfirmar.setEnabled(true);
        //    cmbJ3.requestFocus();
        }
    }//GEN-LAST:event_cmbJ2FocusLost

    private void cmbJ3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ3FocusLost
       if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ3.getSelectedItem().toString().trim()))
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
          
            cmbJ3.requestFocus();
            cmbJ3.setSelectedIndex(2);
        }else
       {
            //btnConfirmar.setEnabled(true);
            //cmbJ4.requestFocus();
       }
    }//GEN-LAST:event_cmbJ3FocusLost

    private void cmbJ4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ4FocusLost
       if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ4.getSelectedItem().toString().trim()))
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
            
            cmbJ4.requestFocus();
            cmbJ4.setSelectedIndex(3);
        }else
       {
            //btnConfirmar.setEnabled(true);
            //cmbJ5.requestFocus();
       }
    }//GEN-LAST:event_cmbJ4FocusLost

    private void cmbJ5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ5FocusLost
        if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ5.getSelectedItem().toString().trim()))    
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
            
            cmbJ5.requestFocus();
            cmbJ5.setSelectedIndex(4);
        }else
       {
           //    btnConfirmar.setEnabled(true);
          //  cmbJ6.requestFocus();
       }
    }//GEN-LAST:event_cmbJ5FocusLost

    private void cmbJ6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ6FocusLost
       if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ6.getSelectedItem().toString().trim()))
            
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
            
            cmbJ6.requestFocus();
            cmbJ6.setSelectedIndex(5);
        }else
       {
        //    btnConfirmar.setEnabled(true);
           // cmbJ7.requestFocus();
       }
    }//GEN-LAST:event_cmbJ6FocusLost

    private void cmbJ7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ7FocusLost
       if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ7.getSelectedItem().toString().trim()))     
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
           
            cmbJ7.requestFocus();
            cmbJ7.setSelectedIndex(6);
        }else
       {
            //    btnConfirmar.setEnabled(true);
            //cmbJ8.requestFocus();
       }
    }//GEN-LAST:event_cmbJ7FocusLost

    private void cmbJ8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ8FocusLost
        if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ8.getSelectedItem().toString().trim())
               ) 
            
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
           
            cmbJ8.requestFocus();
            cmbJ8.setSelectedIndex(7);
        }else
       {
            //    btnConfirmar.setEnabled(true);
            //cmbJ9.requestFocus();
       }
    }//GEN-LAST:event_cmbJ8FocusLost

    private void cmbJ9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ9FocusLost
        if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ9.getSelectedItem().toString().trim())) 
            
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
           
            cmbJ9.requestFocus();
            cmbJ9.setSelectedIndex(8);
        }else
       {
           //    btnConfirmar.setEnabled(true);
           // cmbJ10.requestFocus();
       }
    }//GEN-LAST:event_cmbJ9FocusLost

    private void cmbJ10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ10FocusLost
        if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())
               || cmbJ11.getSelectedItem().toString().trim().equals(cmbJ10.getSelectedItem().toString().trim())) 
            
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
           
            cmbJ10.requestFocus();
            cmbJ10.setSelectedIndex(9);
        }else
       {
           //    btnConfirmar.setEnabled(true);
          //  cmbJ11.requestFocus();
       }
    }//GEN-LAST:event_cmbJ10FocusLost

    private void cmbJ11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ11FocusLost
        if(cmbJ1.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ11.getSelectedItem().toString().trim())) 
            
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
            
            cmbJ11.requestFocus();
            cmbJ11.setSelectedIndex(10);
        }else
       {
           //    btnConfirmar.setEnabled(true);
            btnConfirmar.requestFocus();
       }
    }//GEN-LAST:event_cmbJ11FocusLost

    private void cmbJ1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbJ1FocusLost
          if(cmbJ11.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ2.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ3.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ4.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ5.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ6.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ7.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ8.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ9.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())
               || cmbJ10.getSelectedItem().toString().trim().equals(cmbJ1.getSelectedItem().toString().trim())) 
            
        {
            JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la Alineación\nVolver a Seleccionar otro...");
            
            cmbJ1.requestFocus();
            cmbJ1.setSelectedIndex(0);
        }else
       {
           //    btnConfirmar.setEnabled(true);
            cmbJ2.requestFocus();
       }
    }//GEN-LAST:event_cmbJ1FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox cmbJ1;
    private javax.swing.JComboBox cmbJ10;
    private javax.swing.JComboBox cmbJ11;
    private javax.swing.JComboBox cmbJ2;
    private javax.swing.JComboBox cmbJ3;
    private javax.swing.JComboBox cmbJ4;
    private javax.swing.JComboBox cmbJ5;
    private javax.swing.JComboBox cmbJ6;
    private javax.swing.JComboBox cmbJ7;
    private javax.swing.JComboBox cmbJ8;
    private javax.swing.JComboBox cmbJ9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblId_Partido;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtJ1;
    private javax.swing.JTextField txtJ10;
    private javax.swing.JTextField txtJ11;
    private javax.swing.JTextField txtJ2;
    private javax.swing.JTextField txtJ3;
    private javax.swing.JTextField txtJ4;
    private javax.swing.JTextField txtJ5;
    private javax.swing.JTextField txtJ6;
    private javax.swing.JTextField txtJ7;
    private javax.swing.JTextField txtJ8;
    private javax.swing.JTextField txtJ9;
    private javax.swing.JTextField txtPosJ1;
    private javax.swing.JTextField txtPosJ10;
    private javax.swing.JTextField txtPosJ11;
    private javax.swing.JTextField txtPosJ2;
    private javax.swing.JTextField txtPosJ3;
    private javax.swing.JTextField txtPosJ4;
    private javax.swing.JTextField txtPosJ5;
    private javax.swing.JTextField txtPosJ6;
    private javax.swing.JTextField txtPosJ7;
    private javax.swing.JTextField txtPosJ8;
    private javax.swing.JTextField txtPosJ9;
    // End of variables declaration//GEN-END:variables
}
