package com.server.seb41_main_11.program;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int cost;

    @Column(length = 150)
    private String image;

    @Column(nullable = false)
    private int userCount;

    @Column(nullable = false)
    private int userMax;

    @Column(nullable = false)
    private LocalDateTime dateStart;

    @Column(nullable = false)
    private LocalDateTime dateEnd;

    @Column
    private String announce;

    @Column
    private String zoomLink;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private SymptomTypes symptomTypes;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNSELOR_ID")
    @JsonIgnore
    private Counselor counselor;

    void addCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

    public enum SymptomTypes {
        A("우울증"),
        B("무기력");

        @Getter
        private String status;

        SymptomTypes(String status) {
            this.status = status;
        }
    }
}

