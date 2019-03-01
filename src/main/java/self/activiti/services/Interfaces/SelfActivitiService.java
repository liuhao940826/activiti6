package self.activiti.services.Interfaces;

import org.activiti.engine.repository.ProcessDefinition;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 11:07 AM 2019/1/7
 */
public interface SelfActivitiService {

    void createDeployment(String resource);

    void startProcessInstanceByKey(String key);

    ProcessDefinition getLastDeployment(String key);
}
