package com.fita.project.services.impl;

import com.fita.project.dao.DepartmentRepository;
import com.fita.project.dao.LecturerRepository;
import com.fita.project.dao.StudentRepository;
import com.fita.project.dao.UserRepository;
import com.fita.project.dto.LecturerDTO;
import com.fita.project.dto.StudentDTO;
import com.fita.project.dto.UserDTO;
import com.fita.project.repository.entity.Department;
import com.fita.project.repository.entity.Lecturer;
import com.fita.project.repository.entity.Student;
import com.fita.project.repository.entity.User;
import com.fita.project.services.*;
import com.fita.project.ulti.Constants;
import com.google.api.services.drive.model.File;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Value("${user.folder.name}")
    private String folderName;

    @Autowired
    private GooglerService googlerService;

    //====================NG?????I D??NG====================

    /**
     * L???y t???t c??? c??c ng?????i d??ng trong c?? s??? d??? li???u
     *
     * @return List<UserDTO>
     */
    @Override
    public List<UserDTO> getUsers() {
        List<User> users =  userRepository.findAll();

        return getUserDTOS(users);
    }

    private List<UserDTO> getUserDTOS(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        // Convert user (Entity) -> userDTO (DTO)
        for (User user : users) {
            UserDTO userDTOTerm = getUserDTO(user);
            usersDTO.add(userDTOTerm);
        }
        return usersDTO;
    }

    /**
     * L???y ng?????i d??ng trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     * @return UserDTO
     */
    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id).orElse(new User());

        return getUserDTO(user);
    }

    private UserDTO getUserDTO(User user) {
        UserDTO userDTO  = modelMapper.map(user, UserDTO.class);
        userDTO.setRoleName(roleService.getRoleById(userDTO.getRoleId()).getRoleName());
        return userDTO;
    }

    /**
     * L???y ng?????i d??ng trong c?? s??? d??? li???u d???a theo username
     *
     * @param username
     * @return UserDTO
     */
    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        //Convert user (Entity) -> userDTO (DTO)

        return getUserDTO(user);
    }

    /**
     * Th??m 1 ng?????i d??ng v??o c?? s??? d??? li???u
     *
     * @param userDTO
     */
    @Override
    public void addUser(UserDTO userDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userDTO.setCreatedDate(dateFormat.format(new Date()));
        userDTO.setPassword(bCryptEncoder.encode(userDTO.getPassword()));

        userRepository.save(modelMapper.map(userDTO, User.class));
    }

    /**
     * S???a ng?????i d??ng trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     * @param userDTO
     */
    @Override
    public void editUser(int id, UserDTO userDTO) {
        // L???y ng?????i d??ng c???n s???a
        User userToUpdate = getOne(id);

        // C???p nh???t d??? li???u m???i
        convert(userDTO, userToUpdate);

        // L??u l???i v??o c?? s??? d??? li???u
        userRepository.save(userToUpdate);
    }

    /**
     * Xo?? ng?????i d??ng trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        String username = getUserById(id).getUsername();

        lecturerRepository.deleteByLecturerCode(username);
        studentRepository.deleteByStudentCode(username);

        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateAvatar(MultipartFile file , int id) throws IOException {
        String url = null;
        File fileIMG = googlerService.updateFile(Constants.ID_FOLDER_PARENT_IMG_GOOGLE_DRIVER,file);
        User user = userRepository.getOne(id);
        if (fileIMG != null){
            user.setUserAvatarUrl(fileIMG.getId());
        }
        return modelMapper.map(this.userRepository.save(user), UserDTO.class);
    }


    //====================GI???NG VI??N====================

    /**
     * L???y t???t c??? c??c gi???ng vi??n trong c?? s??? d??? li???u
     *
     * @return List<LecturerDTO>
     */
    @Override
    public List<LecturerDTO> getLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll();

        return getLecturerDTOS(lecturers);
    }

    private List<LecturerDTO> getLecturerDTOS(List<Lecturer> lecturers) {
        List<LecturerDTO> lecturersDTO = new ArrayList<>();

        //Convert lecturer (Entity) -> lecturerDTO (DTO)
        for (Lecturer lecturer : lecturers) {
            UserDTO userLecture = new UserDTO();
            userLecture = getUserByUsername(lecturer.getLecturerCode());

            LecturerDTO lecturerDTO = new LecturerDTO();
            lecturerDTO = convertLecturer(userLecture, lecturer);
            lecturersDTO.add(lecturerDTO);
        }
        return lecturersDTO;
    }

    /**
     * L???y gi???ng vi??n trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     * @return LecturerDTO
     */
    @Override
    public LecturerDTO getLecturerById(int id) {
        UserDTO userDTO = getUserById(id);

        return getLecturerByLecturerCode(userDTO.getUsername());
    }

    /**
     * L???y gi???ng vi??n trong c?? s??? d??? li???u d???a theo m?? gi???ng vi??n
     *
     * @param lecturerCode
     * @return LecturerDTO
     */
    public LecturerDTO getLecturerByLecturerCode(String lecturerCode) {
        Lecturer lecturer = lecturerRepository.findByLecturerCode(lecturerCode);
        UserDTO userDTO = getUserByUsername(lecturer.getLecturerCode());

        //Convert lecturer (Entity) -> lecturerDTO (DTO)

        return convertLecturer(userDTO, lecturer);
    }

    private LecturerDTO convertLecturer(UserDTO userDTO, Lecturer lecturer) {
        LecturerDTO lecturerDTO = modelMapper.map(userDTO, LecturerDTO.class);

        if (lecturer.getDepartmentCode() != null) {
            lecturerDTO.setDepartmentCode(lecturer.getDepartmentCode());
            lecturerDTO.setDepartmentName(departmentService.getDepartmentByDepartmentCode(lecturer.getDepartmentCode()).getDepartmentName());
        }

        return lecturerDTO;
    }

    /**
     * Th??m 1 gi???ng vi??n v??o c?? s??? d??? li???u
     *
     * @param lecturerDTO
     * @return true n???u th??m th??nh c??ng, ng?????c l???i tr??? v??? false
     */
    @Override
    public void addLecturer(LecturerDTO lecturerDTO) {
        lecturerDTO.setPassword(bCryptEncoder.encode(lecturerDTO.getPassword()));
        User user = getUser(lecturerDTO);
        Lecturer lecturer = getLecturer(lecturerDTO);

        userRepository.save(user);
        lecturerRepository.save(lecturer);
    }

    private User getUser(LecturerDTO lecturerDTO) {
        return modelMapper.map(lecturerDTO, User.class);
    }

    private Lecturer getLecturer(LecturerDTO lecturerDTO) {
        Lecturer lecturer = modelMapper.map(lecturerDTO, Lecturer.class);
        lecturer.setLecturerCode(lecturerDTO.getUsername());
        return lecturer;
    }

    /**
     * S???a gi???ng vi??n trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     * @param lecturerDTO
     */
    @Override
    public void editLecturer(int id, LecturerDTO lecturerDTO) {
        // L???y gi???ng vi??n c???n s???a
        LecturerDTO lecturerToUpdateDTO = getLecturerById(id);
        User userToUpdate = userRepository.getOne(lecturerToUpdateDTO.getId());
        Lecturer lecturerToUpdate = lecturerRepository.findByLecturerCode(userToUpdate.getUsername());

        // C???p nh???t d??? li???u m???i
        convert(lecturerDTO, userToUpdate);

        lecturerToUpdate.setLecturerCode(lecturerDTO.getUsername());
        lecturerToUpdate.setDepartmentCode(lecturerDTO.getDepartmentCode());

        // L??u l???i v??o c?? s??? d??? li???u
        lecturerRepository.save(lecturerToUpdate);
        userRepository.save(userToUpdate);
    }

    /**
     * Xo?? gi???ng vi??n trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     */
    @Override
    public void deleteLecturer(int id) {
        LecturerDTO lecturerToDeleteDTO = getLecturerById(id);

        lecturerRepository.deleteByLecturerCode(lecturerToDeleteDTO.getUsername());
        userRepository.deleteById(id);
    }


    //====================SINH VI??N====================

    /**
     * L???y t???t c??? c??c sinh vi??n trong c?? s??? d??? li???u
     *
     * @return List<StudentDTO>
     */
    @Override
    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();

        return getStudentDTOS(students);
    }

    @Override
    public List<StudentDTO> getStudentByLecture() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user  = authentication.getPrincipal();

        if (user instanceof org.springframework.security.core.userdetails.User){

            List<User> users = userRepository.findAllByCreatedBy(((org.springframework.security.core.userdetails.User) user).getUsername());
            List<Student> students =  users.stream().map(student -> studentRepository.findByStudentCode(student.getUsername())).collect(Collectors.toList());
            return getStudentDTOS(students);
        }
        return new ArrayList<>();
    }

    private String getUserNameContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user  = authentication.getPrincipal();
        if (user instanceof org.springframework.security.core.userdetails.User){
            return ((org.springframework.security.core.userdetails.User) user).getUsername();
        }
        return "";
    }

    private List<StudentDTO> getStudentDTOS(List<Student> students) {
        List<StudentDTO> studentsDTO = new ArrayList<>();

        //Convert student (Entity) -> studentDTO (DTO)
        for (Student student : students) {
            try {
                UserDTO userMapper = new UserDTO();
                userMapper = getUserByUsername(student.getStudentCode());
                StudentDTO studentMapper = new StudentDTO();
                studentMapper = modelMapper.map(userMapper, StudentDTO.class);
                studentMapper.setClassCode(student.getClassCode());

                studentsDTO.add(studentMapper);
            }catch (Exception e){
                System.out.println(student);
            }
        }
        return studentsDTO;
    }

    /**
     * L???y sinh vi??n trong c?? s??? d??? li???u d???a theo id
     *
     * @return StudentDTO
     */
    @Override
    public StudentDTO getStudentById(int id) {
        UserDTO userDTO = getUserById(id);

        return getStudentByStudentCode(userDTO.getUsername());
    }

    /**
     * L???y sinh vi??n trong c?? s??? d??? li???u d???a theo m?? sinh vi??n
     *
     * @param studentCode
     * @return studentDTO
     */
    @Override
    public StudentDTO getStudentByStudentCode(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode);
        UserDTO userDTO = getUserByUsername(student.getStudentCode());

        // Convert student (Entity) -> studentDTO (DTO)
        StudentDTO studentDTO = modelMapper.map(userDTO, StudentDTO.class);
        studentDTO.setClassCode(student.getClassCode());

        return studentDTO;
    }

    /**
     * Th??m 1 sinh vi??n v??o c?? s??? d??? li???u
     *
     * @param studentDTO
     */
    @Override
    public void addStudent(StudentDTO studentDTO) {
        studentDTO.setPassword(bCryptEncoder.encode(studentDTO.getPassword()));
        User user = getUser(studentDTO);
        Student student = getStudent(studentDTO);

        userRepository.save(user);
        studentRepository.save(student);
    }

    private User getUser(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, User.class);
    }

    private Student getStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setStudentCode(studentDTO.getUsername());
        return student;
    }

    /**
     * S???a sinh vi??n trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     * @param studentDTO
     */
    @Override
    public void editStudent(int id, StudentDTO studentDTO) {
        // L???y sinh vi??n c???n s???a
        StudentDTO studentToUpdateDTO = getStudentById(id);
        User userToUpdate = getOne(studentToUpdateDTO.getId());
        Student studentToUpdate = studentRepository.findByStudentCode(userToUpdate.getUsername());

        // C???p nh???t d??? li???u m???i
        convert(studentDTO, userToUpdate);

        studentToUpdate.setStudentCode(studentDTO.getUsername());
        studentToUpdate.setClassCode(studentDTO.getClassCode());

        // L??u l???i v??o c?? s??? d??? li???u
        studentRepository.save(studentToUpdate);
        userRepository.save(userToUpdate);
    }

    private User getOne(Integer id) {
        return userRepository.getOne(id);
    }

    /**
     * Xo?? sinh vi??n trong c?? s??? d??? li???u d???a theo id
     *
     * @param id
     */
    @Override
    public void deleteStudent(int id) {
        StudentDTO studentToDeleteDTO = getStudentById(id);

        studentRepository.deleteByStudentCode(studentToDeleteDTO.getUsername());
        userRepository.deleteById(id);
    }

    private void convert(UserDTO userDTO, User userToUpdate) {
        userToUpdate.setUsername(userDTO.getUsername());
        if (userDTO.getPassword() != null) {
            userToUpdate.setPassword(bCryptEncoder.encode(userDTO.getPassword()));
        }
        userToUpdate.setFullName(userDTO.getFullName());
        userToUpdate.setBirthDate(userDTO.getBirthDate());
        if (userDTO.getGender() != null) {
            userToUpdate.setGender(userDTO.getGender());
        }
        userToUpdate.setEmailAddress(userDTO.getEmailAddress());
        userToUpdate.setPhoneNumber(userDTO.getPhoneNumber());
        userToUpdate.setRoleId(userDTO.getRoleId());
        if (userDTO.getUserAvatarUrl() != null) {
            userToUpdate.setUserAvatarUrl(userDTO.getUserAvatarUrl());
        }
        userToUpdate.setStatus(userDTO.getStatus());
    }

    /**
     * Get username + password by username
     *
     * @param username
     * @return username + password
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void createUserFile(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 8; index < worksheet.getPhysicalNumberOfRows(); index++) {
                XSSFRow row = worksheet.getRow(index);
                DataFormatter formatter = new DataFormatter();
                String birthDay = formatter.formatCellValue(row.getCell(4));

                if (formatter.formatCellValue(row.getCell(5)).equalsIgnoreCase("sinh vien")) {
                    this.addStudent(getStudentDTO(row, formatter, birthDay));
                }
                if (formatter.formatCellValue(row.getCell(5)).equalsIgnoreCase("giang vien")) {
                    this.addLecturer(getLecturerDTO(row, formatter, birthDay));
                }
        }
    }

    private StudentDTO getStudentDTO(XSSFRow row, DataFormatter formatter, String birthDay) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFullName(formatter.formatCellValue(row.getCell(1)));
        studentDTO.setUsername(formatter.formatCellValue(row.getCell(2)));
        studentDTO.setPassword(formatter.formatCellValue(row.getCell(3)));
        studentDTO.setBirthDate(birthDay);
        studentDTO.setStatus(1);
        studentDTO.setRoleId(3);
        studentDTO.setCreatedBy(getUserNameContext());
        studentDTO.setClassCode(formatter.formatCellValue(row.getCell(6)));
        studentDTO.setCreatedDate(LocalDateTime.now().toString());
        studentDTO.setEmailAddress(formatter.formatCellValue(row.getCell(7)));
        return studentDTO;
    }

    private LecturerDTO getLecturerDTO(XSSFRow row, DataFormatter formatter, String birthDay) {
        LecturerDTO lecturerDTO = new LecturerDTO();
        lecturerDTO.setFullName(formatter.formatCellValue(row.getCell(1)));
        lecturerDTO.setUsername(formatter.formatCellValue(row.getCell(2)));
        lecturerDTO.setPassword(formatter.formatCellValue(row.getCell(3)));
        lecturerDTO.setBirthDate(birthDay);
        lecturerDTO.setStatus(1);
        lecturerDTO.setRoleId(2);
        lecturerDTO.setEmailAddress(formatter.formatCellValue(row.getCell(7)));
        lecturerDTO.setCreatedBy(getUserNameContext());
        lecturerDTO.setCreatedDate(LocalDateTime.now().toString());
        Department department = departmentRepository.findDepartmentByDepartmentName(formatter.formatCellValue(row.getCell(7)));
        lecturerDTO.setDepartmentCode(department.getDepartmentCode());
        return lecturerDTO;
    }

}
