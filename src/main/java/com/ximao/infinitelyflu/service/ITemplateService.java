package com.ximao.infinitelyflu.service;


import com.ximao.infinitelyflu.pojo.Template;

import java.util.List;

/**
 * 模板数据服务接口
 * @author ximao
 * @date 2021/7/7
 */
public interface ITemplateService {

    // 对模板CURD
    int addTemplate(Template template);

    int deleteTemplateById(int id);

    int updateTemplate(Template template);

    Template queryTemplateById(int id);

    List<Template> queryAllTemplate();
}
