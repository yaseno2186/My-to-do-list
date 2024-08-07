package dev.Yass.to_do_list.repository;

import dev.Yass.to_do_list.model.task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<task, Long> {
    List<task> findByDone(boolean done);

    List<task> findByDeleted(boolean deleted);

    List<task> findByArchived(boolean archived);

    List<task> findByTitle(String title);
}