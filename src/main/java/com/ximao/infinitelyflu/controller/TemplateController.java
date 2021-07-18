package com.ximao.infinitelyflu.controller;


import com.ximao.infinitelyflu.pojo.Template;
import com.ximao.infinitelyflu.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 模板数据服务控制类
 * @author ximao
 * @date 2021/7/7
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    @Qualifier("TemplateServiceImpl")
    private ITemplateService templateService;

    @RequestMapping("/allTemplate")
    public String list(Model model) {
        List<Template> list = templateService.queryAllTemplate();
        model.addAttribute("list", list);
        return "allTemplate";
    }

    /**
     * 跳转到添加模板页面
     */
    @RequestMapping("/toAddTemplate")
    public String toAddTemplate(){
        return "addTemplate";
    }

    @RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
    public String addTemplate(String name, String version, MultipartFile multipartFile) {
        Template template = new Template();
        template.setName(name);
        template.setVersion(version);
        templateService.addTemplate(template, multipartFile);
        return "redirect:/template/allTemplate";
    }

    /**
     * 跳转到更新模板页面
     */
    @RequestMapping("/toUpdateTemplate")
    public String toUpdateTemplate(Model model, int id) {
        Template template = templateService.queryTemplateById(id);
        model.addAttribute("template", template);
        return "updateTemplate";
    }

    @RequestMapping("/updateTemplate")
    public String updateTemplate(Template template) {
        templateService.updateTemplate(template);
        return "redirect:/template/allTemplate";
    }

    @RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        templateService.downloadTemplate(request, response);
    }

    @RequestMapping("/deleteTemplate/{id}")
    public String deleteTemplate(@PathVariable("id") int id) {
        templateService.deleteTemplateById(id);
        return "redirect:/template/allTemplate";
    }

}
