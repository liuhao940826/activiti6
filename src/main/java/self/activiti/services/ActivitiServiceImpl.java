package self.activiti.services;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.activiti.services.Interfaces.ActivitiService;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 11:08 AM 2019/1/7
 */
@Service
public class ActivitiServiceImpl implements ActivitiService, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiServiceImpl.class);

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * 交给Spring初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("spring容器初始化bean执行方法:"+this.getClass().getName());
        createDeployment("bpmn/FinancialReport.bpmn20.xml");
    }

    /**
     * 流程的路径和文件名
     * 以 repository
     * @param resource
     */
    @Override
    public void createDeployment(String resource) {
        logger.info("部署 Activiti 工作引擎流程文件:{},开始",resource);
        Deployment deploy = repositoryService.createDeployment().addClasspathResource(resource).deploy();
        logger.info("部署 Activiti 工作引擎流程文件:{},结束 部署Id:{},部署名字:{}",resource,deploy.getId(),deploy.getName());

        //key 是对应的bpmn文件中的<process id="financialReport">
//        startProcessInstanceByKey("financialReport");

    }

    /**
     * 创建流程实例
     * @param key
     */
    @Override
    public void startProcessInstanceByKey(String key) {
        logger.info("创建 Activiti 流程实例:key{},开始",key);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        logger.info("创建 Activiti 流程实例:key{},结束",key);
    }

    /**
     * 部署过的bpmn 会被存储在存储库,可以用下面的API去检索查询
     * @param
     * @return
     */
    @Override
    public ProcessDefinition getLastDeployment(String key) {

        return repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).active().latestVersion().singleResult();
    }


}
