package com.bilsora.usermanager.service.serviceImpl;

import com.bilsora.usermanager.repository.RoleRepository;
import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

}
