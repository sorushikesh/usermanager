package com.bilsora.usermanager.service.serviceImpl;

import com.bilsora.usermanager.constants.ExceptionErrorCode;
import com.bilsora.usermanager.constants.FieldConstant;
import com.bilsora.usermanager.dto.RoleResponse;
import com.bilsora.usermanager.exceptions.NotFoundException;
import com.bilsora.usermanager.repository.RoleRepository;
import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public RoleResponse findByName(String roleName) {

    log.info("Searching for role {}", roleName);
    var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    return roleRepository
        .findByName(roleName)
        .map(role -> new RoleResponse(role.getId(), role.getName()))
        .orElseThrow(
            () ->
                new NotFoundException(
                    HttpStatus.NOT_FOUND,
                    problemDetail,
                    ExceptionErrorCode.EXCEPTION_NOT_FOUND,
                    new Object[] {FieldConstant.ROLE, roleName}));
  }
}
