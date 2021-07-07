package com.ximao.infinitelyflu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板数据类
 * @author ximao
 * @date 2021/7/7
 * lombok的三个注解自动构造数据类和setter&getter方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {

    private int id;

    private String version;

    private String name;

}
