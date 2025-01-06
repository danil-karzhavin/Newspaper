package karzhavin.newspaper.repository.news;

import karzhavin.newspaper.model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface INewsRepository extends JpaRepository<News, Integer> {
    // получить все записи по убыванию даты DateCreation
    List<News> findAllByOrderByDateCreationDesc();

    @Query("SELECT n " +
            "FROM News n " +
            "WHERE n.dateCreation BETWEEN :startDate AND :endDate")
    List<News> findAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    // получить все записи, отфильтрованные по полю AuthorId, расположенные по убыванию даты DateCreation
    List<News> findAllByAuthorIdOrderByDateCreationDesc(Integer authorId);
    void deleteAllByAuthorId(Integer AuthorId);
}
