/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partido;


import Principal.Procesos;
import Estadisticas.*;
import Principal.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author JL
 */
public class Ventana_Partido extends javax.swing.JFrame {

    /**
     * Creates new form Ventana_Partido
     */
    public static String idPartido=null;
    public static Statement estSQL=Login.estSQL;
    DefaultTableModel modelo=null;
    boolean swCMB=true;
    public Ventana_Partido() 
    {
        initComponents();
        insertImage();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/icono.png"));
        setIconImage(icon);
        //conexionBD();
        txtFechaPartido.requestFocus();
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana Partido");
        this.setResizable(false);
        cerrar();
        String cabecera[]={};
        String datos[][]={};
        modelo=new DefaultTableModel(datos,cabecera);
        Tabla_Partido.setModel(modelo); 
        swCMB=false;
        genClave();
        cargarArbitro();
        cargarComboEstadio();
        cargarComboLugar();
        cargarImg();
        
        swCMB=true;
        
    }
    public ImageIcon ajustarImagen(String dir)
	{
            ImageIcon iconAux=new ImageIcon(getClass().getResource(dir));
            ImageIcon tempIcon= new ImageIcon(iconAux.getImage().getScaledInstance(214, -1, Image.SCALE_DEFAULT));
            return tempIcon;
	}
    public void limpiarTabla(JTable tabla)
    {
        while(tabla.getRowCount()>0)
        {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
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
        String cadeql="select max(id_partido) from C##JOEL.partido";
        ResultSet rs=sql.consultarUltimoId(estSQL,cadeql);
        Object valorO;
        try
        {   
            rs.next();
            valorO=rs.getObject(1);    
            if(valorO!=null)
            {
                txtCodPartido.setText(Integer.parseInt(valorO.toString().trim())+1+"");
            }else
            {
                txtCodPartido.setText(222000+"");
            }
        }catch(Exception e)
        {    
        }   
    }
    public void editarCabecera()
    {
        JTableHeader th; 
        th = Tabla_Partido.getTableHeader(); 
        Font fuente = new Font("Consolas", Font.BOLD, 12); 
        th.setFont(fuente); 
    }
    public void cargarComboEstadio()
    {
        cmbEstadio.removeAllItems();
        cmbEstadio.addItem("Monumental");
        cmbEstadio.addItem("Casa Blanca");
        cmbEstadio.addItem("Capwell");
        cmbEstadio.addItem("Atahualpa");
        cmbEstadio.addItem("Bombonera");
        cmbEstadio.addItem("Old Trafford");
        cmbEstadio.addItem("Camp Nou");
    }
    public void cargarComboLugar()
    {
        cmbLugar.removeAllItems();
        cmbLugar.addItem("Guayaquil");
        cmbLugar.addItem("Quito");
        cmbLugar.addItem("Buenos Aires");
        cmbLugar.addItem("Manchester");
        cmbLugar.addItem("Barcelona");
    }
    public void cargarArbitro()
    {
        cmbArbitro.removeAllItems();
        cmbArbitro.addItem("Carlos Vera");
        cmbArbitro.addItem("Sandro Richi");
        cmbArbitro.addItem("Byron Moreno");
        cmbArbitro.addItem("Carlos Amarilla");
        cmbArbitro.addItem("Omar Ponce");
    }
    public void cargarImg()
    {
        if(cmbEstadio.getSelectedIndex()==0)
        {
            lblImg.setIcon(ajustarImagen("/Img/e1.jpg"));
        }
        if(cmbEstadio.getSelectedIndex()==1)
        {
            lblImg.setIcon(ajustarImagen("/Img/e2.jpg"));
        }
        if(cmbEstadio.getSelectedIndex()==2)
        {
            lblImg.setIcon(ajustarImagen("/Img/e3.jpg"));
        }
        if(cmbEstadio.getSelectedIndex()==3)
        {
            lblImg.setIcon(ajustarImagen("/Img/e4.jpg"));
        }
        if(cmbEstadio.getSelectedIndex()==4)
        {
            lblImg.setIcon(ajustarImagen("/Img/e5.png"));
        }
        if(cmbEstadio.getSelectedIndex()==5)
        {
            lblImg.setIcon(ajustarImagen("/Img/e6.jpg"));
        }
        if(cmbEstadio.getSelectedIndex()==6)
        {
            lblImg.setIcon(ajustarImagen("/Img/e7.jpg"));
        }
    }
    public void limpiar()
    {
        txtCodPartido.setText("");
        txtEquipoContrario.setText("");
        txtFechaPartido.setText("");
        txtHora.setText("");
        txtResultado.setText("");
        genClave();
        txtCodPartido.setBackground(Color.WHITE);
        txtFechaPartido.requestFocus();
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
            this.setSize(935,670);
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
        txtCodPartido = new javax.swing.JTextField();
        txtEquipoContrario = new javax.swing.JTextField();
        txtFechaPartido = new javax.swing.JFormattedTextField();
        txtHora = new javax.swing.JFormattedTextField();
        cmbArbitro = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        btnJugadores = new javax.swing.JButton();
        txtResultado = new javax.swing.JFormattedTextField();
        cmbEstadio = new javax.swing.JComboBox();
        lblImg = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Partido = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cmbLugar = new javax.swing.JComboBox();
        btnEstadisticas = new javax.swing.JButton();
        cmbInfoPart = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 28)); // NOI18N
        jLabel1.setText("Información del Partido");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel2.setText("Cod. Partido: ");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Fecha del Encuentro: ");

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel4.setText("Hora del Encuentro: ");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel5.setText("Estadio: ");

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel6.setText("Arbitro Designado: ");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel7.setText("Resultado Final: ");

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel8.setText("Nombre Equipo Contrario: ");

