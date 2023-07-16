package com.project.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private Long id;
    private LocalDateTime regdate;
    private Long count;
    private String title;           // NN
    private String content;         // NN
    private Boolean is_file;        // NN


    private String game_id;         // NN
    private Long user_id;   // FK   // NN

    @ToString.Exclude
    private User user;

    @ToString.Exclude
    @Builder.Default
    private List<Attachment> files = new ArrayList<>();
}
