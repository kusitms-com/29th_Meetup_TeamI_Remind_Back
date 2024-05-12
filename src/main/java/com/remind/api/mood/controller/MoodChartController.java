package com.remind.api.mood.controller;

import com.remind.api.mood.dto.response.MoodChartPagingResponseDto;
import com.remind.api.mood.service.MoodChartService;
import com.remind.core.domain.common.response.ApiSuccessResponse;
import com.remind.core.security.dto.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mood/chart")
@Tag(name = "무드 차트 API", description = "무드 차트, 기분 별 활동 차트 API입니다.")
public class MoodChartController {

    private final MoodChartService moodChartService;

    @Operation(
            summary = "무드 차트 조회"
    )
    @ApiResponse(
            responseCode = "200", description = "무드 차트 조회 성공 응답입니다.", useReturnTypeSchema = true
    )
    @GetMapping
    public ResponseEntity<ApiSuccessResponse<MoodChartPagingResponseDto>> getMoodChart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "년도") @RequestParam("year") Integer year,
            @Parameter(description = "월") @RequestParam("month") Integer month,
            @Parameter(description = "마지막으로 조회한 일") @RequestParam("day") Integer day,
            @Parameter(description = "한 페이지 속 데이터 갯수") @RequestParam("size") Integer size) {
        return ResponseEntity.ok(
                new ApiSuccessResponse<>(moodChartService.getMoodChart(userDetails, year, month, day, size)));
    }
}
