package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//String er PrimaryKey her UserName
public interface MemberRepository extends JpaRepository<Member, String> {
}
