package repository.news;

import model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsRepository extends JpaRepository<News, Integer> {
    // получить все записи по убыванию даты DateCreation
    List<News> findAllByOrderByDateCreationDesc();
    // получить все записи, отфильтрованные по полю AuthorId, расположенные по убыванию даты DateCreation
    List<News> findByAuthorIdOrderByDateCreationDesc(Integer AuthorId);
    void deleteAllByAuthorId(Integer AuthorId);
}
