package com.xiaostudy.springboot_studentmanager.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaostudy.springboot_studentmanager.domain.Grade;
import com.xiaostudy.springboot_studentmanager.service.GradeService;
import com.xiaostudy.springboot_studentmanager.service.IndexService;
import com.xiaostudy.springboot_studentmanager.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/grade", produces = {"application/text;charset=UTF-8"})
public class GradeController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/gradelist")
    public String gradelist(HttpSession session, Model model) {
        if(indexService.isLogin(session)) {
            List<Grade> gradeList = gradeService.getGradeAll();
            //放在请求域中
            model.addAttribute("gradeList",gradeList);
            return "gradelist";
        }
        return "userlogin";
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjax", method = RequestMethod.GET)
    public void updateGradeAJAX(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String newGradeNumber = request.getParameter("newGradeNumber");
        String newGradeName = request.getParameter("newGradeName");
        String gradeId = request.getParameter("gradeId");

        System.out.println("newGradeNumber:" + newGradeNumber);
        System.out.println("newGradeName:" + newGradeName);
        System.out.println("gradeId:" + gradeId);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;//用于一个错误，其他就不进入，直接返回信息给前台
        try {
            jsonObject.put("status", "error");
            if(b) {
                if(indexService.isLogin(session) == false) {
                    jsonObject.put("message", "请先登录！");
                    b = false;
                }
            }
            if (gradeId == null || gradeId.trim().length() <= 0 || gradeId.trim().length() > 11 || !gradeId.trim().matches("[0-9]*")) {
                jsonObject.put("message", "非法操作！");
                b = false;
            }

            Grade oldGrade = null;
            if (b) {
                oldGrade = gradeService.getGradeByGradeId(new Integer(gradeId.trim()));
                jsonObject.put("oldGradeNumber", oldGrade.getGradeNumber());
                jsonObject.put("oldGradeName", oldGrade.getGradeName());
                jsonObject.put("gradeId", oldGrade.getGradeId());
            }


            if (b) {//检查年级号是否为空或只有空格
                if (newGradeNumber == null || newGradeNumber.trim().length() <= 0) {
                    jsonObject.put("message", "年级号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查年级号是否只有数字
                if (!newGradeNumber.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "年级号含有非数字！");
                    b = false;
                }
            }

            if (b) {//检查年级号长度是否大于11
                if (newGradeNumber.trim().length() > 11) {
                    jsonObject.put("message", "年级号长度大于11！");
                    b = false;
                }
            }

            if (b) {//检查年级名称是否为空或只有空格
                if (newGradeName == null || newGradeName.trim().length() <= 0) {
                    jsonObject.put("message", "年级名称为空或只有空格！");
                    b = false;
                }
            }

            if (b) {//检查年级名称长度是否大于40
                if (newGradeName.trim().length() > 40) {
                    jsonObject.put("message", "年级名称长度过长，中文长度大于39或英文长度大于40！");
                    b = false;
                }
            }

            if (b) {//检查年级信息是否改变
                if (oldGrade.getGradeNumber().equals(newGradeNumber.trim()) && oldGrade.getGradeName().equals(newGradeName.trim())) {
                    jsonObject.put("message", "年级信息没变！");
                    jsonObject.put("status", "ok");
                    b = false;
                }
            }

            if (b) {//检查年级号改变后是否与其他已存在的相同
                if (!oldGrade.getGradeNumber().equals(newGradeNumber.trim()) && gradeService.isGradeNumber(newGradeNumber.trim())) {
                    jsonObject.put("message", "年级号已存在！");
                    b = false;
                }
            }

            if (b) {
                if (!newGradeName.trim().equals(oldGrade.getGradeName()) && gradeService.isGradeName(newGradeName.trim())) {
                    jsonObject.put("message", "年级名称已存在！");
                    b = false;
                }
            }

            if(b) {
                oldGrade.setGradeNumber(newGradeNumber.trim());
                oldGrade.setGradeName(newGradeName.trim());
                boolean b1 = gradeService.updateGrade(oldGrade);
                if(b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                } else {
                    jsonObject.put("message", "年级信息修改成功！");
                    jsonObject.put("status", "ok");
                    b = false;
                }
            }
            response.getWriter().print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjax", method = RequestMethod.GET)
    public void deleteAjaxGrade(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String gradeId = request.getParameter("gradeId");

        System.out.println("gradeId:" + gradeId);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if(b) {
                if(indexService.isLogin(session) == false) {
                    jsonObject.put("message", "请先登录！");
                    b = false;
                }
            }
            if (b) {
                if (gradeId == null || gradeId.trim().length() <= 0 || gradeId.trim().length() > 11 || !gradeId.trim().matches("[0-9]*")) {
                    jsonObject.put("message", "非法操作！");
                    b = false;
                }
            }


            if (b) {
                if (gradeService.getGradeByGradeId(new Integer(gradeId.trim())) == null) {
                    jsonObject.put("message", "该年级已不存在！");
                    b = false;
                }
            }

            if (b) {
                boolean b2 = gradeService.deleteGrade(gradeService.getGradeByGradeId(new Integer(gradeId.trim())));
                if (b2 == false) {
                    jsonObject.put("message", "服务器出错！");
                    b = false;
                } else {
                    jsonObject.put("message", "删除年级成功！");
                    jsonObject.put("status", "ok");
                }
            }

            response.getWriter().print(jsonObject.toString());

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/insertAjax", method = RequestMethod.GET)
    public void insertAjaxGrade(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String newGradeNumber = request.getParameter("newGradeNumber");
        String newGradeName = request.getParameter("newGradeName");

        System.out.println("newGradeNumber:" + newGradeNumber);
        System.out.println("newGradeName:" + newGradeName);

        JSONObject jsonObject = new JSONObject();
        boolean b = true;

        try {
            jsonObject.put("status", "error");
            if(b) {
                if(indexService.isLogin(session) == false) {
                    jsonObject.put("message", "请先登录！");
                    b = false;
                }
            }
            if (b) {
                if (newGradeNumber == null || newGradeNumber.trim().length() <= 0) {
                    jsonObject.put("message", "年级号为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (!newGradeNumber.matches("[0-9]*")) {
                    jsonObject.put("message", "年级号含有非数字！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeNumber.trim().length() > 11) {
                    jsonObject.put("message", "年级号长度大于11！");
                    b = false;
                }
            }

            if (b) {
                if (gradeService.isGradeNumber(newGradeNumber.trim())) {
                    jsonObject.put("message", "年级号已存在！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName == null || newGradeName.trim().length() <= 0) {
                    jsonObject.put("message", "年级名称为空或只有空格！");
                    b = false;
                }
            }

            if (b) {
                if (newGradeName.trim().length() > 40) {
                    jsonObject.put("message", "年级名称长度过长，中文长度大于13或英文长度大于40！");
                    b = false;
                }
            }

            if (b) {
                if (gradeService.isGradeName(newGradeName.trim())) {
                    jsonObject.put("message", "年级名称已存在！");
                    b = false;
                }
            }

            if (b) {
                Grade grade = SpringUtil.getBean(Grade.class);
                grade.setGradeNumber(newGradeNumber.trim());
                grade.setGradeName(newGradeName.trim());
                boolean b1 = gradeService.insertGrade(grade);
                if (b1 == false) {
                    jsonObject.put("message", "服务器出错！");
                } else {
                    jsonObject.put("message", "添加年级成功！");
                    jsonObject.put("status", "ok");
                    jsonObject.put("gradeId", gradeService.getGradeByGradeNumber(newGradeNumber.trim()).getGradeId());
                }
            }

            response.getWriter().print(jsonObject.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
