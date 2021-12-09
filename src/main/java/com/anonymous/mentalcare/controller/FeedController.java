package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.FeedResponseDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"피드"})
@RestController
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

//    @GetMapping("/feeds/{userId}")
//    public FeedResponseDto getFeed(@PathVariable Long userId){
//        return feedService.getFeed(userId);
//    }

    @ApiOperation(value = "내가 작성한 글, 댓글 확인하기")
    @GetMapping("/api/feeds")
    public ResponseEntity<FeedResponseDto> getFeed(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails.getUser().getUserId());
        FeedResponseDto feedResponseDto = feedService.getFeed(userDetails.getUser());

        return ResponseEntity.ok()
                        .body(feedResponseDto);
    }
}
