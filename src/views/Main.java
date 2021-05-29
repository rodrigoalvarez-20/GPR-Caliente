/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ItemController;
import controllers.SaleController;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import models.Item;
import models.Sale;
import utils.MySQLConn;

/**
 *
 * @author rodri
 */
public class Main extends javax.swing.JFrame {
    private Connection cnx = null;
    private ArrayList<Item> inventoryData = new ArrayList(), mercItemsData = new ArrayList(), consItemsData = new ArrayList();
    private ArrayList<Sale> mercData = new ArrayList(), consData = new ArrayList();
    private final String[] headers = new String[]{"ID", "Nombre", "Categoria", "Precio", "Stock"};
    private final String[] saleHeaders = new String[]{"ID", "Nombre", "Categoria", "Precio", "Cantidad", "Total", "Fecha"};
    private Item selectedItem = new Item();
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.tbInv.setDefaultEditor(Object.class, null);
        setDataForInventory("");
        setItemsOfMercSale();
        setItemsOfConsSale();
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
    
    private void setItemsOfConsSale(){
        try {
            cnx = MySQLConn.getConn();
            consItemsData = new ItemController().getListItems(cnx, "insumos");
            this.cbItemCons.removeAllItems();
            for(Item consItem : consItemsData){
                this.cbItemCons.addItem(consItem.getNombre());
            }
            consData = new SaleController().getAllSales(cnx, "insumos");

            DefaultTableModel model = new DefaultTableModel();

            model.setColumnIdentifiers(saleHeaders);

            this.tbSaleCons.setModel(model);

            for(Sale sl : consData){
                String categoria = sl.getCategoria().substring(0,1).toUpperCase() + sl.getCategoria().substring(1);
                model.addRow(new Object[]{ sl.getId(), sl.getNombre(), categoria, sl.getPrecio(), sl.getCantidad(), sl.getTotal(), sl.getFecha() } );
            }
            
        }catch (ClassNotFoundException | SQLException ex) {
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

        mainTab = new javax.swing.JTabbedPane();
        tbInventario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbInv = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        cbType = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbTypeFilter = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        tbMercancia = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbItemMerc = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCantMerc = new javax.swing.JTextField();
        btnBuyMerc = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSaleMerc = new javax.swing.JTable();
        tbInsumos = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbItemCons = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtCantCons = new javax.swing.JTextField();
        btnBuyCons = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSaleCons = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

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

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setText("Item Seleccionado");

        txtID.setEditable(false);
        txtID.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtName.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Insumos", "Mercancias" }));

        btnAdd.setText("Añadir");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnUpdate.setText("Actualizar");
        btnUpdate.setEnabled(false);
        btnUpdate.setPreferredSize(new java.awt.Dimension(98, 29));
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        jLabel2.setText("ID:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Categoria:");

        jLabel5.setText("Precio:");

        jLabel6.setText("Stock:");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setText("Filtrar items");

        cbTypeFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Insumos", "Mercancias" }));

        btnFilter.setText("Aplicar");
        btnFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFilterMouseClicked(evt);
            }
        });

        btnDelete.setEnabled(false);
        btnDelete.setLabel("Eliminar");
        btnDelete.setPreferredSize(new java.awt.Dimension(98, 29));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tbInventarioLayout = new javax.swing.GroupLayout(tbInventario);
        tbInventario.setLayout(tbInventarioLayout);
        tbInventarioLayout.setHorizontalGroup(
            tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(tbInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbInventarioLayout.createSequentialGroup()
                        .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(tbInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(54, 54, 54)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(tbInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(34, 34, 34)))
                        .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbType, 0, 1, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbInventarioLayout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(tbInventarioLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTypeFilter, 0, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(515, 515, 515))))
        );
        tbInventarioLayout.setVerticalGroup(
            tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbInventarioLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTypeFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tbInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        mainTab.addTab("Inventario", tbInventario);

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Selecciona un item:");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cantidad solicitada:");

        txtCantMerc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuyMerc.setText("Comprar");
        btnBuyMerc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuyMercMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setText("Registro de ventas");

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

        javax.swing.GroupLayout tbMercanciaLayout = new javax.swing.GroupLayout(tbMercancia);
        tbMercancia.setLayout(tbMercanciaLayout);
        tbMercanciaLayout.setHorizontalGroup(
            tbMercanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbMercanciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbMercanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbMercanciaLayout.createSequentialGroup()
                        .addGroup(tbMercanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(cbItemMerc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(tbMercanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(txtCantMerc))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuyMerc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(363, 363, 363))
                    .addGroup(tbMercanciaLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane3)
        );
        tbMercanciaLayout.setVerticalGroup(
            tbMercanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbMercanciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbMercanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tbMercanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbItemMerc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantMerc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuyMerc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainTab.addTab("Mercancia", tbMercancia);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Selecciona un item:");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cantidad solicitada:");

        txtCantCons.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuyCons.setText("Comprar");
        btnBuyCons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuyConsMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setText("Registro de ventas");

        tbSaleCons.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbSaleCons);

        javax.swing.GroupLayout tbInsumosLayout = new javax.swing.GroupLayout(tbInsumos);
        tbInsumos.setLayout(tbInsumosLayout);
        tbInsumosLayout.setHorizontalGroup(
            tbInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbInsumosLayout.createSequentialGroup()
                        .addGroup(tbInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(cbItemCons, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(tbInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(txtCantCons))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuyCons, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(363, 363, 363))
                    .addGroup(tbInsumosLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane2)
        );
        tbInsumosLayout.setVerticalGroup(
            tbInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tbInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbItemCons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantCons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuyCons))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainTab.addTab("Consumibles", tbInsumos);

        getContentPane().add(mainTab);

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
                setItemsOfMercSale();
                setItemsOfConsSale();
            }
        }else {
            JOptionPane.showMessageDialog(rootPane, "Los campos no pueden quedar vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddMouseClicked

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
                setItemsOfMercSale();
                setItemsOfConsSale();
            }
        }else {
            JOptionPane.showMessageDialog(rootPane, "Los campos no pueden quedar vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        try {
            int hasExecuted = new ItemController().delete(cnx, selectedItem);
            if(hasExecuted > 0){
                //this.tbInv.getModel().addTableModelListener(null);
                JOptionPane.showMessageDialog(rootPane, "Se ha eliminado correctamente el item", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al eliminar el item", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error con la sentencia SQL", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }finally {
            setDataForInventory("");
            setItemsOfMercSale();
            setItemsOfConsSale();
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnBuyMercMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuyMercMouseClicked
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
                setDataForInventory("");
                setItemsOfMercSale();
                setItemsOfConsSale();
            }
        }else {
            JOptionPane.showMessageDialog(this, "La cantidad ingresada es invalidad", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuyMercMouseClicked

    private void btnBuyConsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuyConsMouseClicked
        if(!this.txtCantCons.getText().isEmpty()){
            Item selectedItemForSale = consItemsData.get(this.cbItemCons.getSelectedIndex());
            int cant = Integer.parseInt(this.txtCantCons.getText());
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
                setDataForInventory("");
                setItemsOfMercSale();
                setItemsOfConsSale();
            }
        }else {
            JOptionPane.showMessageDialog(this, "La cantidad ingresada es invalidad", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuyConsMouseClicked

    private boolean validateData(){
        return !this.txtName.getText().isEmpty() || !this.txtPrice.getText().isEmpty() || !this.txtStock.getText().isEmpty();
    }
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuyCons;
    private javax.swing.JButton btnBuyMerc;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbItemCons;
    private javax.swing.JComboBox<String> cbItemMerc;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JComboBox<String> cbTypeFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane mainTab;
    private javax.swing.JPanel tbInsumos;
    private javax.swing.JTable tbInv;
    private javax.swing.JPanel tbInventario;
    private javax.swing.JPanel tbMercancia;
    private javax.swing.JTable tbSaleCons;
    private javax.swing.JTable tbSaleMerc;
    private javax.swing.JTextField txtCantCons;
    private javax.swing.JTextField txtCantMerc;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
