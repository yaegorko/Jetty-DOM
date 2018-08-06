package server;

import resources.TestResource;

public class ResourceServer implements ResourceServerMBean {

    TestResource testResource;

    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;
    }

    public ResourceServer(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }


}
