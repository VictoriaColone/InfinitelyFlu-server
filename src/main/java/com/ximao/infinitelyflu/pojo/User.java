package com.ximao.infinitelyflu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户数据类
 * @author ximao
 * @date 2021/7/6
 * lombok的三个注解自动构造数据类和setter&getter方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String name;

    private String account;

    private String password;

}
