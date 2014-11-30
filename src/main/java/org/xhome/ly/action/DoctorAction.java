package org.xhome.ly.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhome.ly.bean.Doctor;
import org.xhome.ly.common.Response;
import org.xhome.ly.service.DoctorService;


/**
 * Created by fenjuly
 * Date: 14/11/30
 * Time: 下午9:29
 */
@Controller
public class DoctorAction {

    @Autowired
    private DoctorService doctorService;

    @ResponseBody
    @RequestMapping(value="/api/login",method= RequestMethod.POST)
    public Object registDoctor(@RequestBody Doctor doctor) {
        int status;
        status = doctorService.add(doctor);
        return new Response(status);
    }

}
