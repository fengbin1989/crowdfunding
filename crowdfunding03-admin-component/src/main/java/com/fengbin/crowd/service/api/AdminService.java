package com.fengbin.crowd.service.api;
/*
@作者 冯彬
@时间 2020-06-02-23:21
*/

import com.fengbin.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword,Integer pageNumber,Integer pageSize);

    void remove(Integer adminId);
}
