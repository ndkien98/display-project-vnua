package com.fita.project.services.impl;

import com.fita.project.dao.DepartmentRepository;
import com.fita.project.dao.ProjectMemberRepository;
import com.fita.project.dao.ProjectRepository;
import com.fita.project.dto.*;
import com.fita.project.repository.entity.Department;
import com.fita.project.repository.entity.Project;
import com.fita.project.repository.entity.ProjectMember;
import com.fita.project.services.*;
import com.fita.project.ulti.Constants;
import com.google.api.services.drive.model.File;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private YearSemesterService yearSemesterService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private GooglerService googlerService;

    @Value("${project.folder.name}")
    private String folderName;

    /**
     * Lấy tất cả các đồ án trong cơ sở dữ liệu
     *
     * @return List<ProjectDTO>
     */
    @Override
    public List<ProjectDTO> getProjects() {
        List<Project> list = projectRepository.findAllByStatus(1);
        List<ProjectDTO> projectsDTO = new ArrayList<>();

        // Convert project (Entity) -> projectDTO (DTO)
        for (Project project : list) {
            ProjectDTO projectDTO2 = new ProjectDTO();
            projectDTO2 = convert(project);
            projectsDTO.add(projectDTO2);
        }

        return projectsDTO;
    }

    @Override
    public List<ProjectDTO> findByKey(String key,String nameCategory) {
        List<Project> list =projectRepository.findByKey(key,nameCategory);
        return getProjectDTOS(list);
    }

    private List<ProjectDTO> getProjectDTOS(List<Project> list) {
        List<ProjectDTO> list1 = new ArrayList<>();
        ProjectDTO projectDTO = new ProjectDTO();
        if (list != null){
            for (Project project : list) {
                projectDTO = convert(project);
                list1.add(projectDTO);
            }
        }

        return list1;
    }

    @Override
    public List<ProjectDTO> findByName(String key) {
        List<Project> list =projectRepository.findByNameOrNameCategory(key);
        return getProjectDTOS(list);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo trạng thái
     *
     * @param status
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjectsByStatus(int status) {
        List<Project> list = projectRepository.findByStatus(status);
        List<ProjectDTO> projectsDTO = new ArrayList<>();

        //Convert project (Entity) -> project (DTO)
        for (Project project : list) {
            ProjectDTO projectDTO1 ;
            projectDTO1 = convert(project);
            projectsDTO.add(projectDTO1);
        }

        return projectsDTO;
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @return ProjectDTO
     */
    @Override
    public ProjectDTO getProjectById(int id) {
        Project project = projectRepository.findById(id).orElse(new Project());

        // Convert project (Entity) -> projectDTO (DTO)
        return convert(project);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo mã đồ án
     *
     * @param projectCode
     * @return ProjectDTO
     */
    @Override
    public ProjectDTO getProjectByProjectCode(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);

        // Convert project (Entity) -> project (DTO)
        return convert(project);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo mã sinh viên
     *
     * @param studentCode
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjectsByStudentCode(String studentCode) {
        List<Project> projects = projectRepository.findByStudentCode(studentCode);

        return this.getProjectDTOS(projects);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo mã thể loại
     *
     * @param categoryCode
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjectsByCategoryCode(String categoryCode) {
        List<Project> projects = projectRepository.findByCategoryCode(categoryCode);

        return getProjectDTOS(projects);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo mã giảng viên
     *
     * @param lecturerCode
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjectsByLecturerCode(String lecturerCode) {
        List<Project> projects = projectRepository.findByLectureCode(lecturerCode);
        return getProjectDTOS(projects);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo lớp học phần id
     *
     * @param courseId
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjectsByCourseId(int courseId) {
        List<Project> projects = projectRepository.findByCourseId(courseId);

        return getProjectDTOS(projects);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo năm học - học kỳ id
     *
     * @param yearSemesterId
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjectsByYearSemesterId(int yearSemesterId) {
        List<Project> projects = projectRepository.findByYearSemesterId(yearSemesterId);
        return getProjectDTOS(projects);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo mã giảng viên + trạng thái
     *
     * @param lecturerCode
     * @param status
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjects(String lecturerCode, int status) {
        List<Project> projects = projectRepository.findByLectureCodeAndStatus(lecturerCode, status);
        return getProjectDTOS(projects);
    }

    /**
     * Lấy đồ án trong cơ sở dữ liệu dựa theo mã giảng viên + trạng thái + năm học - học kỳ id
     *
     * @param lecturerCode
     * @param status
     * @param yearSemesterId
     * @return ProjectDTO
     */
    @Override
    public List<ProjectDTO> getProjects(String lecturerCode, int status, int yearSemesterId) {
        List<Project> projects = projectRepository.findByLectureCodeAndStatusAndYearSemesterId(lecturerCode, status, yearSemesterId);

        return getProjectDTOS(projects);
    }

    /**
     * Lấy số lượng đồ án trong cơ sở dữ liệu dựa theo mã thể loại
     *
     * @param categoryCode
     * @return int
     */
    @Override
    public int getQuantityByCategoryCode(String categoryCode) {
        return projectRepository.countByCategoryCode(categoryCode);
    }

    /**
     * Lấy số lượng đồ án trong cơ sở dữ liệu dựa theo mã bộ môn
     *
     * @param departmentCode
     * @return int
     */
    @Override
    public int getQuantityByDepartmentCode(String departmentCode) {
        return projectRepository.countByDepartmentCode(departmentCode);
    }

    /**
     * Lấy số lượng đồ án trong cơ sở dữ liệu dựa theo mã bộ môn
     *
     * @param startYear
     * @param endYear
     * @return int
     */
    @Override
    public List<YearDTO> getQuantityByYears(int startYear, int endYear) {
        List<YearDTO> yearsDTO = new ArrayList<>();
        while (startYear <= endYear) {
            int quantity = projectRepository.countByYear(startYear);
            yearsDTO.add(new YearDTO(startYear++, quantity));
        }

        return yearsDTO;
    }

    /**
     * Thêm 1 đồ án vào cơ sở dữ liệu
     *
     * @param projectDTO
     */
    @Override
    public ProjectDTO addProject(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        project.setCreatedDate(date);
        project.setLastModifiedDate(date);
        Project projectResponse = projectRepository.save(project);
        saveProjectMember(projectDTO);
        return convert(projectResponse);
    }

    /**
     * Sửa đồ án trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @param projectDTO
     */
    @Override
    public void editProject(int id, ProjectDTO projectDTO) {
        // Lấy đồ án cần sửa
        Project projectToUpdate = projectRepository.getOne(id);

        // Cập nhật dữ liệu mới
        projectToUpdate.setProjectCode(projectDTO.getProjectCode());
        projectToUpdate.setProjectName(projectDTO.getProjectName());
        projectToUpdate.setProjectAvatarUrl(projectDTO.getProjectAvatarUrl());
        projectToUpdate.setShortDescription(projectDTO.getShortDescription());
        projectToUpdate.setDetailedDescription(projectDTO.getDetailedDescription());
        projectToUpdate.setDemoLink(projectDTO.getDemoLink());
        projectToUpdate.setCategoryCode(projectDTO.getCategoryCode());
        projectToUpdate.setStudentCode(projectDTO.getStudentCode());
        projectToUpdate.setCourseId(projectDTO.getCourseId());
        projectToUpdate.setStatus(projectDTO.getStatus());
        //projectToUpdate.setCreatedDate(projectDTO.getCreatedDate());
        //projectToUpdate.setCreatedBy(projectDTO.getCreatedBy());
        projectToUpdate.setLastModifiedBy(projectDTO.getLastModifiedBy());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        projectToUpdate.setLastModifiedDate(dateFormat.format(new Date()));

        // Cập nhật thành viên project
        projectMemberRepository.deleteByProjectCode(projectDTO.getProjectCode());
        saveProjectMember(projectDTO);

        // Lưu lại vào cơ sở dữ liệu
        projectRepository.save(projectToUpdate);
    }

    /**
     * Xoá đồ án trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     */
    @Override
    public void deleteProject(int id) {
        projectMemberRepository.deleteByProjectCode(getProjectById(id).getProjectCode());
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectDTO uploadAvatar(MultipartFile file, int id) throws IOException {

        String url = null;
//        url = this.imageService.uploadImg(file,folderName).getImageLink();
        File fileImg = googlerService.updateFile(Constants.ID_FOLDER_PARENT_IMG_GOOGLE_DRIVER,file);
        Project project = projectRepository.getOne(id);
        if (fileImg != null){
            project.setProjectAvatarUrl(fileImg.getId());
        }
        this.projectRepository.save(project);
        return convert(project);
    }

    private ProjectDTO convert(Project project) {

        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);

        if (projectDTO.getCategoryCode() != null) {
            CategoryDTO categoryDTO = categoryService.getCategoryByCategoryCode(projectDTO.getCategoryCode());
            projectDTO.setCategoryName(categoryDTO.getCategoryName());
        }

        if (projectDTO.getStudentCode() != null) {
            StudentDTO studentDTO = userService.getStudentByStudentCode(projectDTO.getStudentCode());
            projectDTO.setStudentName(studentDTO.getFullName());
            projectDTO.setStudentClass(studentDTO.getClassCode());
        }

        if (projectDTO.getCourseId() != null) {
            CourseDTO courseDTO = courseService.getCourseById(projectDTO.getCourseId());
            projectDTO.setSubjectCode(courseDTO.getSubjectCode());
            projectDTO.setSubjectName(courseDTO.getSubjectName());
            projectDTO.setSubjectGroup(courseDTO.getSubjectGroup());
            projectDTO.setCourseClass(courseDTO.getClassCode());

            if (courseDTO.getYearSemesterId() != null) {
                YearSemesterDTO yearSemesterDTO = yearSemesterService.getYearSemesterById(courseDTO.getYearSemesterId());
                projectDTO.setYearSemesterId(yearSemesterDTO.getId());
                projectDTO.setYear(yearSemesterDTO.getYear());
                projectDTO.setSemester(yearSemesterDTO.getSemester());
            }

            if (courseDTO.getLecturerCode() != null) {
                LecturerDTO lecturerDTO = userService.getLecturerByLecturerCode(courseDTO.getLecturerCode());
                Department department = departmentRepository.findDepartmentByDepartmentCode(lecturerDTO.getDepartmentCode());
                projectDTO.setNameDepartment(department.getDepartmentName());
                projectDTO.setLecturerCode(lecturerDTO.getUsername());
                projectDTO.setLecturerName(lecturerDTO.getFullName());
            }
        }
        projectDTO.setDetailedDescription(project.getDetailedDescription());
        projectDTO.setProjectMembers(getProjectMembers(projectDTO.getProjectCode()));
        if (projectDTO.getYear() != null){
            int term = projectDTO.getYear() + 1 ;
            projectDTO.setYearSemester(" " + projectDTO.getSemester()+ " / " +  projectDTO.getYear() + " - " + term);
        }else projectDTO.setYearSemester("  ");

        return projectDTO;
    }

    private void saveProjectMember(ProjectDTO projectDTO) {
        List<ProjectMemberDTO> projectMembersDTO = projectDTO.getProjectMembers();
        for (ProjectMemberDTO projectMemberDTO : projectMembersDTO) {
            ProjectMember projectMember = new ProjectMember();
            projectMember.setStudentCode(projectMemberDTO.getStudentCode());
            projectMember.setFullName(projectMemberDTO.getFullName());
            projectMember.setClassCode(projectMemberDTO.getClassCode());
            projectMember.setProjectCode(projectDTO.getProjectCode());

            projectMemberRepository.save(projectMember);
        }
    }

    private List<ProjectMemberDTO> getProjectMembers(String projectCode) {
        List<ProjectMember> projectMembers = projectMemberRepository.findByProjectCode(projectCode);
        List<ProjectMemberDTO> projectMembersDTO = new ArrayList<>();

        // Convert projectMember (Entity) -> projectMemberDTO (DTO)
        for (ProjectMember projectMember : projectMembers) {
            projectMembersDTO.add(modelMapper.map(projectMember, ProjectMemberDTO.class));
        }

        return projectMembersDTO;
    }
}
