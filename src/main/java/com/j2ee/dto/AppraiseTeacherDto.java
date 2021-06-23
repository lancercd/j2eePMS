package com.j2ee.dto;


import com.j2ee.db.domain.StuTeaCh;
import com.j2ee.db.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppraiseTeacherDto {

    Integer id;

    Teacher teacher;

    StuTeaCh stuTeaCh;

    String suggestion;

    Integer score;

    Byte isAccept;

    Boolean isDel;

    LocalDateTime addTime;

}
