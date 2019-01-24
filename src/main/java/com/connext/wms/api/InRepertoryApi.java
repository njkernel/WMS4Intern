package com.connext.wms.api;

import com.connext.wms.api.dto.InRepertoryDetailDTO;
import com.connext.wms.api.util.EntityAndDto;
import com.connext.wms.entity.InRepertory;
import com.connext.wms.service.InRepertoryService;
import com.connext.wms.util.AES;
import com.connext.wms.util.Constant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
@Api(tags = "入库单接口")
@Slf4j
public class InRepertoryApi {
    private final InRepertoryService inRepertoryService;
    private final Constant constant;
    private ObjectMapper objectMapper;
    private final EntityAndDto entityAndDto;

    @Autowired
    public InRepertoryApi(InRepertoryService inRepertoryService, Constant constant, ObjectMapper objectMapper, EntityAndDto entityAndDto) {
        this.inRepertoryService = inRepertoryService;
        this.constant = constant;
        this.objectMapper = objectMapper;
        this.entityAndDto = entityAndDto;
    }

    @PostMapping("/inRepertoryOrder")
    @ApiOperation(value = "入库单接口")
    public HttpStatus inRepertoryOrder(@RequestParam String tokens,
                                       @RequestParam String inRepoId,
                                       @RequestParam String orderId,
                                       @RequestParam String channelId,
                                       @RequestParam String expressId,
                                       @RequestParam String expressCompany,
                                       @RequestParam String detailDTOS
    ) throws IOException {
        //token校验
        if (Objects.equals(AES.AESDncode(constant.TOKENS, tokens), inRepoId)) {
            return HttpStatus.FORBIDDEN;
        }
        if (validData(inRepoId, orderId, channelId, expressId, expressCompany, detailDTOS)) {
            return HttpStatus.BAD_REQUEST;
        }
        List<InRepertoryDetailDTO> repertoryDetailDTOS = objectMapper.readValue(detailDTOS, new TypeReference<List<InRepertoryDetailDTO>>() {
        });
        Date nowTime = new Date();
        InRepertory inRepertory = InRepertory.builder()
                .inRepoId(inRepoId)
                .orderId(orderId)
                .channelId(channelId)
                .expressId(expressId)
                .expressCompany(expressCompany)
                .inRepoStatus(constant.INIT_STATUS)
                .syncStatus(constant.SYNC_FALSE_STATES)
                .receivingRepo(constant.RECEIVING_REPERTORY)
                .createTime(nowTime)
                .reviser(constant.REVISER)
                .reviseTime(nowTime)
                .repertoryDetails(entityAndDto.toEntity(inRepoId, repertoryDetailDTOS))
                .build();
        try {
            inRepertoryService.initInRepertory(inRepertory);
        } catch (Exception e) {
            log.error("inRepertory has been existed: " + inRepertory.getInRepoId());
            return HttpStatus.CONFLICT;
        }
        return HttpStatus.ACCEPTED;
    }

    private boolean validData(String inRepoId, String orderId, String channelId, String expressId, String expressCompany, String detailDTOS) {
        return inRepoId.length() <= 50 && orderId.length() <= 50 && channelId.length() <= 50 && expressId.length() <= 50 && expressCompany.length() <= 30 && detailDTOS.length() > 2;
    }
}
