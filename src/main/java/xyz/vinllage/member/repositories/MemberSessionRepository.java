package xyz.vinllage.member.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.vinllage.member.entities.MemberSession;

public interface MemberSessionRepository extends CrudRepository<MemberSession, String> {
}
