package views;

import controllers.ItemController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import models.Item;
import utils.MySQLConn;

public class Inventory extends javax.swing.JFrame {
    private Connection cnx = null;
    private ArrayList<Item> inventoryData = new ArrayList(), mercItemsData = new ArrayList(), consItemsData = new ArrayList();
    private final String[] headers = new String[]{"ID", "Nombre", "Categoria", "Precio", "Stock"};
    private Item selectedItem = new Item();
    /**
     * Creates new form Inventory
     */
    public Inventory() {
        initComponents();
        this.tbInv.setDefaultEditor(Object.class, null);
        setDataForInventory("");
        this.setLocationRelativeTo(null);
    }
    
    private void setDataForInventory(String query){
        try {
            cnx = MySQLConn.getConn();
            System.out.println("Conexion establecida");
            inventoryData = new ItemController().getListItems(cnx, query);
            DefaultTableModel model = new DefaultTableModel();
            
            model.setColumnIdentifiers(headers);
            this.tbInv.setModel(model);
            //this.tbInv.getSelectionModel().removeListSelectionListener(tbInv);
            
            for(Item item : inventoryData){
                String categoria = item.getCategoria().substring(0,1).toUpperCase() + item.getCategoria().substring(1);
                model.addRow(new Object[]{ item.getId(), item.getNombre(), categoria, item.getPrecio(), item.getStock() } );
            }
            selectedItem = new Item();
            this.btnUpdate.setEnabled(false);
            this.btnDelete.setEnabled(false);
            this.txtID.setText("");
            this.txtName.setText("");
            this.txtPrice.setText("");
            this.txtStock.setText("");
            this.cbType.setSelectedIndex(0);
            setListener();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setListener(){
        this.tbInv.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(tbInv.getSelectedRow() >= 0){
                selectedItem = inventoryData.get(tbInv.getSelectedRow());
                this.txtID.setText(String.valueOf(selectedItem.getId()));
                this.txtName.setText(selectedItem.getNombre());
                this.txtPrice.setText(String.valueOf(selectedItem.getPrecio()));
                this.txtStock.setText(String.valueOf(selectedItem.getStock()));
                String categoria = selectedItem.getCategoria().substring(0,1).toUpperCase() + selectedItem.getCategoria().substring(1);
                this.cbType.setSelectedItem(categoria);
                this.btnUpdate.setEnabled(true);
                this.btnDelete.setEnabled(true);
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnToMerch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbInv = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnToIns = new javax.swing.JButton();
        cbTypeFilter = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbType = new javax.swing.JComboBox<>();
        txtPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Inventario");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 6, 980, 40));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Item Seleccionado");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 24));

        btnToMerch.setText("Venta de mercancias");
        btnToMerch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnToMerchMouseClicked(evt);
            }
        });
        getContentPane().add(btnToMerch, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, -1, -1));

        tbInv.setModel(new javax.swing.table.DefaultTableModel(
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
        tbInv.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbInv);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 50, 970, 360));

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Filtrar items");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, 24));

        btnToIns.setText("Venta de insumos");
        btnToIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnToInsMouseClicked(evt);
            }
        });
        getContentPane().add(btnToIns, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, -1, -1));

        cbTypeFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Insumos", "Mercancias" }));
        getContentPane().add(cbTypeFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 150, -1));

        btnFilter.setText("Aplicar");
        btnFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFilterMouseClicked(evt);
            }
        });
        getContentPane().add(btnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 98, -1));

        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 596, -1, -1));

        txtID.setEditable(false);
        txtID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 120, -1));

        jLabel3.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ID:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 80, -1));

        txtName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 120, -1));

        jLabel4.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Categoria:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, -1, -1));

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Insumos", "Mercancias" }));
        getContentPane().add(cbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 181, -1));

        txtPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, 181, -1));

        jLabel6.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Stock:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 500, -1, -1));

        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 120, -1));

        jLabel10.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Precio:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 100, -1));

        jLabel11.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nombre:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, -1));

        btnAdd.setText("A??adir");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 500, 98, -1));

        btnDelete.setEnabled(false);
        btnDelete.setLabel("Eliminar");
        btnDelete.setPreferredSize(new java.awt.Dimension(98, 29));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, -1, -1));

        btnUpdate.setText("Actualizar");
        btnUpdate.setEnabled(false);
        btnUpdate.setPreferredSize(new java.awt.Dimension(98, 29));
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 500, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg_3.jpeg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFilterMouseClicked
        String query = this.cbTypeFilter.getSelectedItem().toString().equals("Todos") ? "" : this.cbTypeFilter.getSelectedItem().toString().toLowerCase();
        setDataForInventory(query);
    }//GEN-LAST:event_btnFilterMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        if(validateData()){
            Item toAdd = new Item(0, this.txtName.getText(), this.cbType.getSelectedItem().toString().toLowerCase(), Double.parseDouble(this.txtPrice.getText()), Integer.parseInt(this.txtStock.getText()));
            try {
                int hasExecuted = new ItemController().save(cnx, toAdd);
                if(hasExecuted > 0){
                    JOptionPane.showMessageDialog(rootPane, "Se ha agregado correctamente el item", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al agregar el item", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error con la sentencia SQL", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }finally {
                setDataForInventory("");
                //setItemsOfMercSale();
                //setItemsOfConsSale();
            }
        }else {
            JOptionPane.showMessageDialog(rootPane, "Los campos no pueden quedar vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private boolean validateData(){
        return !this.txtName.getText().isEmpty() || !this.txtPrice.getText().isEmpty() || !this.txtStock.getText().isEmpty();
    }
    
    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        try {
            int hasExecuted = new ItemController().delete(cnx, selectedItem);
            if(hasExecuted > 0){
                JOptionPane.showMessageDialog(rootPane, "Se ha eliminado correctamente el item", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al eliminar el item", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error con la sentencia SQL", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }finally {
            setDataForInventory("");
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        if(validateData()){
            Item toUpdate = new Item(this.selectedItem.getId(), this.txtName.getText(), this.cbType.getSelectedItem().toString().toLowerCase(), Double.parseDouble(this.txtPrice.getText()), Integer.parseInt(this.txtStock.getText()));
            try {
                int hasExecuted = new ItemController().save(cnx, toUpdate);
                if(hasExecuted > 0){
                    JOptionPane.showMessageDialog(rootPane, "Se ha actualizado correctamente el item", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al actualizar el item", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error con la sentencia SQL", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }finally {
                setDataForInventory("");
            }
        }else {
            JOptionPane.showMessageDialog(rootPane, "Los campos no pueden quedar vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnToMerchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToMerchMouseClicked
        this.setVisible(false);
        new MerchSells().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnToMerchMouseClicked

    private void btnToInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToInsMouseClicked
        this.setVisible(false);
        new InsSells().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnToInsMouseClicked

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
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnToIns;
    private javax.swing.JButton btnToMerch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JComboBox<String> cbTypeFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbInv;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
