package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import dao.DaoCustomer;
import dao.DaoProduct;

import java.awt.*;

public class PnlSale extends JPanel {
    private JTextField tfPrice;
    private JTextField tfEa;
    private JTextField tfTotal;
    private JTextField tfQuan;
    private JComboBox cbCate;
    private JComboBox cbProd;
    private String price;
    private DefaultTableModel model;
    private JTable table;
    private JLabel lbProdImg, lbProdImgBack;

    public PnlSale() {
        DaoProduct daoProduct = new DaoProduct();
        setLayout(null);

        JLabel lblNewLabel = new JLabel("\uB9E4\uCD9C \uCC98\uB9AC");
        lblNewLabel.setBounds(12, 10, 57, 15);
        add(lblNewLabel);

        JLabel lbWriteDate = new JLabel("\uC804\uD45C \uC791\uC131\uC77C");
        lbWriteDate.setBounds(12, 44, 70, 15);
        add(lbWriteDate);

        JLabel lbCustomer = new JLabel("\uACE0\uAC1D\uBA85");
        lbCustomer.setBounds(343, 46, 47, 15);
        add(lbCustomer);

        tfPrice = new JTextField(10);
        tfPrice.setBounds(418, 70, 69, 21);
        add(tfPrice);

        tfEa = new JTextField(10);
        tfEa.setBounds(489, 70, 47, 21);
        add(tfEa);

        tfQuan = new JTextField(10);
        tfQuan.setBounds(489, 70, 47, 21);
        add(tfQuan);

        tfTotal = new JTextField(10);
        tfTotal.setBounds(538, 70, 69, 21);
        add(tfTotal);

        JLabel lbProdTitle = new JLabel("\uC81C\uD488 \uC774\uBBF8\uC9C0");
        lbProdTitle.setBounds(696, 70, 163, 15);
        add(lbProdTitle);

        lbProdImgBack = new JLabel("");
        lbProdImgBack.setBounds(609, 95, 240, 309);
        lbProdImgBack.setOpaque(true);
        lbProdImgBack.setBackground(Color.white);
        Border bevelBorder = new BevelBorder(BevelBorder.RAISED,
                Color.LIGHT_GRAY, Color.LIGHT_GRAY.darker(),
                Color.LIGHT_GRAY, Color.LIGHT_GRAY.brighter());
        lbProdImgBack.setBorder(bevelBorder);

        model = new DefaultTableModel(new String[]{"구분", "상품명", "단가", "수량", "합계"}, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 96, 595, 309);
        add(scrollPane);

        JButton addBtn = new JButton("\uCD94\uAC00");
        addBtn.setBounds(439, 415, 69, 23);
        add(addBtn);
        addBtn.addActionListener( e -> {
            String cate = cbCate.getSelectedItem().toString();
            String item = cbProd.getSelectedItem().toString();
            String price = tfPrice.getText();
            String ea = tfEa.getText();
            String tot = tfTotal.getText();
            String[] arrRow = {cate, item, price, ea, tot};
            if (ea.equals("")) {
                JOptionPane.showMessageDialog(null, "수량을 입력해주세요");
                return;
            }
            model.addRow(arrRow);
        });

        JButton delBtn = new JButton("\uC0AD\uC81C");
        delBtn.setBounds(368, 415, 69, 23);
        add(delBtn);
        delBtn.addActionListener( e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "No Selected Row");
                return;
            }
            model.removeRow(row);
        });

        JButton saleBtn = new JButton("\uACB0 \uC81C");
        saleBtn.setBounds(510, 415, 97, 23);
        add(saleBtn);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.GRAY);
        panel_1.setBounds(12, 415, 202, 25);
        add(panel_1);
        panel_1.setLayout(null);

        JLabel lbTotTitle = new JLabel("\uD569   \uACC4:");
        lbTotTitle.setBounds(30, 3, 45, 17);
        lbTotTitle.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        lbTotTitle.setForeground(Color.WHITE);
        panel_1.add(lbTotTitle);

        JLabel lbTotal = new JLabel("");
        lbTotal.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        lbTotal.setForeground(Color.WHITE);
        lbTotal.setBounds(98, 4, 57, 15);
        panel_1.add(lbTotal);

        cbCate = new JComboBox(daoProduct.getCate());
        cbCate.setBounds(12, 69, 120, 23);
        add(cbCate);

        cbProd = new JComboBox(daoProduct.getProdList(cbCate.getSelectedItem().toString()));
        cbProd.setBounds(137, 69, 279, 23);
        add(cbProd);
        if (cbCate.getSelectedItem() != null) {
            tfPrice.setText(daoProduct.getProdPrice(cbProd.getSelectedItem().toString().split("/")[0]));
        }


        JComboBox cbCustomer = new JComboBox(new DaoCustomer().getCustAll());

        cbCustomer.setBounds(391, 43, 216, 23);
        add(cbCustomer);

        lbProdImg = new JLabel(resizeImg(new DaoProduct().getProdImg(cbProd.getSelectedItem().toString().split("/")[0])));
        lbProdImg.setBounds(604, 95, 255, 309);

        add(lbProdImg);
        add(lbProdImgBack);

        cbCate.addActionListener(e-> {
            String cateW = cbCate.getSelectedItem().toString();
            Object[] oArr = null;
            cbProd.removeAllItems();
            try {
                oArr = daoProduct.getProdList(cateW);
            } catch (Exception e1) {
            }
            if (oArr.length > 0)
                for (int i = 0; i < oArr.length; i++) {
                    cbProd.addItem(oArr[i]);
                    String pId1 = cbProd.getSelectedItem().toString().split("/")[0];
                    try {
                        tfPrice.setText(daoProduct.getProdPrice(pId1));
                        ImageIcon img = daoProduct.getProdImg(pId1);
                        lbProdImg.setIcon(resizeImg(img));
                    } catch (Exception e2) { }
                    tfEa.setText("");
                    tfTotal.setText("");
                }
        });
        cbProd.addActionListener(e-> {
            if (cbProd.getSelectedItem() != null) {
                String cbProdWord =
                        cbProd.getSelectedItem().toString();
                if (!cbProdWord.equals(null) ||
                        !cbProdWord.equals("")) {
                    String pId2 = cbProd.getSelectedItem().toString().split("/")[0];
                    try {
                        tfPrice.setText(daoProduct.getProdPrice(pId2));
                        ImageIcon img = daoProduct.getProdImg(pId2);
                        lbProdImg.setIcon(resizeImg(img));
                    } catch (Exception e2) { }
                }
            }
            tfEa.setText("");
            tfTotal.setText("");
        });

    }

    private ImageIcon resizeImg(ImageIcon img){
        int imgW = img.getIconWidth();
        int imgH = img.getIconHeight();
        int dynamicH = imgH*230/imgW;
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(230, dynamicH,
                java.awt.Image.SCALE_SMOOTH);
        return img = new ImageIcon(newimg);
    }
}