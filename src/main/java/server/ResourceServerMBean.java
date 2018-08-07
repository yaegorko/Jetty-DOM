package server;

import resources.TestResource;

public interface ResourceServerMBean {

    void setTestResource(TestResource testResource);
    String getname();
    int getage();

}
