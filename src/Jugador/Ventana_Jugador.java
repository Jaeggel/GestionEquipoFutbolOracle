/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugador;

import Principal.Login;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import Principal.Procesos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author JL
 */
public class Ventana_Jugador extends javax.swing.JFrame {

    /**
     * Creates new form Ventana_Jugador
     */
    public static Statement estSQL=Login.estSQL;
    DefaultTableModel modelo=null;
    boolean swCMB=true;
    public Ventana_Jugador()
    {
        initComponents();
        //conexionBD();
        insertImage();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/icono.png"));
        setIconImage(icon);
        txtNombres_Jug.requestFocus();
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana Jugador");
        this.setResizable(false);
        cerrar();
        String cabecera[]={};
        String datos[][]={};
        modelo=new DefaultTableModel(datos,cabecera);
        Tabla_Jugador.setModel(modelo); 
        swCMB=false;
        genClave();
        
        cargarComboNumero();
        cargarComboPos();
        cargarComboJugador();
        genNombreJugador();
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
    public void genClave()
    {
        Procesos sql=new Procesos();
        String cadeql="select max(cod_jugador) from C##JOEL.jugador";
        ResultSet rs=sql.consultarUltimoId(estSQL,cadeql);
        Object valorO;
        try
        {   
            rs.next();
            valorO=rs.getObject(1);    
            if(valorO!=null)
            {
                txtCod_Jugador.setText(Integer.parseInt(valorO.toString().trim())+1+"");
            }else
            {
                txtCod_Jugador.setText(111000+"");
            }
        }catch(Exception e)
        {    
        }   
    }
    public void genNombreJugador()
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
                    String codJugador=cmbJugador.getSelectedItem().toString();
                    ResultSet rs=sql.consultarDatos(estSQL, "select nombres_jugador,apellidos_jugador  from C##JOEL.jugador where cod_jugador='"+codJugador+"'");    
                    rs.next();
                    txtNombres_Jug.setText(String.valueOf(rs.getObject(1)).trim()+" "+String.valueOf(rs.getObject(2)).trim());
                }catch(java.lang.NullPointerException e)
                {

                }catch(Exception e){}
                }
        }catch(Exception e)
        {}
    
    }
     public void cargarComboJugador()
    {
        cmbJugador.removeAllItems();
        swCMB=false;
        Procesos sql=new Procesos();
        ResultSet rs=sql.consultarDatos(estSQL, "select cod_jugador from C##JOEL.jugador order by 1 asc");
        sql.buscarDatosCombo(rs, cmbJugador);
    }
    public void cargarComboPos()
    {
        cmbPosicion.removeAllItems();
        cmbPosicion.addItem(("Arquero"));
        cmbPosicion.addItem(("Defensa Central"));
        cmbPosicion.addItem(("Lateral Derecho"));
        cmbPosicion.addItem(("Lateral Izquierdo"));
        cmbPosicion.addItem(("Mediocampista Of."));
        cmbPosicion.addItem(("Mediocampista Def."));
        cmbPosicion.addItem(("Armador"));
        cmbPosicion.addItem(("Delantero"));
        
    }
    public void cargarComboNumero()
    {
        cmbNumCamiseta.removeAllItems();
        cmbNumCamiseta.addItem("1");cmbNumCamiseta.addItem("2");cmbNumCamiseta.addItem("3");cmbNumCamiseta.addItem("4");
        cmbNumCamiseta.addItem("5");cmbNumCamiseta.addItem("6");cmbNumCamiseta.addItem("7");cmbNumCamiseta.addItem("8");
        cmbNumCamiseta.addItem("9");cmbNumCamiseta.addItem("10");cmbNumCamiseta.addItem("11");cmbNumCamiseta.addItem("12");
        cmbNumCamiseta.addItem("13");cmbNumCamiseta.addItem("14");cmbNumCamiseta.addItem("15");cmbNumCamiseta.addItem("16");
        cmbNumCamiseta.addItem("17");cmbNumCamiseta.addItem("18");cmbNumCamiseta.addItem("19");cmbNumCamiseta.addItem("20");
        cmbNumCamiseta.addItem("21");cmbNumCamiseta.addItem("22");cmbNumCamiseta.addItem("23");cmbNumCamiseta.addItem("24");
        
    }
    public void limpiarTabla(JTable tabla)
    {
        while(tabla.getRowCount()>0)
        {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
    }
    
    public void limpiar()
    {
        
        txtCod_Jugador.setText("");
        txtContrato.setText("");
        txtFechaNac.setText("");
        txtNacionalidad.setText("");
        txt_ApellJug.setText("");
        txt_NombresJug.setText("");
        genClave();
        cargarComboJugador();
        txtCod_Jugador.setBackground(Color.WHITE);
        txtNombres_Jug.requestFocus();
        swCMB=true;
    }  
       
    public void editarCabecera()
    {
        JTableHeader th; 
        th = Tabla_Jugador.getTableHeader(); 
        Font fuente = new Font("Consolas", Font.BOLD, 12); 
        th.setFont(fuente); 
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
            this.setSize(775,710);
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCod_Jugador = new javax.swing.JTextField();
        txt_NombresJug = new javax.swing.JTextField();
        txt_ApellJug = new javax.swing.JTextField();
        txtNacionalidad = new javax.swing.JTextField();
        txtContrato = new javax.swing.JTextField();
        txtFechaNac = new javax.swing.JFormattedTextField();
        cmbPosicion = new javax.swing.JComboBox();
        cmbNumCamiseta = new javax.swing.JComboBox();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Jugador = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cmbJugador = new javax.swing.JComboBox();
        btnPartidos = new javax.swing.JButton();
        txtNombres_Jug = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 28)); // NOI18N
        jLabel1.setText("Información Jugador");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel2.setText("Cod. Jugador: ");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Nombres: ");

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel4.setText("Apellidos: ");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel5.setText("Nacionalidad: ");

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel6.setText("Duración del Contrato: ");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel7.setText("Fecha de Nacimiento: ");

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel8.setText("Posición: ");

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel9.setText("N° Camiseta: ");

        txtCod_Jugador.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        txtCod_Jugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCod_JugadorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCod_JugadorKeyTyped(evt);
            }
        });

        txt_NombresJug.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txt_ApellJug.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtNacionalidad.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtContrato.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtFechaNac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yy"))));
        txtFechaNac.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        cmbPosicion.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbPosicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbNumCamiseta.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbNumCamiseta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/modif.jpg"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/elim.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        Tabla_Jugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Tabla_Jugador);

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel10.setText("Seleccionar Jugador: ");

        cmbJugador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJugador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJugadorItemStateChanged(evt);
            }
        });

        btnPartidos.setBackground(new java.awt.Color(255, 255, 255));
        btnPartidos.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnPartidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/num_part.png"))); // NOI18N
        btnPartidos.setText("Consultar N° Partidos");
        btnPartidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPartidosActionPerformed(evt);
            }
        });

        txtNombres_Jug.setEditable(false);
        txtNombres_Jug.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(96, 96, 96)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCod_Jugador, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txt_NombresJug)
                                    .addComponent(txt_ApellJug)
                                    .addComponent(txtNacionalidad)))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(44, 44, 44)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtContrato)
                                    .addComponent(txtFechaNac)
                                    .addComponent(cmbPosicion, 0, 280, Short.MAX_VALUE)
                                    .addComponent(cmbNumCamiseta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(73, 73, 73)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10)
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNombres_Jug, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbJugador, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConsultar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnPartidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCod_Jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_NombresJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_ApellJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbNumCamiseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombres_Jug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnPartidos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbJugadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJugadorItemStateChanged
        if (evt.getSource()==cmbJugador&& swCMB) 
        {
            genNombreJugador();
        }
    }//GEN-LAST:event_cmbJugadorItemStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Procesos sql=new Procesos();
        String codJug=txtCod_Jugador.getText().trim();
        String nombres=txt_NombresJug.getText().trim();
        String apell=txt_ApellJug.getText().trim();
        String fecha=txtFechaNac.getText().trim();
        String nacion=txtNacionalidad.getText().trim();
        String duracion=txtContrato.getText().trim();
        String posicion=cmbPosicion.getSelectedItem().toString().trim();
        int numCamiseta=Integer.parseInt(cmbNumCamiseta.getSelectedItem().toString().trim());
  
        String sentenciaSQL="insert into C##JOEL.jugador values ('"+codJug+"','"+nombres+"','"+apell+"','"+nacion+"','"+duracion+"','"+fecha+"','"+posicion+"','"+numCamiseta+"')";
                                                                                            
        if(sql.ingresarDatos(estSQL, sentenciaSQL))
        {
            JOptionPane.showMessageDialog(null, "Datos Agregados Exitosamente.");  
        }else
        {
                JOptionPane.showMessageDialog(null, "No se pudo guardar el registro.");
        }
        
        limpiar();
      
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        limpiarTabla(Tabla_Jugador);
        Procesos sql=new Procesos();
        
        ResultSet rs=sql.consultarDatos(estSQL, "select * from C##JOEL.jugador");
        sql.procesarRB(rs, modelo);
        
        limpiar();
        editarCabecera();        

    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnPartidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartidosActionPerformed
        limpiarTabla(Tabla_Jugador);
        Procesos sql=new Procesos();
        ResultSet rs=sql.consultarDatos(estSQL, "select count(*) as Numero_Partidos\n" +
        "from C##JOEL.detalle_partido\n" +
        "where cod_jugador='"+cmbJugador.getSelectedItem().toString().trim()+"'");
        sql.procesarRB(rs, modelo);
        editarCabecera(); 
    }//GEN-LAST:event_btnPartidosActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        String nombres=txt_NombresJug.getText().trim();
        String apell=txt_ApellJug.getText().trim();
        String fecha=txtFechaNac.getText().substring(0,10).trim();
        String nacion=txtNacionalidad.getText().trim();
        String duracion=txtContrato.getText().trim();
        String posicion=cmbPosicion.getSelectedItem().toString().trim();
        int numCamiseta=Integer.parseInt(cmbNumCamiseta.getSelectedItem().toString().trim());
        String sentenciaSQL="UPDATE C##JOEL.jugador SET nombres_jugador='"+nombres+"',apellidos_jugador='"+apell+"',fecha_nac=TO_DATE('"+fecha+"','YYYY/MM/DD'),"
                + "nacionalidad='"+nacion+"',duracion_contrato='"+duracion+"',posicion='"+posicion+"',num_camiseta="+numCamiseta+" where cod_jugador='"+txtCod_Jugador.getText().trim()+"'";
        Procesos obj=new Procesos();
        if(obj.ingresarDatos(estSQL, sentenciaSQL))
        {
            JOptionPane.showMessageDialog(null, "Datos Moficados Exitosamente.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se puede modificar.");
        }
        limpiar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtCod_JugadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCod_JugadorKeyReleased
        if(evt.getKeyChar()==KeyEvent.VK_B)
        {
            
            
            txtCod_Jugador.setBackground(Color.red);
            //////////////////////////////////////
            String sentenciaSQL="select * from C##JOEL.jugador where cod_jugador='"+txtCod_Jugador.getText().trim()+"'";
            Procesos obj=new Procesos();
            ResultSet rs=obj.consultarDatos(estSQL, sentenciaSQL);

            try{
                rs.next();
                
                    txt_NombresJug.setText(String.valueOf(rs.getObject(2)));
                    txt_ApellJug.setText(String.valueOf(rs.getObject(3)));
                    txtNacionalidad.setText(String.valueOf(rs.getObject(4)));
                    txtContrato.setText(String.valueOf(rs.getObject(5)));
                    txtFechaNac.setText(String.valueOf(rs.getObject(6).toString().substring(0,10)));
            }
            catch(Exception E1)
            {
               JOptionPane.showMessageDialog(null,"No se puede mostrar.","Error",JOptionPane.ERROR);
                E1.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_txtCod_JugadorKeyReleased

    private void txtCod_JugadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCod_JugadorKeyTyped
        if(!Character.isDigit(evt.getKeyChar())|| txtCod_Jugador.getText().length()==10)
        evt.consume();
    }//GEN-LAST:event_txtCod_JugadorKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Procesos obj=new Procesos();
        
        String sentenciaSQL="DELETE from C##JOEL.jugador where cod_jugador='"+txtCod_Jugador.getText().trim()+"'";

        if(obj.ingresarDatos(estSQL, sentenciaSQL))
        {
                JOptionPane.showMessageDialog(null, "Datos Eliminados Exitosamente.");
            
        }else
        {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar.");
        }
        limpiar(); 
    
    }//GEN-LAST:event_btnEliminarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_Jugador;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPartidos;
    private javax.swing.JComboBox cmbJugador;
    private javax.swing.JComboBox cmbNumCamiseta;
    private javax.swing.JComboBox cmbPosicion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtCod_Jugador;
    private javax.swing.JTextField txtContrato;
    private javax.swing.JFormattedTextField txtFechaNac;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombres_Jug;
    private javax.swing.JTextField txt_ApellJug;
    private javax.swing.JTextField txt_NombresJug;
    // End of variables declaration//GEN-END:variables
}
