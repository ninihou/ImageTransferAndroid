package idv.david.imagetransferandroid;


public class Member {
    private String name;
    private String password;
    private byte[] logo;

    public Member() {
    }

    public Member(String name, String password, byte[] logo) {
        this.name = name;
        this.password = password;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
}
