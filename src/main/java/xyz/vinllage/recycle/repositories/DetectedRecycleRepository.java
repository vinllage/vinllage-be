package xyz.vinllage.recycle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.vinllage.recycle.entities.DetectedRecycle;

public interface DetectedRecycleRepository extends JpaRepository<DetectedRecycle, Long>, QuerydslPredicateExecutor<DetectedRecycle> {
}
