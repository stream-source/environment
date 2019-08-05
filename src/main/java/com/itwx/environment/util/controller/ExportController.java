package com.itwx.environment.util.controller;

import com.itwx.environment.result.Result;
import com.itwx.environment.util.ExportExcelUtil;
import com.itwx.environment.util.model.AllConfigState;
import com.itwx.environment.util.model.ExportSampleDto;
import com.itwx.environment.util.model.Student;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:wx
 * @Date:2019/7/17 17:31
 */
@RestController
public class ExportController {

    @GetMapping("/export")
    public Result getExport(HttpServletResponse response){
        response.reset(); // 清除buffer缓存
        // 指定下载的文件名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(date);
        response.setHeader("Content-Disposition", "attachment;filename=File" + time + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook;

        ExportExcelUtil<Student> util = new ExportExcelUtil<>();
        // 准备数据
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Student(111, "张三asdf" + i, "男"));
        }
        String[] columnNames = {"ID","weuyong", "姓名", "性别","ceshi"};
        workbook = util.exportExcel2007("用户导出", columnNames, list);

        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/lab/exportExcel")
    public Result exportExcel(String itemId, String SampleId,HttpServletResponse response){
        response.reset(); // 清除buffer缓存
        // 指定下载的文件名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(date);
        response.setHeader("Content-Disposition", "attachment;filename=File" + time + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook;
        List<ExportSampleDto> sampleDtoList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            ExportSampleDto sampleDto = new ExportSampleDto();
            sampleDto.setAge(i);
            sampleDto.setItemName("item_"+i);
            sampleDto.setPatientName("name_"+i);
            sampleDto.setApplyId("apply_"+i);
            sampleDtoList.add(sampleDto);
        }
        ExportExcelUtil<ExportSampleDto> util = new ExportExcelUtil<>();
        workbook = util.exportExcel2007(AllConfigState.SHEET_NAME, AllConfigState.EXCEL_TITLE, sampleDtoList);
        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

