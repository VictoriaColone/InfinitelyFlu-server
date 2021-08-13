package com.ximao.infinitelyflu.service.impl;

import com.ximao.infinitelyflu.dao.TemplateMapper;
import com.ximao.infinitelyflu.pojo.Template;
import com.ximao.infinitelyflu.service.ITemplateService;
import com.ximao.infinitelyflu.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 模板数据服务实现类
 * @author ximao
 * @date 2021/7/7
 */
public class TemplateServiceImpl implements ITemplateService {

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
        String fileName = templateMapper.queryTemplateById(id).getFile();
        FileUtils.delete(fileName);
        return templateMapper.deleteTemplateById(id);
    }

    public int updateTemplate(Template template) {
        return templateMapper.updateTemplate(template);
    }

    public Template queryTemplateById(int id) {
        return templateMapper.queryTemplateById(id);
    }

    public void downloadTemplate(Template template, HttpServletRequest request, HttpServletResponse response) {
        String fileName = templateMapper.queryTemplateByNameAndVersion(template.getName(), template.getVersion()).getFile();
        FileUtils.download(fileName, response);
    }

    public void getTemplateJson(Template template, HttpServletRequest request, HttpServletResponse response) {
        String fileName = templateMapper.queryTemplateByNameAndVersion(template.getName(), template.getVersion()).getFile();
        FileUtils.generateJson(fileName, response);
    }

    public List<Template> queryAllTemplate() {
        return templateMapper.queryAllTemplate();
    }
}
