package bg.softuni.likebook.repository;

import bg.softuni.likebook.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByCreatorId(Long ownerId);
    List<PostEntity> findByCreatorIdNot(Long ownerId);

    Optional<PostEntity> findById(Long idPost);
}
