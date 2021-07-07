package com.ximao.infinitelyflu.service.impl;

import com.ximao.infinitelyflu.dao.TemplateMapper;
import com.ximao.infinitelyflu.pojo.Template;
import com.ximao.infinitelyflu.service.ITemplateService;

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

    public int addTemplate(Template template) {
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

    public List<Template> queryAllTemplate() {
        return templateMapper.queryAllTemplate();
    }
}
