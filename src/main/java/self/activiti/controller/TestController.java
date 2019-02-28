package self.activiti.controller;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.activiti.config.Constants;
import self.activiti.services.Interfaces.ActivitiService;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 10:36 AM 2019/1/11
 */
@RestController(value = "/activiti")
public class TestController {

    @Autowired
    ActivitiService activitiService;

    @RequestMapping("/start")
    public  void startProcessInstance(){

        activitiService.startProcessInstanceByKey(Constants.KEY);

        ProcessDefinition financialReport = activitiService.getLastDeployment(Constants.KEY);

    }


}
