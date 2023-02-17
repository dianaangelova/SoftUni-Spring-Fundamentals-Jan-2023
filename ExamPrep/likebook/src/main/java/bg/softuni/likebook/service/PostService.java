package bg.softuni.likebook.service;

import bg.softuni.likebook.model.dto.CreatePostDTO;
import bg.softuni.likebook.model.dto.PostDTO;
import bg.softuni.likebook.model.entity.MoodEntity;
import bg.softuni.likebook.model.entity.PostEntity;
import bg.softuni.likebook.model.entity.UserEntity;
import bg.softuni.likebook.model.enums.MoodTypeEnum;
import bg.softuni.likebook.repository.MoodRepository;
import bg.softuni.likebook.repository.PostRepository;
import bg.softuni.likebook.repository.UserRepository;
import bg.softuni.likebook.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    private final MoodRepository moodRepository;

    @Autowired
    public PostService(PostRepository postRepository, CurrentUser currentUser, UserRepository userRepository, MoodRepository moodRepository) {
        this.postRepository = postRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
    }

    public boolean create(CreatePostDTO createPostDTO) {

        MoodTypeEnum moodName = MoodTypeEnum.valueOf(createPostDTO.getMood());
        MoodEntity mood = this.moodRepository.findByName(moodName);

        Optional<UserEntity> user = this.userRepository.findByUsername(currentUser.getUsername());

        PostEntity post = new PostEntity();
        post.setContent(createPostDTO.getContent());
        post.setMood(mood);
        post.setCreator(user.get());

        this.postRepository.save(post);

        return true;
    }

    public List<PostDTO> getPostsOwnedBy(Long ownerId) {
        return this.postRepository.findByCreatorId(ownerId)
                .stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getPostsNotOwnedBy(Long ownerId) {
        return this.postRepository.findByCreatorIdNot(ownerId)
                .stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());

    }

    public void likePostWithId(Long idPost, String username) {

       PostEntity postToLike =  this.postRepository.findById(idPost).orElse(null);
       UserEntity user = this.userRepository.findByUsername(username).orElse(null);

        Objects.requireNonNull(postToLike).getUserLikes().add(user);

        this.postRepository.save(postToLike);

    }

    public void removePostById(Long idPost) {

        PostEntity postToRemove =  this.postRepository.findById(idPost).orElse(null);
        this.postRepository.delete(postToRemove);
    }
}