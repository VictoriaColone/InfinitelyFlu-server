package com.ximao.infinitelyflu.service;


import com.alibaba.fastjson.JSONObject;
import com.ximao.infinitelyflu.pojo.Template;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 模板数据服务接口
 * @author ximao
 * @date 2021/7/7
 */
public interface ITemplateService {

    // 对模板CURD
    int addTemplate(Template template, MultipartFile multipartFile);

    int deleteTemplateById(int id);

    int updateTemplate(Template template);

    Template queryTemplateById(int id);

    // 下载模板
    void downloadTemplate(Template template, HttpServletRequest request, HttpServletResponse response);

    // 获取模板json数据
    String getTemplateJson(Template template, HttpServletRequest request, HttpServletResponse response);

    List<Template> queryAllTemplate();
}
