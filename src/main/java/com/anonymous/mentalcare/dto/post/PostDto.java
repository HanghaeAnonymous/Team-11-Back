package com.anonymous.mentalcare.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostWrittenRequestDto{
        private String title;
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostUpdateRequestDto{
        private String content;
    }
}
