package com.ximao.infinitelyflu.dao;


import com.ximao.infinitelyflu.pojo.Template;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作Android模板类
 * @author ximao
 * @date 2021/7/7
 */
public interface TemplateMapper {

    // 对模板CURD
    int addTemplate(Template template);

    int deleteTemplateById(@Param("id") int id);

    int updateTemplate(Template template);

    Template queryTemplateById(@Param("id") int id);

    List<Template> queryAllTemplate();
}
