package com.startuper.postonapp.repositories;


import com.startuper.postonapp.entities.Category;
import com.startuper.postonapp.entities.Post;
import com.startuper.postonapp.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTest {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

 //   @Autowired
  //  public PostRepositoryTest(IPostRepository repository) {
  //      this.postRepository = repository;
 //   }

    @Test
    public void IPostRepository_SaveAll_ReturnsSavedPost() {


        Post savedPost = this.createTestablePost("New Post", "A great Post");
        Assertions.assertThat(savedPost).isNotNull();
        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);
    }


    @Test
    public void PostRepository_GetAll_ReturnsMoreThanOnePost() {
        // Arrange
       this.createTestablePost("New Post", "A great Post");
       this.createTestablePost("New Post 2", "A great Post 2");

        // Act
        List<Post> postList =  (List<Post>) postRepository.findAll();

        // Assertions
        Assertions.assertThat(postList).isNotNull();
        Assertions.assertThat(postList.size()).isEqualTo(2);
    }


    @Test
    public void PostRepository_FindById_ReturnsOnePost() {
        // Arrange
      Post savedPost =   this.createTestablePost("New Post", "A great Post");

        // Act
        Post postFromDb = postRepository.findById(savedPost.getId()).get();

        // Assertions
        Assertions.assertThat(postFromDb).isNotNull();
    }

    @Test
    public void PostRepository_UpdatePost_ReturnsPost() {

        // Arrange
        Post savedPost = this.createTestablePost("New Post", "A great Post");
        savedPost.setContent("A big Post");
        savedPost.setTitle("My lovely Post");

        // Act
        Post updatedPost = postRepository.save(savedPost);

        // Assertions
        Assertions.assertThat(updatedPost).isNotNull();
        Assertions.assertThat(updatedPost.getContent()).isEqualTo("A big Post");
        Assertions.assertThat(updatedPost.getTitle()).isEqualTo("My lovely Post");
    }


    @Test
    public void PostRepository_DeletePost_ReturnsPost() {

        // Arrange
        Post savedPost = this.createTestablePost("New Post", "A great Post");

        // Act
        postRepository.deleteById(savedPost.getId());
        Optional<Post> postReturn = postRepository.findById(savedPost.getId());

        // Assertions
       Assertions.assertThat(postReturn).isEmpty();
    }


    private Post createTestablePost(String title, String content) {
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();
        User savedUser = userRepository.save(user);

        Category category1 = Category.builder()
                .categoryName("sport")
                .build();
        Category category2 = Category.builder()
                .categoryName("music")
                .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);


        Post post = Post.builder()
                .title(title)
                .content(content)
                .dateCreated(new Date())
                .user(savedUser)
                .categories(categories)
                .build();

        Post savedPost = postRepository.save(post);
        return savedPost;
    }
}
