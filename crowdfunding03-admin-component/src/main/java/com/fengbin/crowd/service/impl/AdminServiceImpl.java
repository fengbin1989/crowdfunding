package com.fengbin.crowd.service.impl;
/*
@作者 冯彬
@时间 2020-06-02-23:22
*/

import com.fengbin.crowd.constant.CrowdConstant;
import com.fengbin.crowd.entity.Admin;
import com.fengbin.crowd.entity.AdminExample;
import com.fengbin.crowd.exception.LoginFailedException;
import com.fengbin.crowd.mapper.AdminMapper;
import com.fengbin.crowd.service.api.AdminService;
import com.fengbin.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size() > 1){
            throw  new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = admins.get(0);
        if (admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        String userPdDB = admin.getUserPswd();
        logger.info(userPdDB);
        String userPdForm = CrowdUtil.md5(userPswd);
        if (!Objects.equals(userPdDB,userPdForm)){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        List<Admin> adminList = adminMapper.selectAdminByKeyword(keyword);

        return new PageInfo<>(adminList);
    }

    @Override
    public void remove(Integer adminId) {

        adminMapper.deleteByPrimaryKey(adminId);

    }


}
