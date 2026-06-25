package com.wjy.controller;

import com.wjy.domain.User;
import com.wjy.service.IInfoService;
import com.wjy.utils.R;
import com.wjy.vo.InfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/info")
public class InfoController {


    //
    @Autowired
    private IInfoService iInfoService;

    @GetMapping("/index")
    public R index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);

        InfoVO infoVO = iInfoService.getInfo(user.getFactoryId());
        return R.success("查询成功", infoVO);

    }
}
