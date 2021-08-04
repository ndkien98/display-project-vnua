package com.fita.project.services.impl;

import com.fita.project.dao.YearSemesterRepository;
import com.fita.project.dto.YearSemesterDTO;
import com.fita.project.repository.entity.YearSemester;
import com.fita.project.services.YearSemesterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class YearSemesterServiceImpl implements YearSemesterService {
    @Autowired
    private YearSemesterRepository yearSemesterRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Lấy tất cả các năm học - học kỳ trong cơ sở dữ liệu
     *
     * @return List<YearSemesterDTO>
     */
    @Override
    public List<YearSemesterDTO> getYearsSemesters() {
        List<YearSemester> yearsSemesters = yearSemesterRepository.findAll();

        return getYearSemesterDTOS(yearsSemesters);
    }

    private List<YearSemesterDTO> getYearSemesterDTOS(List<YearSemester> yearsSemesters) {
        List<YearSemesterDTO>yearsSemestersDTO = new ArrayList<>();

        //Convert yearSemester (Entity) -> yearSemesterDTO (DTO)
        for (YearSemester yearSemester : yearsSemesters) {
            yearsSemestersDTO.add(modelMapper.map(yearSemester, YearSemesterDTO.class));
        }
        return yearsSemestersDTO;
    }

    /**
     * Lấy năm học - học kỳ trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @return YearSemesterDTO
     */
    @Override
    public YearSemesterDTO getYearSemesterById(int id) {
        YearSemester yearSemester = yearSemesterRepository.findById(id).get();

        return getYearSemesterDTO(yearSemester);
    }

    private YearSemesterDTO getYearSemesterDTO(YearSemester yearSemester) {
        //Convert yearSemester (Entity) -> yearSemesterDTO (DTO)
        YearSemesterDTO yearSemesterDTO = modelMapper.map(yearSemester, YearSemesterDTO.class);
        return yearSemesterDTO;
    }

    /**
     * Thêm 1 học kỳ - năm học vào cơ sở dữ liệu
     *
     * @param yearSemesterDTO
     */
    @Override
    public void addYearSemester(YearSemesterDTO yearSemesterDTO) {
        yearSemesterRepository.save(modelMapper.map(yearSemesterDTO, YearSemester.class));
    }

    /**
     * Sửa năm học - học kỳ trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @param yearSemesterDTO
     */
    @Override
    public void editYearSemester(int id, YearSemesterDTO yearSemesterDTO) {
        // Lấy năm học - học kỳ cần sửa
        YearSemester yearSemesterToUpdate = yearSemesterRepository.getOne(id);

        // Cập nhật dữ liệu mới
        yearSemesterToUpdate.setYear(yearSemesterDTO.getYear());
        yearSemesterToUpdate.setSemester(yearSemesterDTO.getSemester());
        yearSemesterToUpdate.setStartDate(yearSemesterDTO.getStartDate());
        yearSemesterToUpdate.setWeeksNumber(yearSemesterDTO.getWeeksNumber());

        // Lưu lại vào cơ sở dữ liệu
        yearSemesterRepository.save(yearSemesterToUpdate);
    }

    /**
     * Xoá năm học - học kỳ trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     */
    @Override
    public void deleteYearSemester(int id) {
        yearSemesterRepository.deleteById(id);
    }
}
