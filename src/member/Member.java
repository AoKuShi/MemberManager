package member;

import java.sql.Date;

public class Member {
  private int no;
  private String id;
  private String password;
  private String nickname;
  private Date regdate;

  public Member(int no, String id, String password, String nickname, Date string) {
    this.no = no;
    this.id = id;
    this.password = password;
    this.nickname = nickname;
    this.regdate = string;
  }

  public Member(String id, String password, String nickname) {
    this.id = id;
    this.password = password;
    this.nickname = nickname;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getRegdate() {
    return regdate;
  }
  public void setRegdate(Date regdate) {
    this.regdate = regdate;
  }

  @Override
  public String toString() {
    return "[" + no + ", " + id + ", " + password + ", " + nickname + ", " + regdate + "]";
  }
}
