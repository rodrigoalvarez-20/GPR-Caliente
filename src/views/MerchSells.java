package views;

import controllers.ItemController;
import controllers.SaleController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Item;
import models.Sale;
import utils.MySQLConn;

public class MerchSells extends javax.swing.JFrame {
    private Connection cnx = null;
    private ArrayList<Sale> mercData = new ArrayList(), consData = new ArrayList();
    private final String[] saleHeaders = new String[]{"ID", "Nombre", "Categoria", "Precio", "Cantidad", "Total", "Fecha"};
    private ArrayList<Item> mercItemsData = new ArrayList();
    
    /**
     * Creates new form MerchSells
     */
    public MerchSells() {
        initComponents();
        setItemsOfMercSale();
        this.setLocationRelativeTo(null);
    }

    private void setItemsOfMercSale(){
        try {
            cnx = MySQLConn.getConn();
            mercItemsData = new ItemController().getListItems(cnx, "mercancias");
            this.cbItemMerc.removeAllItems();
            for(Item mercItem : mercItemsData){
                this.cbItemMerc.addItem(mercItem.getNombre());
            }
            mercData = new SaleController().getAllSales(cnx, "mercancias");

            DefaultTableModel model = new DefaultTableModel();

            model.setColumnIdentifiers(saleHeaders);

            this.tbSaleMerc.setModel(model);

            for(Sale sl : mercData){
                String categoria = sl.getCategoria().substring(0,1).toUpperCase() + sl.getCategoria().substring(1);
                model.addRow(new Object[]{ sl.getId(), sl.getNombre(), categoria, sl.getPrecio(), sl.getCantidad(), sl.getTotal(), sl.getFecha() } );
            }
            
        }catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
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

        jLabel11 = new javax.swing.JLabel();
        cbItemMerc = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCantMerc = new javax.swing.JTextField();
        btnReload = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSaleMerc = new javax.swing.JTable();
        btnBuy = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Selecciona un item:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        getContentPane().add(cbItemMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 220, -1));

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cantidad solicitada:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        txtCantMerc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtCantMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 230, -1));

        btnReload.setText("Refrescar");
        btnReload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReloadMouseClicked(evt);
            }
        });
        getContentPane().add(btnReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, -1, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Registro de ventas de mercancias");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        tbSaleMerc.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbSaleMerc);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 840, 410));

        btnBuy.setText("Comprar");
        btnBuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuyMouseClicked(evt);
            }
        });
        getContentPane().add(btnBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, -1, -1));

        btnBack.setText("Regresar");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg_3.jpeg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.setVisible(false);
        new Inventory().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnBuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuyMouseClicked
        if(!this.txtCantMerc.getText().isEmpty()){
            Item selectedItemForSale = mercItemsData.get(this.cbItemMerc.getSelectedIndex());
            int cant = Integer.parseInt(this.txtCantMerc.getText());
            try {
                int sale = new SaleController().newSale(cnx, selectedItemForSale.getId(), cant);
                if(sale > 0){
                    selectedItemForSale.setStock(selectedItemForSale.getStock() - cant);
                    int hasExecuted = new ItemController().save(cnx, selectedItemForSale);
                    if(hasExecuted <= 0) JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al actualizar los datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    JOptionPane.showMessageDialog(rootPane, "Gracias por su compra", "Compra realizada", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al generar la compra", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error con la sentencia SQL", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }finally {
                setItemsOfMercSale();
            }
        }else {
            JOptionPane.showMessageDialog(this, "La cantidad ingresada es invalidad", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuyMouseClicked

    private void btnReloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReloadMouseClicked
        setItemsOfMercSale();
    }//GEN-LAST:event_btnReloadMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MerchSells.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MerchSells.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MerchSells.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MerchSells.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MerchSells().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnReload;
    private javax.swing.JComboBox<String> cbItemMerc;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbSaleMerc;
    private javax.swing.JTextField txtCantMerc;
    // End of variables declaration//GEN-END:variables
}
