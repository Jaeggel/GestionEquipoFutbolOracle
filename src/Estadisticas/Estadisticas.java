/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estadisticas;



import Partido.Ventana_Partido;
import Principal.Login;
import Principal.Procesos;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author JL
 */
public class Estadisticas extends javax.swing.JFrame {

    /**
     * Creates new form Estadisticas
     */
    public static Statement estSQL=Login.estSQL;
    boolean swCMB=true;
     DefaultTableModel modelo=null;
     String idpartido=Ventana_Partido.idPartido;
     
    public Estadisticas() {
        initComponents();
        insertImage();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/icono.png"));
        setIconImage(icon);
        //conexionBD();
        
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana Estadísticas");
        this.setResizable(false);
        cerrar();
        
        lblId_Partido.setText(Integer.toString(verifPart()));
        String cabecera[]={};
        String datos[][]={};
        modelo=new DefaultTableModel(datos,cabecera);
        Tabla_Estadisticas.setModel(modelo); 
        swCMB=false;
        cargarComboJugador();
        genNombreJugador(cmbCodJugador,txtNombresJug);
        
        cargarComboPartido();
        cargarComboJugadorEst();
        genNombreJugador(cmbEstJugador,txtEstNombreJug);
        
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
    public void cargarComboJugador()
    {
        cmbCodJugador.removeAllItems();
        swCMB=false;
        Procesos sql=new Procesos();
        ResultSet rs=sql.consultarDatos(estSQL, "select jugador.cod_jugador\n" +
        "from C##JOEL.detalle_partido,C##JOEL.jugador\n" +
        "where id_partido='"+lblId_Partido.getText().trim()+"'\n" +
        "and detalle_partido.cod_jugador=jugador.cod_jugador order by 1 asc");
        sql.buscarDatosCombo(rs, cmbCodJugador);
    }
    public void cargarComboJugadorEst()
    {
        cmbEstJugador.removeAllItems();
        swCMB=false;
        Procesos sql=new Procesos();
        ResultSet rs=sql.consultarDatos(estSQL, "select jugador.cod_jugador\n" +
        "from C##JOEL.detalle_partido,C##JOEL.jugador\n" +
        "where id_partido='"+cmbPartido.getSelectedItem().toString().trim()+"'\n" +
        "and detalle_partido.cod_jugador=jugador.cod_jugador order by 1 asc");
        sql.buscarDatosCombo(rs, cmbEstJugador);
    }
    public void cargarComboPartido()
    {
        cmbPartido.removeAllItems();
        swCMB=false;
        Procesos sql=new Procesos();
        ResultSet rs=sql.consultarDatos(estSQL, "select id_partido from C##JOEL.partido");
        sql.buscarDatosCombo(rs, cmbPartido);
        
    }
    public void genNombreJugador(JComboBox combo, JTextField texto)
    {
        Procesos sql=new Procesos();
        ResultSet rsP=sql.consultarDatos(estSQL, "select nombres_jugador,apellidos_jugador from C##JOEL.jugador");
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
    public int verifPart()
    {
        int temp=0;
         Procesos sql=new Procesos();
        ResultSet rsP=sql.consultarDatos(estSQL, "select count(*) from C##JOEL.partido where id_partido='"+idpartido+"'");
        Object valorO;
        try
        {
            rsP.next();
            valorO=rsP.getObject(1);
            if(Integer.parseInt(valorO.toString())==0)
            {
                temp=Integer.parseInt(idpartido)-1;
            }else
                temp=Integer.parseInt(idpartido);
        }catch(Exception e)
        {}
        return temp;
    }
    
     public void editarCabecera()
    {
        JTableHeader th; 
        th = Tabla_Estadisticas.getTableHeader(); 
        Font fuente = new Font("Consolas", Font.BOLD, 12); 
        th.setFont(fuente); 
    }
    public void limpiar()
    {
        txtNombresJug.setText("");
        numPases.setValue(0);
        numGoles.setValue(0);
        numAsist.setValue(0);
        numAma.setValue(0);
        numRojas.setValue(0);
        distRec.setValue(0);
        txtNombresJug.requestFocus();
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
            this.setSize(820,710);
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
     public void limpiarTabla(JTable tabla)
    {
        while(tabla.getRowCount()>0)
        {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
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
        lblId_Partido = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombresJug = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbCodJugador = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Estadisticas = new javax.swing.JTable();
        numPases = new javax.swing.JSpinner();
        numGoles = new javax.swing.JSpinner();
        numAsist = new javax.swing.JSpinner();
        distRec = new javax.swing.JSpinner();
        numAma = new javax.swing.JSpinner();
        numRojas = new javax.swing.JSpinner();
        btnGuardar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cmbPartido = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cmbEstJugador = new javax.swing.JComboBox();
        txtEstNombreJug = new javax.swing.JTextField();
        btnConsultarEst = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblId_Partido.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        lblId_Partido.setText("0");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel2.setText("Cod. Jugador: ");

        txtNombresJug.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        txtNombresJug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresJugActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Nombres: ");

        cmbCodJugador.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbCodJugador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCodJugador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCodJugadorItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel4.setText("Número de Pases: ");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel5.setText("Número de Goles: ");

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel6.setText("Número de Asistencias: ");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel7.setText("Distancia Recorrida [ m ]: ");

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel8.setText("Tarjetas Amarillas: ");

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel9.setText("Tarjetas Rojas: ");

        Tabla_Estadisticas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tabla_Estadisticas);

        numPases.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        numPases.setModel(new javax.swing.SpinnerNumberModel(0, 0, 500, 1));

        numGoles.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        numGoles.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        numAsist.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        numAsist.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        distRec.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        distRec.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(5000.0f), Float.valueOf(0.5f)));

        numAma.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        numAma.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2, 1));

