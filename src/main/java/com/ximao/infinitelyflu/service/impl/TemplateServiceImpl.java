package com.ximao.infinitelyflu.service.impl;

import com.ximao.infinitelyflu.dao.TemplateMapper;
import com.ximao.infinitelyflu.pojo.Template;
import com.ximao.infinitelyflu.service.ITemplateService;
import com.ximao.infinitelyflu.service.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * 模板数据服务实现类
 * @author ximao
 * @date 2021/7/7
 */
public class TemplateServiceImpl implements ITemplateService {

    // 默认下载文件路径
    private static final String DOWNLOAD_DIR =
            "/Users/apple/Documents/JavaProjects/InfinitelyFlu-server/src/main/webapp/down";

    // service层调dao层，组合关系
    private TemplateMapper templateMapper;

    public void setTemplateMapper(TemplateMapper templateMapper) {
        this.templateMapper = templateMapper;
    }

    public int addTemplate(Template template, MultipartFile multipartFile) {
        String newPath = FileUtils.upload(multipartFile);
        template.setFile(newPath);
        return templateMapper.addTemplate(template);
    }

    public int deleteTemplateById(int id) {
        return templateMapper.deleteTemplateById(id);
    }

    public int updateTemplate(Template template) {
        return templateMapper.updateTemplate(template);
    }

    public Template queryTemplateById(int id) {
        return templateMapper.queryTemplateById(id);
    }

    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {


    }

    public List<Template> queryAllTemplate() {
        return templateMapper.queryAllTemplate();
    }
}
