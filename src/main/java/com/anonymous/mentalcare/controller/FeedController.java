package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.FeedResponseDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

//    @GetMapping("/feeds/{userId}")
//    public FeedResponseDto getFeed(@PathVariable Long userId){
//        return feedService.getFeed(userId);
//    }

    @GetMapping("/feeds")
    public FeedResponseDto getFeed(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return feedService.getFeed(userDetails.getUser());
    }
}
