package com.zipple.module.member.email;

import com.zipple.module.member.email.model.EmailAgentRequest;
import com.zipple.module.member.email.model.EmailGeneralRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "이메일 회원가입 API")
@RestController
@RequestMapping(value = "/api/v1/email")
@RequiredArgsConstructor
public class EmailRegisterController {

    private final EmailRegisterService emailRegisterService;

    @Operation(
            summary = "이메일 일반 회원 가입"
            , description = "이메일 회원 가입이기 때문에 JWT 없이 요청"
    )
    @PostMapping(
            value = "/general",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> emailRegisterGeneral(
            @Parameter(
                    name = "EmailGeneralRequest",
                    description = "이메일 회원 일반 가입 요청 데이터"
            )
            @RequestBody EmailGeneralRequest emailGeneralRequest
            ) {
        emailRegisterService.emailRegisterGeneral(emailGeneralRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "이메일 공인중개사 회원 가입"
            , description = "이메일 회원 가입이기 때문에 JWT 없이 요청"
    )
    @PostMapping(
            value = "/agent",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> emailRegisterAgent(
            @Parameter(
                    name = "realEstateAgencyCertificate",
                    description = "중개 사무소 등록증"
            )
            @RequestPart(value = "realEstateAgencyCertificate") MultipartFile realEstateAgencyCertificate,
//            @Parameter(
//                    name = "businessCertificate",
//                    description = "사업자 등록증"
//            )
//            @RequestPart(value = "businessCertificate",required = false) MultipartFile businessCertificate,
//            @Parameter(
//                    name = "realEstateAgentLicense",
//                    description = "공인중개사 자격증"
//            )
//            @RequestPart(value = "realEstateAgentLicense", required = false) MultipartFile realEstateAgentLicense,
//            @Parameter(
//                    name = "agentImage",
//                    description = "공인 중개사 본인 인증 사진"
//            )
//            @RequestPart(value = "agentImage", required = false) MultipartFile agentImage,
//            @Parameter(
//                    name = "emailAgentRequest",
//                    description = "이메일 회원 공인중개사 가입 요청 데이터"
//            )
            @RequestBody EmailAgentRequest emailAgentRequest
            ) {
        List<MultipartFile> agentCertificationDocuments = List.of(realEstateAgencyCertificate);
        emailRegisterService.emailRegisterAgent(emailAgentRequest, agentCertificationDocuments);
//        List<MultipartFile> agentCertificationDocuments = List.of(realEstateAgencyCertificate, businessCertificate, realEstateAgentLicense);
//        emailRegisterService.emailRegisterAgent(emailAgentRequest, agentCertificationDocuments, agentImage);
        return ResponseEntity.ok().build();
    }
}
