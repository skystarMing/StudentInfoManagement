package net.lmq.student.bean;

/**
 * 时间：2019.6.17
 * 功能：状态类
 */
public class Status {
    /**
     * 标识符
     */
    private int id;
    /**
     * 校名
     */
    private String college;
    /**
     * 版本
     */
    private String version;
    /**
     * 作者
     */
    private String author;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 通信地址
     */
    private String address;
    /**
     * 电子邮箱
     */
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Status [id=" + id + ", college=" + college + ", version=" + version + ", author=" + author
                + ", telephone=" + telephone + ", address=" + address + ", email=" + email + "]";
    }
}