        numRojas.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        numRojas.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1, 1));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/guardar.png"))); // NOI18N
        btnGuardar.setText("            Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar todo.png"))); // NOI18N
        btnConsultar.setText("Consultar Todo");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel10.setText("Seleccionar Partido: ");

        cmbPartido.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbPartido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPartido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPartidoItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel11.setText("Seleccionar Jugador: ");

        cmbEstJugador.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbEstJugador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEstJugador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstJugadorItemStateChanged(evt);
            }
        });

        txtEstNombreJug.setEditable(false);
        txtEstNombreJug.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        btnConsultarEst.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarEst.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnConsultarEst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/est.png"))); // NOI18N
        btnConsultarEst.setText("Consultar");
        btnConsultarEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarEstActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel1.setText("Estadisticas por Jugador                    Partido Cod.  N°:");

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(36, 36, 36)
                                .addComponent(lblId_Partido))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelMainLayout.createSequentialGroup()
                                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(26, 26, 26)
                                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombresJug)
                                            .addComponent(cmbCodJugador, 0, 230, Short.MAX_VALUE)
                                            .addComponent(numPases)
                                            .addComponent(numGoles)
                                            .addComponent(numAsist)
                                            .addComponent(distRec)
                                            .addComponent(numAma)
                                            .addComponent(numRojas)))
                                    .addComponent(jSeparator2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbPartido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(cmbEstJugador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEstNombreJug)
                                    .addComponent(btnConsultarEst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1))))
                        .addGap(35, 35, 35))))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId_Partido)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbCodJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultar))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombresJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(numPases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(numGoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(numAsist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(distRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(numAma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(numRojas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addGap(12, 12, 12)
                                .addComponent(cmbEstJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEstNombreJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultarEst)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void btnConsultarEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarEstActionPerformed
        limpiarTabla(Tabla_Estadisticas);
        Procesos sql=new Procesos();

        ResultSet rs=sql.consultarDatos(estSQL, "select nombres_jugador,apellidos_jugador,num_goles,e.*\n" +
            "from C##JOEL.estadisticas e,C##JOEL.jugador\n" +
            "where id_partido='"+cmbPartido.getSelectedItem().toString().trim()+"' and e.cod_jugador='"+cmbEstJugador.getSelectedItem()+"'\n" +
            "and e.cod_jugador=jugador.cod_jugador");
        sql.procesarRB(rs, modelo);

        limpiar();
        editarCabecera();
    }//GEN-LAST:event_btnConsultarEstActionPerformed

    private void cmbEstJugadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstJugadorItemStateChanged
        if (evt.getSource()==cmbEstJugador&& swCMB)
        {
            genNombreJugador(cmbEstJugador,txtEstNombreJug);
        }
    }//GEN-LAST:event_cmbEstJugadorItemStateChanged

    private void cmbPartidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPartidoItemStateChanged
        if (evt.getSource()==cmbPartido&& swCMB)
        {
            cargarComboJugadorEst();
            swCMB=true;
        }
    }//GEN-LAST:event_cmbPartidoItemStateChanged

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        limpiarTabla(Tabla_Estadisticas);
        Procesos sql=new Procesos();

        ResultSet rs=sql.consultarDatos(estSQL, "select * from C##JOEL.estadisticas");
        sql.procesarRB(rs, modelo);

        limpiar();
        editarCabecera();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Procesos sql=new Procesos();
        String idPartido=lblId_Partido.getText().trim();
        String codJug=cmbCodJugador.getSelectedItem().toString().trim();
        int pases=Integer.parseInt(numPases.getValue().toString());
        int goles=Integer.parseInt(numGoles.getValue().toString());
        int asist=Integer.parseInt(numAsist.getValue().toString());
        double dist=Double.parseDouble(distRec.getValue().toString());
        int ama=Integer.parseInt(numAma.getValue().toString());
        int roj=Integer.parseInt(numRojas.getValue().toString());

        String sentenciaSQL="insert into C##JOEL.estadisticas values ('"+idPartido+"','"+codJug+"',"+pases+","+goles+","+asist+","+dist+","+ama+","+roj+")";

        if(sql.ingresarDatos(estSQL, sentenciaSQL))
        {
            JOptionPane.showMessageDialog(null, "Datos Agregados Exitosamente.");
        }else
        {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el registro.");
        }

        limpiar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbCodJugadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCodJugadorItemStateChanged
        if (evt.getSource()==cmbCodJugador&& swCMB)
        {
            genNombreJugador(cmbCodJugador,txtNombresJug);

        }
    }//GEN-LAST:event_cmbCodJugadorItemStateChanged

    private void txtNombresJugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresJugActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresJugActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_Estadisticas;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarEst;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbCodJugador;
    private javax.swing.JComboBox cmbEstJugador;
    private javax.swing.JComboBox cmbPartido;
    private javax.swing.JSpinner distRec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblId_Partido;
    private javax.swing.JSpinner numAma;
    private javax.swing.JSpinner numAsist;
    private javax.swing.JSpinner numGoles;
    private javax.swing.JSpinner numPases;
    private javax.swing.JSpinner numRojas;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtEstNombreJug;
    private javax.swing.JTextField txtNombresJug;
    // End of variables declaration//GEN-END:variables
}
