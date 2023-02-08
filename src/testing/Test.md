# Test

[Entity]

1. Private 생성자 생성 (PostDto 필드만큼 or 원하는만큼)
2. of 메서드 생성

   @Override
   public boolean equals(Object o) {
   if (this == o) return true;
   if (o == null || getClass() != o.getClass()) return false;
   Post post = (Post) o;
   return postId.equals(post.getPostId());
   }

   @Override
   public int hashCode() {
   return Objects.hash(postId);
   }


[DTO]

### Post
1. 모든 필드로 생성자 생성 후 이름을 of로 변경,  DTO의 Post에 toEntity() 정의
2. DTO -> Entity로 변환하는 Entity.of() 메서드 정의
   (DTO의 Post에 맞는 필드로 엔티티의 of메소드 생성)
   toEntity()의 파라미터로 연관관계가 매핑된 엔티티 삽입

### Patch
1. 모든 필드로 생성자 생성 후 이름을 of로 변경

### Response
1. 모든 필드로 생성자 생성 후 이름을 of로 변경
2. from 메소드 정의
   파라미터로 엔티티를 받아서 Response 리턴, 필드는 엔티티의 값 가져옴


[Stub]

1. 