        txtCodPartido.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        txtCodPartido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodPartidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodPartidoKeyTyped(evt);
            }
        });

        txtEquipoContrario.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtFechaPartido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yy"))));
        txtFechaPartido.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        txtHora.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        cmbArbitro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel9.setText("Jugadores:");

        btnJugadores.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnJugadores.setText("Interfaz Jugadores");
        btnJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugadoresActionPerformed(evt);
            }
        });

        try {
            txtResultado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtResultado.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N

        cmbEstadio.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbEstadio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEstadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadioItemStateChanged(evt);
            }
        });

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

        Tabla_Partido.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tabla_Partido);

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel10.setText("Lugar:");

        cmbLugar.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbLugar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEstadisticas.setBackground(new java.awt.Color(255, 255, 255));
        btnEstadisticas.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        btnEstadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/est.png"))); // NOI18N
        btnEstadisticas.setText("Estadísticas");
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });

        cmbInfoPart.setBackground(new java.awt.Color(255, 255, 255));
        cmbInfoPart.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        cmbInfoPart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/inf.png"))); // NOI18N
        cmbInfoPart.setText("Información Partidos");
        cmbInfoPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfoPartActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ball.png"))); // NOI18N

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelMainLayout.createSequentialGroup()
                                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFechaPartido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelMainLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(94, 94, 94)
                                        .addComponent(txtCodPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbLugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEquipoContrario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnJugadores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbArbitro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelMainLayout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)))
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbInfoPart, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEstadisticas)
                        .addGap(18, 18, 18)
                        .addComponent(cmbInfoPart)
                        .addGap(145, 145, 145))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtCodPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtFechaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cmbArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(btnJugadores))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEquipoContrario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cmbEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
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

    private void cmbEstadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadioItemStateChanged
        if (evt.getSource()==cmbEstadio&& swCMB) 
        {
            cargarImg();
        }
    }//GEN-LAST:event_cmbEstadioItemStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Procesos sql=new Procesos();
        
        String id_partido=txtCodPartido.getText().trim();
        String fecha=txtFechaPartido.getText().trim();
        String hora=txtHora.getText().trim();
        String lugar=cmbLugar.getSelectedItem().toString().trim();
        String estadio=cmbEstadio.getSelectedItem().toString().trim();
        String arbitro=cmbEstadio.getSelectedItem().toString().trim();
        String resultado=txtResultado.getText().trim();
        String rival=txtEquipoContrario.getText().trim();
        
        int r1,r2,estado;
        r1=Integer.parseInt(resultado.substring(0,1));
        r2=Integer.parseInt(resultado.substring(2));
        
        if(r1>r2)
        {
            estado=1;
        }else if(r1<r2)
        {
            estado=0;
        }else
            estado=2;
        
        
  
       //String sentenciaSQL="insert into partido values ('"+id_partido+"','"+fecha+" "+hora+"','"+lugar+"','"+estadio+"','"+arbitro+"','"+resultado+"',"+estado+",'"+rival+"')";
         String sentenciaSQL="UPDATE C##JOEL.partido SET  fecha_hora='"+fecha+" "+hora+"',lugar='"+lugar+"',estadio='"+estadio+"',arbitro_designado='"+arbitro+"',resultado='"+resultado+"',estado_partido="+estado+",nombre_equipo_rival='"+rival+"' where id_partido='"+id_partido+"'";                                                                                   
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
        limpiarTabla(Tabla_Partido);
        Procesos sql=new Procesos();
        
        ResultSet rs=sql.consultarDatos(estSQL, "select * from C##JOEL.partido");
        sql.procesarRB(rs, modelo);
        
        limpiar();
        editarCabecera();        
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Procesos obj=new Procesos();
        
        String sentenciaSQL1="DELETE from C##JOEL.detalle_partido where id_partido='"+txtCodPartido.getText().trim()+"'";
        obj.ingresarDatos(estSQL, sentenciaSQL1);
        String sentenciaSQL="DELETE from C##JOEL.partido where id_partido='"+txtCodPartido.getText().trim()+"'";
        if(obj.ingresarDatos(estSQL, sentenciaSQL))
        {
                JOptionPane.showMessageDialog(null, "Datos Eliminados Exitosamente.");
            
        }else
        {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar.");
        }
        limpiar(); 
    
        
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCodPartidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPartidoKeyReleased
         if(evt.getKeyChar()==KeyEvent.VK_B)
        {
            
            
            txtCodPartido.setBackground(Color.red);
            //////////////////////////////////////
            String sentenciaSQL="select * from C##JOEL.partido where id_partido='"+txtCodPartido.getText().trim()+"'";
            Procesos obj=new Procesos();
            ResultSet rs=obj.consultarDatos(estSQL, sentenciaSQL);

            try{
                rs.next();
                
                    txtFechaPartido.setText(String.valueOf(rs.getObject(2)).substring(0,10));
                    txtHora.setText(String.valueOf(rs.getObject(2)).substring(11,16));
                    txtResultado.setText(String.valueOf(rs.getObject(6)));
                    txtEquipoContrario.setText(String.valueOf(rs.getObject(8)));
                    
                    
                

            }
            catch(Exception E1)
            {
               JOptionPane.showMessageDialog(null,"No se puede mostrar.","Error",JOptionPane.ERROR);
                E1.printStackTrace();
            }

        }
    }//GEN-LAST:event_txtCodPartidoKeyReleased

    private void txtCodPartidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPartidoKeyTyped
        if(!Character.isDigit(evt.getKeyChar())|| txtCodPartido.getText().length()==10)
        evt.consume();
    }//GEN-LAST:event_txtCodPartidoKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       
        String id_partido=txtCodPartido.getText().trim();
        String fecha=txtFechaPartido.getText().substring(0,10).trim();
        String hora=txtHora.getText().trim();
        String lugar=cmbLugar.getSelectedItem().toString().trim();
        String estadio=cmbEstadio.getSelectedItem().toString().trim();
        String arbitro=cmbEstadio.getSelectedItem().toString().trim();
        String resultado=txtResultado.getText().trim();
        String rival=txtEquipoContrario.getText().trim();
        
        int r1,r2,estado;
        r1=Integer.parseInt(resultado.substring(0,1));
        r2=Integer.parseInt(resultado.substring(2));
        
        if(r1>r2)
        {
            estado=1;
        }else if(r1<r2)
        {
            estado=0;
        }else
            estado=2;
        
        String sentenciaSQL="UPDATE C##JOEL.partido SET  fecha_hora='"+fecha.trim()+" "+hora.trim()+"',lugar='"+lugar+"',estadio='"+estadio+"',arbitro_designado='"+arbitro+"',resultado='"+resultado+"',estado_partido="+estado+",nombre_equipo_rival='"+rival+"' where id_partido='"+id_partido+"'";
        Procesos obj=new Procesos();
        if(obj.ingresarDatos(estSQL, sentenciaSQL))
        {
            JOptionPane.showMessageDialog(null, "Datos Moficados Exitosamente.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se pudo Modificar.");
        }
        limpiar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugadoresActionPerformed
        idPartido=txtCodPartido.getText().trim();
        Procesos sql=new Procesos();
        String sentenciaSQL="insert into C##JOEL.partido values ('"+txtCodPartido.getText().trim()+"','01/01/2001','a','a','a','a',0,'a')";
        sql.ingresarDatos(estSQL, sentenciaSQL);
        Jugadores_Partido vtn=new Jugadores_Partido();
        vtn.setVisible(true);
        
    }//GEN-LAST:event_btnJugadoresActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        idPartido=txtCodPartido.getText().trim();
        Estadisticas vtn=new Estadisticas();
        vtn.setVisible(true);
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void cmbInfoPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInfoPartActionPerformed
        Consultas_Partido vtn=new Consultas_Partido();
        vtn.setVisible(true);
    }//GEN-LAST:event_cmbInfoPartActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_Partido;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnJugadores;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cmbArbitro;
    private javax.swing.JComboBox cmbEstadio;
    private javax.swing.JButton cmbInfoPart;
    private javax.swing.JComboBox cmbLugar;
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
    private javax.swing.JLabel lblImg;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtCodPartido;
    private javax.swing.JTextField txtEquipoContrario;
    private javax.swing.JFormattedTextField txtFechaPartido;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JFormattedTextField txtResultado;
    // End of variables declaration//GEN-END:variables
}
