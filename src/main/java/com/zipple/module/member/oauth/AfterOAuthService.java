package com.zipple.module.member.oauth;

import com.zipple.common.utils.GetMember;
import com.zipple.module.member.common.entity.AgentUser;
import com.zipple.module.member.common.entity.GeneralUser;
import com.zipple.module.member.common.entity.User;
import com.zipple.module.member.common.entity.category.AgentType;
import com.zipple.module.member.common.entity.category.HousingType;
import com.zipple.module.member.oauth.model.AgentUserRequest;
import com.zipple.module.member.oauth.model.GeneralUserRequest;
import com.zipple.module.member.common.repository.AgentUserRepository;
import com.zipple.module.member.common.repository.GeneralUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AfterOAuthService {

    private final GeneralUserRepository generalUserRepository;
    private final AgentUserRepository agentUserRepository;

    private User user() {
        return GetMember.getCurrentMember();
    }

    public void generalRegister(GeneralUserRequest generalUserRequest) {
        GeneralUser generalUser = GeneralUser.builder()
                .user(user())
                .generalName(generalUserRequest.getGeneralName())
                .generalAddress(generalUserRequest.getGeneralAddress())
                .housingType(HousingType.fromValue(generalUserRequest.getHousingType()))
                .mandatoryTerms("Y")
                .optionalTerms(generalUserRequest.getMarketingNotificationTerms())
                .build();
        generalUserRepository.save(generalUser);
    }

    public void agentRegisters(AgentUserRequest agentUserRequest, List<MultipartFile> agentCertificationDocuments, MultipartFile agentImage) {
        AgentUser agentUser = AgentUser.builder()
                .user(user())
                .agentType(AgentType.fromValue(agentUserRequest.getAgentType()))
                .businessName(agentUserRequest.getBusinessName())
                .agentRegistrationNumber(agentUserRequest.getAgentRegistrationNumber())
                .primaryContactNumber(agentUserRequest.getPrimaryContactNumber())
                .officeAddress(agentUserRequest.getOfficeAddress())
                .ownerName(agentUserRequest.getOwnerName())
                .ownerContactNumber(agentUserRequest.getOwnerContactNumber())
                .singleHouseholdExpertRequest(agentUserRequest.getSingleHouseholdExpertRequest())
                .agentOfficeRegistrationCertificate(documentsPath(agentCertificationDocuments, 0))
                .businessRegistrationCertification(documentsPath(agentCertificationDocuments, 1))
                .agentLicense(documentsPath(agentCertificationDocuments, 2))
                .agentImage(imagePath(agentImage))
                .externalLink(
                        (agentUserRequest.getExternalLink() != null) ? agentUserRequest.getExternalLink() : ""
                )
                .mandatoryTerms("Y")
                .optionalTerms(agentUserRequest.getMarketingNotificationTerms())
                .build();
        agentUserRepository.save(agentUser);
    }

    private String documentsPath(List<MultipartFile> agentCertificationDocuments, int count) {
        int index = 0;
        for (MultipartFile file : agentCertificationDocuments) {
            if (index == count) {
                try {
                    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String baseDir = "/Users/dongxxi/Downloads/upload";
                    Long userId = user().getId();
                    String directoryPath = baseDir + "/" + userId + "/" + date;

                    Path directory = Paths.get(directoryPath);
                    if (!Files.exists(directory)) {
                        Files.createDirectories(directory);
                    }

                    String fileName = file.getOriginalFilename();
                    if (fileName == null || fileName.isEmpty()) {
                        fileName = "default_filename";
                    }
                    Path filePath = directory.resolve(fileName);

                    file.transferTo(filePath.toFile());

                    return directoryPath + "/" + fileName;
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            index++;
        }
        return "";
    }

    private String imagePath(MultipartFile agentCertificationDocuments) {
        return "";
    }


}
