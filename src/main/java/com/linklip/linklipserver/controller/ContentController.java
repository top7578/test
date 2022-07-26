package com.linklip.linklipserver.controller;

import com.linklip.linklipserver.domain.Content;
import com.linklip.linklipserver.dto.SaveLinkRequest;
import com.linklip.linklipserver.dto.ServerResponse;
import com.linklip.linklipserver.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.linklip.linklipserver.constant.SuccessResponse.SAVE_LINK_SUCCESS;

@Api(value = "ContentController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    @ApiOperation(value = "링크 저장 API v1", notes = "[GYJB-50] 링크 저장 API 생성")
    @ApiResponses({
        @ApiResponse(code = 201, message = "링크 저장 완료"),
        @ApiResponse(code = 400, message = "잘못된 요청입니다"),
        @ApiResponse(code = 404, message = "요청 경로 오류"),
        @ApiResponse(code = 500, message = "서버 내부 오류")
    })
    @PostMapping("/v1/link")
    public ServerResponse saveLinkV1(@RequestBody @Valid SaveLinkRequest request) {
        Content content = Content.builder().linkUrl(request.getUrl()).build();
        contentService.saveContent(content);

        return new ServerResponse(
                SAVE_LINK_SUCCESS.getStatus(),
                SAVE_LINK_SUCCESS.getSuccess(),
                SAVE_LINK_SUCCESS.getMessage());
    }
}
