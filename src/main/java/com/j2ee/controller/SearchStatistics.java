package com.j2ee.controller;

import com.j2ee.db.domain.AdviserInfo;
import com.j2ee.db.domain.Semester;
import com.j2ee.db.service.AdviserInfoService;
import com.j2ee.db.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/queryStatistics")
public class SearchStatistics {

    @Autowired
    private AdviserInfoService adviserInfoService;

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/queryStatisticsByType")
    public String queryStatisticsByType(){
        return "/searchStatistics";
    }


    @GetMapping("/showSemesterStatistics")
    public Object showSemester(Model model){

        List<Semester> semesters = semesterService.queryAll();
        for (Semester semester : semesters) {
            System.out.println(semester.getName());
        }
        if(semesters == null){
            return "redirect:teachingSecretary";
        }
        model.addAttribute("semesters",semesters);
        return "/searchStatistics";
    }
}
