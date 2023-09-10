package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//String er PrimaryKey her UserName <Member ref til Entity Member, String ref til Primary Key UserName>
public interface MemberRepository extends JpaRepository<Member, String> {

    List<Member> findMemberByReservationsIsTrue();
}
