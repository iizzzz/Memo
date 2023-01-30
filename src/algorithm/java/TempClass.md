[AnswerDto]
public class AnswerDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private String content;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private long answerId;
        private String content;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @Getter @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long answerId;
        private String content;
        private Boolean secret;

        public static Response of(Answer answer) {
            return Response.builder()
                    .answerId(answer.getAnswerId())
                    .content(answer.getContent())
                    .secret(answer.getSecret())
                    .build();
        }
    }
}

[MemberDto]
public class MemberDto {
@Getter
public static class Post {
@NotBlank
@Email
private String email;

        private String password;

        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;

        private String password;

        @NotSpace(message = "회원 이름은 공백이 아니어야 합니다")
        private String name;

        @NotSpace(message = "휴대폰 번호는 공백이 아니어야 합니다")
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다")
        private String phone;

        private Member.MemberStatus memberStatus;


        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }
    }

    @Getter @Setter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String email;
        private String name;
        private String phone;
        private Member.MemberStatus memberStatus;
        private int stampCount;
        private List<String> roles;

        public static Response of(Member member) {
            return Response.builder()
                    .memberId(member.getMemberId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .memberStatus(member.getMemberStatus())
                    .stampCount(member.getStamp().getStampCount())
                    .roles(member.getRoles())
                    .build();
        }
    }
}

[QuestionDto]
public class QuestionDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private long memberId;
        private String title;
        private String content;
        private Boolean secret;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private long questionId;
        private String title;
        private String content;
        private Boolean secret;

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleResponse {
        private String title;
        private String content;
        private Boolean secret;
        private Question.QuestionStatus status;
        private List<Answer> answers;
        private LocalDateTime createdTime;

        public static SingleResponse of(Question question) {
            return SingleResponse.builder()
                    .title(question.getTitle())
                    .content(question.getContent())
                    .secret(question.getSecret())
                    .status(question.getStatus())
                    .answers(AnswerDto.Response.of())
        }
    }
}

[Answer]
@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long answerId;
@Column(nullable = false)
private String content;
@Column(nullable = false, length = 20)
private Boolean secret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public void addMember(Member member) {
        this.member = member;
    }

    public void addQuestion(Question question) {
        this.question = question;
    }
}

[Member]
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 150)
    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Stamp stamp;

    // 사용자 등록 시, 사용자의 권한 추가
    // RDB는 컬렉션을 저장할수 없으므로 컬렉션 객체인걸 어노테이션으로 명시
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public void setOrder(Order order) {
        orders.add(order);
        if (order.getMember() != this) {
            order.setMember(this);
        }
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
        if (stamp.getMember() != this) {
            stamp.setMember(this);
        }
    }
}

[Question]
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 20)
    private Boolean secret;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private QuestionStatus status = QuestionStatus.REGISTRATION;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    public void addMember(Member member) {
        this.member = member;
    }

    public Question(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public enum QuestionStatus {
        REGISTRATION("질문 생성 상태"),
        ANSWERED("답변 완료 상태"),
        DELETE("질문 삭제 상태");

        @Getter
        private String status;

        QuestionStatus(String status) {
            this.status = status;
        }
    }
}

[ExceptionCode]

    NOT_AUTHORIZED(403, "Not Logined Member"),

[QuestionService] - create 메소드
List<String> roles = member.getRoles();

        if (!member.getRoles().contains("USER") && member.getRoles().contains("ADMIN")) {
            throw new BusinessLogicException(ExceptionCode.NOT_AUTHORIZED);
        }

[MemberStatus, Secret Enum]
@Getter
public enum MemberStatus {
ACTIVE("활동중"),
SLEEP("휴면 상태"),
QUIT("탈퇴 상태");

    @Getter
    private String status;

    MemberStatus(String status) {
        this.status = status;
    }
}
