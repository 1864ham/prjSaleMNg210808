package ui;

import vo.UserVO;
import controller.MainController;
import dao.DaoUser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrm extends BasicFrm implements ActionListener {
    JLabel lbId, lbPw;
    JTextField tfId;
    JPasswordField pfPw;
    JButton btnLogin, btnCancel, btnJoin;
    JPanel pnlN, pnlC, pnlS;

    public LoginFrm() {
        super(300,150,"Login");
    }

    @Override
    public void init() {
        setResizable(false);
        pnlN = new JPanel();
        pnlC = new JPanel();
        pnlS = new JPanel();
        lbId = new JLabel("ID");
        lbPw = new JLabel("PW");

        tfId = new JTextField(10);
        pfPw = new JPasswordField(10);
        tfId.setText("ADMIN");
        pfPw.setText("1");
        btnLogin = new JButton("로그인");
        btnCancel = new JButton("취소");
        btnJoin = new JButton("가입");
        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        btnJoin.addActionListener(this);
    }
    @Override
    public void arrange() {
        pnlN.add(lbId);
        pnlN.add(tfId);
        pnlC.add(lbPw);
        pnlC.add(pfPw);
        pnlS.add(btnLogin);
        pnlS.add(btnCancel);
        pnlS.add(btnJoin);
        add(pnlN, BorderLayout.NORTH);
        add(pnlC, BorderLayout.CENTER);
        add(pnlS, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = tfId.getText();
        String pw = new String(pfPw.getPassword());
        if (e.getSource() == btnLogin) {
            if (id == null || id.equals("")) {
                JOptionPane.showMessageDialog(null, "ID를 확인하세요");
                tfId.requestFocus();
                return;
            }
            if (pw == null || pw.equals("")) {
                JOptionPane.showMessageDialog(null, "Password를 확인하세요");
                pfPw.requestFocus();
                return;
            }
            UserVO userVO = new DaoUser().checkLogin(id,pw);
            if (userVO == null) {
                JOptionPane.showMessageDialog(null, "ID가 존재하지 않습니다.");
                tfId.setText(""); pfPw.setText("");
                tfId.requestFocus();
                return;
            }
            dispose();
            MainController.forwardControl("Main");
        } else if (e.getSource() == btnCancel) {

        } else if (e.getSource() == btnJoin) {
        }
    }
}