package com.fita.project.services.impl;

import com.fita.project.dao.FunctionRepository;
import com.fita.project.dao.RoleFunctionRepository;
import com.fita.project.dao.RoleRepository;
import com.fita.project.dto.FunctionDTO;
import com.fita.project.dto.RoleDTO;
import com.fita.project.repository.entity.Function;
import com.fita.project.repository.entity.Role;
import com.fita.project.repository.entity.RoleFunction;
import com.fita.project.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private RoleFunctionRepository roleFunctionRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Lấy tất cả vai trò trong cơ sở dữ liệu
     *
     * @return List<RoleDTO>
     */
    @Override
    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();

        return getRoleDTOS(roles);
    }

    private List<RoleDTO> getRoleDTOS(List<Role> roles) {
        List<RoleDTO> rolesDTO = new ArrayList<>();

        // Convert role (Entity) -> roleDTO (DTO)
        for (Role role : roles) {
            rolesDTO.add(modelMapper.map(role, RoleDTO.class));
        }
        return rolesDTO;
    }

    /**
     * Lấy vai trò trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @return roleDTO
     */
    @Override
    public RoleDTO getRoleById(int id) {
        Role role = roleRepository.findById(id).orElse(new Role());

        return getRoleDTO(role);
    }

    private RoleDTO getRoleDTO(Role role) {
        //Convert role (Entity) -> roleDTO (DTO)
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);
        List<RoleFunction> roleFunctions = roleFunctionRepository.findByRoleId(roleDTO.getId());
        List<FunctionDTO> functionsDTO = new ArrayList<>();

        for (RoleFunction roleFunction : roleFunctions) {
            FunctionDTO functionDTO = getFunctionById(roleFunction.getFunctionId());
            functionDTO.setStatus(roleFunction.getStatus());

            functionsDTO.add(functionDTO);
        }
        roleDTO.setFunctionsDTO(functionsDTO);
        return roleDTO;
    }

    /**
     * Lấy vai trò trong cơ sở dữ liệu dựa theo tên quyền
     *
     * @param roleName
     * @return roleDTO
     */
    @Override
    public RoleDTO getRoleByRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        return getRoleDTO(role);
    }

    /**
     * Lấy tất cả chức năng trong cơ sở dữ liệu
     *
     * @return List<FunctionDTO>
     */
    @Override
    public List<FunctionDTO> getFunctions() {
        List<Function> functions = functionRepository.findAll();

        return getFunctionDTOS(functions);
    }

    private List<FunctionDTO> getFunctionDTOS(List<Function> functions) {
        List<FunctionDTO>functionsDTO = new ArrayList<>();

        // Convert function (Entity) -> functionDTO (DTO)
        for (Function function : functions) {
            functionsDTO.add(modelMapper.map(function, FunctionDTO.class));
        }
        return functionsDTO;
    }

    /**
     * Lấy chức năng trong cơ sở dữ liệu dựa theo id
     *
     * @param functionId
     * @return List<FunctionDTO>
     */
    @Override
    public FunctionDTO getFunctionById(int functionId) {
        Function function = functionRepository.findById(functionId).get();

        // Convert function (Entity) -> functionDTO (DTO)

        return getFunctionDTO(function);
    }

    private FunctionDTO getFunctionDTO(Function function) {
        FunctionDTO functionDTO = modelMapper.map(function, FunctionDTO.class);
        return functionDTO;
    }

    /**
     * Thêm 1 vai trò vào cơ sở dữ liệu
     *
     * @param roleDTO
     */
    @Override
    public void addRole(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        roleRepository.save(role);

        roleFunctionRepository.saveAll(getListRoleFunction(roleDTO, role));
    }

    private List<RoleFunction> getListRoleFunction(RoleDTO roleDTO, Role role) {
        List<FunctionDTO> functionsDTO = roleDTO.getFunctionsDTO();
        List<RoleFunction> roleFunctions = new ArrayList<>();
        for (FunctionDTO functionDTO : functionsDTO) {
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setRoleId(getRoleByRoleName(role.getRoleName()).getId());
            roleFunction.setFunctionId(functionDTO.getId());
            roleFunction.setStatus(functionDTO.getStatus());
            roleFunctions.add(roleFunction);

        }

        return roleFunctions;
    }

    /**
     * Sửa vai trò trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @param roleDTO
     */
    @Override
    public void editRole(int id, RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        role.setId(id);
        roleRepository.save(role);

        List<FunctionDTO> functionsDTO = roleDTO.getFunctionsDTO();

        for (FunctionDTO functionDTO : functionsDTO) {
            roleFunctionRepository.updateStatus(id, functionDTO.getId(), functionDTO.getStatus());
        }
    }

    /**
     * Xoá vai trò trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     */
    @Override
    public void deleteRole(int id) {
        roleFunctionRepository.deleteByRoleId(id);
        roleRepository.deleteById(id);
    }
}
