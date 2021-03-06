/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imp_Exp;


import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.common.Logger;
import jxl.read.biff.BiffException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Principal.*;
import Usuarios.Usuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author JL
 */
public class Importacion extends javax.swing.JFrame {

    /**
     * Creates new form Importacion
     */
    public static Statement estSQL=Login.estSQL;
    DefaultTableModel modelo=null;

    public Importacion() {
        initComponents();
        insertImage();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/icono.png"));
        setIconImage(icon);
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana Datos");
        this.setResizable(false);
        cerrar();
        String cabecera[]={};
        String datos[][]={};
        modelo=new DefaultTableModel(datos,cabecera);
        Tabla1.setModel(modelo); 
        
        combo();
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
        panel.add(Imagen);
        panel.repaint();
    }
    public void combo()
    {
        cmbTabla.removeAllItems();
        cmbTabla.addItem("Jugador");
        cmbTabla.addItem("Partido");
        cmbTabla.addItem("Estadisticas");
        cmbTabla.addItem("Detalle_Partido");
    }
   
    public class ImagePanel extends JPanel 
    {
        public ImagePanel()
        {
            //Se crea un método cuyo parámetro debe ser un objeto Graphics
            this.setSize(753,485);
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
        TableColumnModel modCol = tabla.getColumnModel();
        while(modCol.getColumnCount()>0)modCol.removeColumn(modCol.getColumn(0));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        cmbTabla = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        ubic = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 28)); // NOI18N
        jLabel1.setText("Importación y Exportación de Datos");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 10)); // NOI18N
        jLabel2.setText("Seleccionar Tabla para Importar y Exportar:");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Nombre Tabla:");

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tabla1);

        cmbTabla.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        cmbTabla.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jButton1.setText("Importar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jButton3.setText("Exportar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        ubic.setEditable(false);
        ubic.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jButton4.setText("Consultar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ubic)
                            .addComponent(cmbTabla, 0, 218, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(33, 33, 33)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(ubic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiarTabla(Tabla1);
        JFileChooser examinar=new JFileChooser();
        examinar.setFileFilter(new FileNameExtensionFilter("Archivos Excel","xls","xlsx"));
        int opcion=examinar.showOpenDialog(this);
        File archivoExcel=null;
        if(opcion==JFileChooser.APPROVE_OPTION)
        {
            archivoExcel=examinar.getSelectedFile().getAbsoluteFile();
            try{
                Workbook leerExcel=Workbook.getWorkbook(archivoExcel);
                for(int hoja=0;hoja<leerExcel.getNumberOfSheets();hoja++)
                {
                    Sheet hojaP=leerExcel.getSheet(hoja);
                    int columnas=hojaP.getColumns();
                    int filas=hojaP.getRows();
                    JOptionPane.showMessageDialog(null, "Importación Exitosa\nN° de Registros: "+(filas-1));
                    Object data[]=new Object[columnas];
                    for(int fila=0;fila<filas;fila++)
                    {
                        for(int columna=0;columna<columnas;columna++)
                        {
                            if(fila==0)
                            {
                                modelo.addColumn(hojaP.getCell(columna,fila).getContents());
                            }
                            if(fila>=1)
                            {
                                data[columna]=hojaP.getCell(columna,fila).getContents();
                            }
                        }
                        modelo.addRow(data);
                    }
                    
                }
                modelo.removeRow(0);
            } catch (IOException | BiffException ex) {
                java.util.logging.Logger.getLogger(Importacion.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error al almacenar el Excel en el WorkBook");
            }
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(this);

        if(opcion == JFileChooser.APPROVE_OPTION){

            File dir = dialog.getSelectedFile();
            String fl = dir.toString();

            ubic.setText(fl);
        }
         try {      	 
           List<JTable> tb = new ArrayList<JTable>();
           tb.add(Tabla1);
           //-------------------
           export_excel excelExporter = new export_excel(tb, new File(ubic.getText()+".xls"));
           if (excelExporter.export()) {
               JOptionPane.showMessageDialog(null, "¡Datos Exportados con Exito!");
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       
         llama_excel();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiarTabla(Tabla1);
        Procesos sql=new Procesos();
        ResultSet rs=sql.consultarDatos(estSQL, "select * from C##JOEL."+cmbTabla.getSelectedItem().toString().trim());
        sql.procesarRB(rs, modelo);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Procesos sql=new Procesos();

        
        if(cmbTabla.getSelectedItem().toString().equals("Jugador"))
        {
            for(int i=0;i<modelo.getRowCount();i++)
            { 
                String sentenciaSQL="insert into C##JOEL."+cmbTabla.getSelectedItem().toString().trim()+" "
                + "VALUES ('"+modelo.getValueAt(i,0).toString()+"','"+modelo.getValueAt(i,1).toString().trim()+"','"+modelo.getValueAt(i,2).toString().trim()+"','"+modelo.getValueAt(i,3).toString().trim()+"','"+modelo.getValueAt(i,4).toString().trim()+"',TO_DATE('"+modelo.getValueAt(i,5).toString().trim()+"','YYYY/MM/DD'),'"+modelo.getValueAt(i,6).toString().trim()+"',"+modelo.getValueAt(i,7).toString().trim()+")";
                System.out.println(sentenciaSQL);
                sql.ingresarDatos(estSQL, sentenciaSQL);
            

            }
               JOptionPane.showMessageDialog(null, "Datos Guardados en la tabla: "+cmbTabla.getSelectedItem().toString().trim());
           
        }else
         if(cmbTabla.getSelectedItem().toString().equals("Estadisticas"))
         {
             for(int i=0;i<Tabla1.getRowCount();i++)
            { 
                String sentenciaSQL="insert into C##JOEL."+cmbTabla.getSelectedItem().toString().trim()+" "
                + "VALUES ('"+modelo.getValueAt(i,0).toString().trim()+"','"+modelo.getValueAt(i,1).toString().trim()+"','"+modelo.getValueAt(i,2).toString().trim()+"','"+modelo.getValueAt(i,3).toString().trim()+"','"+modelo.getValueAt(i,4).toString().trim()+"',TO_DATE('"+modelo.getValueAt(i,5).toString().trim()+"','YYYY-MM-DD'),'"+modelo.getValueAt(i,6).toString().trim()+"',"+modelo.getValueAt(i,7).toString().trim()+")";
                sql.ingresarDatos(estSQL, sentenciaSQL);
            

            }
             JOptionPane.showMessageDialog(null, "Datos Guardados en la tabla: "+cmbTabla.getSelectedItem().toString().trim());
         }   
        else
         if(cmbTabla.getSelectedItem().toString().equals("Detalle_Partido"))
         {
             for(int i=0;i<Tabla1.getRowCount();i++)
            { 
                String sentenciaSQL="insert into C##JOEL."+cmbTabla.getSelectedItem().toString().trim()+" "
                + "VALUES ('"+modelo.getValueAt(i,0).toString().trim()+"','"+modelo.getValueAt(i,1).toString().trim()+"','"+modelo.getValueAt(i,2).toString().trim()+"','"+modelo.getValueAt(i,3).toString().trim()+"','"+modelo.getValueAt(i,4).toString().trim()+"',TO_DATE('"+modelo.getValueAt(i,5).toString().trim()+"','YYYY-MM-DD'),'"+modelo.getValueAt(i,6).toString().trim()+"',"+modelo.getValueAt(i,7).toString().trim()+")";
                sql.ingresarDatos(estSQL, sentenciaSQL);
            

            }
             JOptionPane.showMessageDialog(null, "Datos Guardados en la tabla: "+cmbTabla.getSelectedItem().toString().trim());
         }else
         {
             for(int i=0;i<Tabla1.getRowCount();i++)
            { 
                String sentenciaSQL="insert into C##JOEL."+cmbTabla.getSelectedItem().toString().trim()+" "
                + "VALUES ('"+modelo.getValueAt(i,0).toString().trim()+"','"+modelo.getValueAt(i,1).toString().trim()+"','"+modelo.getValueAt(i,2).toString().trim()+"','"+modelo.getValueAt(i,3).toString().trim()+"','"+modelo.getValueAt(i,4).toString().trim()+"',TO_DATE('"+modelo.getValueAt(i,5).toString().trim()+"','YYYY-MM-DD'),'"+modelo.getValueAt(i,6).toString().trim()+"',"+modelo.getValueAt(i,7).toString().trim()+")";
                sql.ingresarDatos(estSQL, sentenciaSQL);
            

            }
             JOptionPane.showMessageDialog(null, "Datos Guardados en la tabla: "+cmbTabla.getSelectedItem().toString().trim());
         }

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
        public void llama_excel()
	 {
		 try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ubic.getText()+".xls");
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
	 }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla1;
    private javax.swing.JComboBox cmbTabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField ubic;
    // End of variables declaration//GEN-END:variables
}
