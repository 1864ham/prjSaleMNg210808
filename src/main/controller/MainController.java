package controller;

import ui.LoginFrm;
import ui.MainFrm;
import vo.UserVO;

public class MainController {
    public static void main(String[] args) {
        MainController.forwardControl("Login");
    }
    private static  MainController mainController;
    private UserVO session;

    private MainController(){}
    public static MainController getInstance(){
        if (mainController == null) mainController = new MainController();
        return mainController; //singleton pattern 오직 1개의 인스턴스만 활용
    }

    public UserVO getSession() { return session; }
    public void setSession(UserVO session) { this.session = session; }

    public static void forwardControl(String cmd) {
        if (cmd.equals("Login")) {
            new LoginFrm();
        } else if (cmd.equals("Main")) {
            new MainFrm();
        } else if (cmd.equals("Join")) {

        }
    }

    public void setSession() {
    }
}