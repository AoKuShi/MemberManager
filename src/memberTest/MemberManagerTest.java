package memberTest;

import java.util.List;

import member.Member;
import member.MemberDAO;
import member.MemberService;

public class MemberManagerTest {
  public static void main(String[] args) {
    MemberService service = new MemberService(new MemberDAO());
    List<Member> memberList = service.listAll();

    for (Member m : memberList) {
      System.out.println(m.toString());
    }

    Member member = service.read(3);
    if (member != null) {
      System.out.println(member.toString());
    }

    // 회원 등록
    member = new Member("test2401", "1234", "nick2401");
    if (service.regist(member)) {
      System.out.println("회원 등록 성공.");
    } else {
      System.out.println("회원 등록 실패");
    }

    // 회원 수정(비밀번호, 닉네임 변경)
    member = new Member(5, null, "4321", "nick2402", null);
    service.edit(member, "1234");

    // 회원 삭제
    service.remove(1);


  }
}
