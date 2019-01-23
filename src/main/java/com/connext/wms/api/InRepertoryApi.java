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
        if (Objects.equals(AES.AESDncode(constant.TOKENS, tokens), inRepoId) && validData(inRepoId, orderId, channelId, expressId, expressCompany, detailDTOS)) {
            List<InRepertoryDetailDTO> repertoryDetailDTOS = objectMapper.readValue(detailDTOS, new TypeReference<List<InRepertoryDetailDTO>>() {
            });
            Date nowTime = new Date();
            InRepertory inRepertory = new InRepertory(inRepoId, orderId, channelId, expressId, expressCompany, constant.INIT_STATUS, constant.SYNC_FALSE_STATES, constant.RECEIVING_REPERTORY, nowTime, constant.REVISER, nowTime, entityAndDto.toEntity(inRepoId, repertoryDetailDTOS));
            inRepertoryService.initInRepertory(inRepertory);
            return HttpStatus.valueOf(201);
        } else {
            return HttpStatus.valueOf(400);
        }
    }

    private boolean validData(String inRepoId, String orderId, String channelId, String expressId, String expressCompany, String detailDTOS) {
        return inRepoId.length() <= 50 && orderId.length() <= 50 && channelId.length() <= 50 && expressId.length() <= 50 && expressCompany.length() <= 30;
    }
}
