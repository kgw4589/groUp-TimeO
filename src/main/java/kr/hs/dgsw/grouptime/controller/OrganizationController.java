package kr.hs.dgsw.grouptime.controller;

import kr.hs.dgsw.grouptime.dto.BaseResponse;
import kr.hs.dgsw.grouptime.dto.CreateOrganizationDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationResponseDTO;
import kr.hs.dgsw.grouptime.service.AffiliationService;
import kr.hs.dgsw.grouptime.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;
    private final AffiliationService affiliationService;

    @GetMapping("/{organizationId}")
    public BaseResponse<OrganizationResponseDTO> getOrganization(@PathVariable Long organizationId) {
        return new BaseResponse(200, "단체 조회 성공", organizationService.getOrganization(organizationId));
    }

    @PostMapping
    public BaseResponse<Long> createOrganization(@RequestBody CreateOrganizationDTO organizationDTO) {
        return new BaseResponse(200, "단체 생성 성공", organizationService.createOrganization(organizationDTO));
    }

    @PostMapping("/affiliation")
    public BaseResponse<Void> affiliation(@RequestParam Long userId, @RequestParam Long organizationId) {
        affiliationService.createAffiliation(userId, organizationId);
        return new BaseResponse(200, "단체 소속 성공");
    }

    @PutMapping
    public BaseResponse<Void> update(@RequestBody OrganizationDTO organizationDTO) {
        organizationService.update(organizationDTO);
        return new BaseResponse(200, "단체 수정 성공");
    }

    @DeleteMapping("/{organizationId}")
    public BaseResponse<Void> delete(@PathVariable Long organizationId) {
        organizationService.delete(organizationId);
        return new BaseResponse(200, "단체 삭제 성공");
    }

    @DeleteMapping("/affiliation/{affiliationId}")
    public BaseResponse<Void> deleteAffiliation(@PathVariable Long affiliationId) {
        organizationService.delete(affiliationId);
        return new BaseResponse(200, "단체 소속 삭제 완료");
    }
}
