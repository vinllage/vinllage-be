package xyz.vinllage.recycle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.vinllage.recycle.entities.RecycleResult;

public interface RecycleRepository extends JpaRepository<RecycleResult, Long>, QuerydslPredicateExecutor<RecycleResult> {
}
