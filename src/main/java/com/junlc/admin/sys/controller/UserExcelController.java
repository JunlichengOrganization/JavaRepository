package com.junlc.admin.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junlc.admin.sys.domain.User;
import com.junlc.admin.sys.service.UserService;


import org.apache.poi.hssf.usermodel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;



@RestController
@RequestMapping(value = "/excel")
public class UserExcelController {

    @Autowired
    private UserService userService;

    //创建表头
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        //font.setBold(true);
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("显示名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);
    }

    //生成user表excel
    @RequestMapping(value = "/getUser")
    public String getUser(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook,sheet);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        List<User> rows = userService.list(queryWrapper);

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(User user:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getLoginname());
            HSSFCell cell = row.createCell(3);
            cell.setCellValue(user.getHiredate());
            cell.setCellStyle(style);
            rowNum++;
        }

        String fileName = "导出excel例子.xls";

        //生成excel文件
        //buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        return "download excel";
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }


//    public void exportAll() {
//        OutputStream out = null;
//        try {
//             获取数据
//            List<BaseTalentFlowAnalysisGridVo> talentFlows = this.talentFlowQuestionnaireService.listAnalysis();
//            List<BaseBasicSituationAnalysisGridVo> basicSituations = this.basicSituationService.listAnalysis();
//            List<BaseTalentInputAnalysisGridVo> talentInputs = this.talentInputService.listAnalysis();
//            List<BaseTalentDemandAnalysisGridVo> talentDemands = this.talentDemandService.listAnalysis();
//            List<BaseHighLevelTalentsRosterAnalysisGridVo> talentRosters = this.rosterService.listAnalysis();
//            Map<String, Object> results = new HashMap<>();
//            results.put("talentFlows", talentFlows);
//            results.put("basicSituations", basicSituations);
//            results.put("talentInputs", talentInputs);
//            results.put("talentDemands", talentDemands);
//            results.put("talentRosters", talentRosters);
//
//            // 拿到模板文件
//            String path = ServletActionContext.getServletContext().getRealPath("/");
//            String filePath = path + "\\resources\\temp\\人才统计报表模板.xls";
//            FileInputStream tps = new FileInputStream(new File(filePath));
//            final HSSFWorkbook tpWorkbook = new HSSFWorkbook(tps);
//            out = response.getOutputStream();
//            response.reset();
//            response.setHeader("content-disposition",
//                    "attachment;filename=" + new String(("人才统计报表").getBytes("gb2312"), "ISO8859-1") + ".xls");
//            response.setContentType("APPLICATION/msexcel");
//            // 新建一个Excel的工作空间
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            // 把模板复制到新建的Excel
//            workbook = tpWorkbook;
//            // 填充数据
//            this.excelService.addData(workbook, results);
//            // 输出Excel内容，生成Excel文件
//            workbook.write(out);
//        } catch (final IOException e) {
//            LOGGER.error(e);
//        } catch (final IllegalArgumentException e) {
//            LOGGER.error(e);
//        } catch (final Exception e) {
//            LOGGER.error(e);
//        } finally {
//            try {
//                // 最后记得关闭输出流
//                response.flushBuffer();
//                if (out != null) {
//                    out.flush();
//                    out.close();
//                }
//            } catch (final IOException e) {
//                LOGGER.error(e);
//            }
//        }
//    }


    @RequestMapping(value="/excelExport")
    public void excel2007Export(HttpServletResponse response, HttpServletRequest request) throws Exception{

        Resource resource = new ClassPathResource("templates/excelTemplate/student.xls");
        boolean isFile = resource.isFile();
        if(!isFile){     //如果不存在返回
            return;
        }

        HSSFWorkbook workbook = null;

        try {
            String excel = resource.getFile().getPath();     //获取文件路径
            File file = new File(excel);
//            String excel = basePath + "/excel/user_model.xls";
          //  File fi = new File(excel);
          //  POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
            // 读取excel模板
            workbook = new HSSFWorkbook(new FileInputStream(file));
            // 读取了模板内所有sheet内容
            HSSFSheet sheet0 = workbook.getSheetAt(0);
            HSSFRow row = sheet0.createRow(2);
            HSSFCell cell0 = row.createCell(0);
            HSSFCell cell1 = row.createCell(1);
            HSSFCell cell2 = row.createCell(2);
            cell0.setCellValue("guo");
            cell1.setCellValue("bin");
            cell2.setCellValue("hui");
            System.out.println(cell0);
            //这里作为演示，造几个演示数据，模拟数据库里查数据
//            List <Student> list =  new ArrayList<Student>();
//            Student st1 = new Student();
//            Student st2 = new Student();
//            st1.setName("张三");
//            st1.setScore("87");
//            st1.setClass("一班");
//            st2.setName("张四");
//            st2.setScore("57");
//            st2.setClass("二班");
//            list.add(st1);
//            list.add(st2);
            for(int i = 1;i<5;i++){
               // HSSFRow rowT = sheet0.getRow(i+3);//从第三行开始填充数据  getRow不行！！
                HSSFRow rowT = sheet0.createRow(2+i);
                rowT.createCell(0).setCellValue("45645~~"+i);
                rowT.createCell(1).setCellValue("asdfs~~"+i);
                rowT.createCell(2).setCellValue("345435~~"+i);
            }
            String fileName = "moban.xlsx";
//            downLoadExcel(fileName, response, workbook);
            buildExcelDocument(fileName,workbook,response);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return "download excel";
    }


    //浏览器下载excel
    protected void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

//    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
//            workbook.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void exportPermMatrix(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("xxx信息表");
//        //此处添加数据
//        HSSFRow headerRow1 = sheet.createRow(0);
//        headerRow1.createCell(0).setCellValue("编号");
//        headerRow1.createCell(1).setCellValue("内容");
//        HSSFRow headerRow2 = sheet.createRow(1);
//        headerRow2.createCell(0).setCellValue("01");
//        headerRow2.createCell(1).setCellValue("测试文本");
//        //清空response
//        response.reset();
//        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition",
//                "attachment; filename=" + new String("excel模板".getBytes(), "iso8859-1") + ".xls");
//        OutputStream os = new BufferedOutputStream(response.getOutputStream());
//        workbook.write(os);
//        os.flush();
//        os.close();
//        workbook.close();
//
//    }
}
