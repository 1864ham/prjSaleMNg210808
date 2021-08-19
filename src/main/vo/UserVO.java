package vo;

import java.util.Date;

public class UserVO {
    private int userId;
    private String userName;
    private String password;
    private Date createOn;
    private int quota;
    private String products;
    private Date expiresOn;
    private String adminUser;
    private String id;

    //user 정보를 들고와서 담을 때
    public UserVO(int userId, String userName, String password,
                  Date createOn, int quota, String products,
                  Date expiresOn, String adminUser, String id) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createOn = createOn;
        this.quota = quota;
        this.products = products;
        this.expiresOn = expiresOn;
        this.adminUser = adminUser;
        this.id = id;
    }

    public UserVO(String userName, String password, String id) {
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}