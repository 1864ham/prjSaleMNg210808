package ui;

import dao.DaoUser;
import vo.UserVO;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlUser extends JPanel {
    private JTextField tfName;
    private JTextField tfId;
    private JTextField pfPw;
    private JTextField rePw;
    private JTextField tfSearch;
    private final JButton btnSearch = new JButton("\uC870\uD68C");
    public PnlUser() {
        setLayout(null);
        DaoUser dao = new DaoUser();
        JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uC790 \uAD00\uB9AC");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel.setBounds(71, 13, 171, 54);
        add(lblNewLabel);

        JButton btnRegist = new JButton("\uB4F1\uB85D");
        btnRegist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfId.getText();
                String name = tfName.getText();
                String pw = new String(pfPw.getText());
                String rePass = new String(rePw.getText());

                if (name.equals("")) return;
                if (id.equals("")) return;
                if (pw.equals("")) return;
                if (rePass.equals("")) return;
                if(!pw.equals(rePass)) return;
                dao.registerUser(new UserVO(name, id, pw));
            }
        });
        btnRegist.setBounds(71, 308, 171, 21);
        add(btnRegist);

        JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
        lblNewLabel_1.setBounds(30, 104, 57, 15);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("ID");
        lblNewLabel_2.setBounds(30, 150, 57, 15);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Password");
        lblNewLabel_3.setBounds(30, 201, 57, 15);
        add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("RePass");
        lblNewLabel_4.setBounds(30, 253, 57, 15);
        add(lblNewLabel_4);

        tfName = new JTextField();
        tfName.setBounds(150, 101, 163, 21);
        add(tfName);
        tfName.setColumns(10);

        tfId = new JTextField();
        tfId.setBounds(150, 147, 163, 21);
        add(tfId);
        tfId.setColumns(10);

        pfPw = new JTextField();
        pfPw.setBounds(150, 198, 163, 21);
        add(pfPw);
        pfPw.setColumns(10);

        rePw = new JTextField();
        rePw.setBounds(150, 250, 163, 21);
        add(rePw);
        rePw.setColumns(10);

        tfSearch = new JTextField();
        tfSearch.setBounds(393, 101, 163, 21);
        add(tfSearch);
        tfSearch.setColumns(10);
        btnSearch.setBounds(579, 101, 66, 21);
        add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(393, 140, 336, 223);
        add(scrollPane);

        JLabel lblNewLabel_5 = new JLabel("\uC0AC\uC6A9\uC790 \uBAA9\uB85D");
        lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(408, 26, 123, 29);
        add(lblNewLabel_5);

    }
}