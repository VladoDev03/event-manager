package com.example.SpringProject.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("SELECT e FROM Event e " +
            "Where (:title IS NULL OR e.title LIKE %:title%)")
    List<Event> getAllByName(@Param("title") String title);

    @Query("SELECT e FROM Event e WHERE e.creator.username = :creatorUsername")
    List<Event> getAllByCreatorUserName(@Param("creatorUsername") String creatorName);

    @Query("SELECT e FROM Event e WHERE e.updateDate = :date")
    List<Event> getAllByDate(LocalDate date);

    @Query("SELECT e FROM Event e WHERE " +
            "(:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:date IS NULL OR e.updateDate = :date)")
    List<Event> findByTitleAndDate(@Param("title") String title, @Param("date") LocalDate date);
}

