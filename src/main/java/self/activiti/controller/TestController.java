package self.activiti.controller;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.activiti.config.Constants;
import self.activiti.services.Interfaces.SelfActivitiService;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 10:36 AM 2019/1/11
 */
@RestController
@RequestMapping(value = "/activiti")
public class TestController {

    @Autowired
    SelfActivitiService selfActivitiService;

    @RequestMapping("/start")
    public  String startProcessInstance() {

        selfActivitiService.startProcessInstanceByKey(Constants.KEY);

        ProcessDefinition financialReport = selfActivitiService.getLastDeployment(Constants.KEY);

        System.out.println(financialReport.getId());

        return financialReport.getId();

    }
}
