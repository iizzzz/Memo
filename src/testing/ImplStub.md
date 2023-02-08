import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Stub {

    // 멤버 생성
    public static Member createMember() {
        Member member = Member.builder()
                .memberName("관리자")
                .memberType(MemberType.KAKAO)
                .email("abc@abc.com")
                .role(Role.ADMIN)
                .password("1234")
                .build();
        ReflectionTestUtils.setField(member, "memberId", 1L);
        ReflectionTestUtils.setField(member, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(member, "modifiedAt", LocalDateTime.now());

        return member;
    }

    public static Post createPost() {
        List<Comment> comments = new ArrayList<>();
        Post post = new Post(1L, "제목", "내용", 0, Post.Kind.GENERAL, createMember().getMemberName(), createMember(), comments);
        ReflectionTestUtils.setField(post, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(post, "modifiedAt", LocalDateTime.now());

        return post;
    }

    public static PostDto.Post testPost() {
        return PostDto.Post.of(
                1L,
                "글제목",
                "내용",
                "일반"
        );
    }
    
    public static PostDto.Patch testPatch() {
        return PostDto.Patch.of(
                1L,
                "수정된제목",
                "수정된내용",
                "후기"
        );
    }

    public static List<Post> createPostPage() {
        Post post1 = Post.of(1L, "제목1", "내용1", Post.Kind.GENERAL);
        ReflectionTestUtils.setField(post1, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(post1, "modifiedAt", LocalDateTime.now());

        Post post2 = Post.of(2L, "제목2", "내용2", Post.Kind.REVIEW);
        ReflectionTestUtils.setField(post2, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(post2, "modifiedAt", LocalDateTime.now());

        return List.of(post1,post2);
    }
    
    public static Page<Post> createPostPage(Pageable pageable) {
        Post post1 = Post.of(1L, "제목1", "내용1", Post.Kind.GENERAL);
        ReflectionTestUtils.setField(post1, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(post1, "modifiedAt", LocalDateTime.now());
        
        Post post2 = Post.of(2L, "제목2", "내용2", Post.Kind.REVIEW);
        ReflectionTestUtils.setField(post2, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(post2, "modifiedAt", LocalDateTime.now());
        
        List<Post> posts = List.of(post1,post2);
        
        return new PageImpl<>(posts, pageable, posts.size());
    }
}